package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.RecebaEmail;
import com.mycompany.myapp.repository.RecebaEmailRepository;
import com.mycompany.myapp.service.dto.RecebaEmailDTO;
import com.mycompany.myapp.service.mapper.RecebaEmailMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RecebaEmailResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RecebaEmailResourceIT {

    private static final String DEFAULT_OPCAO_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_OPCAO_EMAIL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/receba-emails";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RecebaEmailRepository recebaEmailRepository;

    @Autowired
    private RecebaEmailMapper recebaEmailMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRecebaEmailMockMvc;

    private RecebaEmail recebaEmail;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RecebaEmail createEntity(EntityManager em) {
        RecebaEmail recebaEmail = new RecebaEmail().opcaoEmail(DEFAULT_OPCAO_EMAIL);
        return recebaEmail;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RecebaEmail createUpdatedEntity(EntityManager em) {
        RecebaEmail recebaEmail = new RecebaEmail().opcaoEmail(UPDATED_OPCAO_EMAIL);
        return recebaEmail;
    }

    @BeforeEach
    public void initTest() {
        recebaEmail = createEntity(em);
    }

    @Test
    @Transactional
    void createRecebaEmail() throws Exception {
        int databaseSizeBeforeCreate = recebaEmailRepository.findAll().size();
        // Create the RecebaEmail
        RecebaEmailDTO recebaEmailDTO = recebaEmailMapper.toDto(recebaEmail);
        restRecebaEmailMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recebaEmailDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RecebaEmail in the database
        List<RecebaEmail> recebaEmailList = recebaEmailRepository.findAll();
        assertThat(recebaEmailList).hasSize(databaseSizeBeforeCreate + 1);
        RecebaEmail testRecebaEmail = recebaEmailList.get(recebaEmailList.size() - 1);
        assertThat(testRecebaEmail.getOpcaoEmail()).isEqualTo(DEFAULT_OPCAO_EMAIL);
    }

    @Test
    @Transactional
    void createRecebaEmailWithExistingId() throws Exception {
        // Create the RecebaEmail with an existing ID
        recebaEmail.setId(1L);
        RecebaEmailDTO recebaEmailDTO = recebaEmailMapper.toDto(recebaEmail);

        int databaseSizeBeforeCreate = recebaEmailRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRecebaEmailMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recebaEmailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RecebaEmail in the database
        List<RecebaEmail> recebaEmailList = recebaEmailRepository.findAll();
        assertThat(recebaEmailList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRecebaEmails() throws Exception {
        // Initialize the database
        recebaEmailRepository.saveAndFlush(recebaEmail);

        // Get all the recebaEmailList
        restRecebaEmailMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(recebaEmail.getId().intValue())))
            .andExpect(jsonPath("$.[*].opcaoEmail").value(hasItem(DEFAULT_OPCAO_EMAIL)));
    }

    @Test
    @Transactional
    void getRecebaEmail() throws Exception {
        // Initialize the database
        recebaEmailRepository.saveAndFlush(recebaEmail);

        // Get the recebaEmail
        restRecebaEmailMockMvc
            .perform(get(ENTITY_API_URL_ID, recebaEmail.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(recebaEmail.getId().intValue()))
            .andExpect(jsonPath("$.opcaoEmail").value(DEFAULT_OPCAO_EMAIL));
    }

    @Test
    @Transactional
    void getNonExistingRecebaEmail() throws Exception {
        // Get the recebaEmail
        restRecebaEmailMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewRecebaEmail() throws Exception {
        // Initialize the database
        recebaEmailRepository.saveAndFlush(recebaEmail);

        int databaseSizeBeforeUpdate = recebaEmailRepository.findAll().size();

        // Update the recebaEmail
        RecebaEmail updatedRecebaEmail = recebaEmailRepository.findById(recebaEmail.getId()).get();
        // Disconnect from session so that the updates on updatedRecebaEmail are not directly saved in db
        em.detach(updatedRecebaEmail);
        updatedRecebaEmail.opcaoEmail(UPDATED_OPCAO_EMAIL);
        RecebaEmailDTO recebaEmailDTO = recebaEmailMapper.toDto(updatedRecebaEmail);

        restRecebaEmailMockMvc
            .perform(
                put(ENTITY_API_URL_ID, recebaEmailDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recebaEmailDTO))
            )
            .andExpect(status().isOk());

        // Validate the RecebaEmail in the database
        List<RecebaEmail> recebaEmailList = recebaEmailRepository.findAll();
        assertThat(recebaEmailList).hasSize(databaseSizeBeforeUpdate);
        RecebaEmail testRecebaEmail = recebaEmailList.get(recebaEmailList.size() - 1);
        assertThat(testRecebaEmail.getOpcaoEmail()).isEqualTo(UPDATED_OPCAO_EMAIL);
    }

    @Test
    @Transactional
    void putNonExistingRecebaEmail() throws Exception {
        int databaseSizeBeforeUpdate = recebaEmailRepository.findAll().size();
        recebaEmail.setId(count.incrementAndGet());

        // Create the RecebaEmail
        RecebaEmailDTO recebaEmailDTO = recebaEmailMapper.toDto(recebaEmail);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecebaEmailMockMvc
            .perform(
                put(ENTITY_API_URL_ID, recebaEmailDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recebaEmailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RecebaEmail in the database
        List<RecebaEmail> recebaEmailList = recebaEmailRepository.findAll();
        assertThat(recebaEmailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRecebaEmail() throws Exception {
        int databaseSizeBeforeUpdate = recebaEmailRepository.findAll().size();
        recebaEmail.setId(count.incrementAndGet());

        // Create the RecebaEmail
        RecebaEmailDTO recebaEmailDTO = recebaEmailMapper.toDto(recebaEmail);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRecebaEmailMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(recebaEmailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RecebaEmail in the database
        List<RecebaEmail> recebaEmailList = recebaEmailRepository.findAll();
        assertThat(recebaEmailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRecebaEmail() throws Exception {
        int databaseSizeBeforeUpdate = recebaEmailRepository.findAll().size();
        recebaEmail.setId(count.incrementAndGet());

        // Create the RecebaEmail
        RecebaEmailDTO recebaEmailDTO = recebaEmailMapper.toDto(recebaEmail);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRecebaEmailMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(recebaEmailDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the RecebaEmail in the database
        List<RecebaEmail> recebaEmailList = recebaEmailRepository.findAll();
        assertThat(recebaEmailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRecebaEmailWithPatch() throws Exception {
        // Initialize the database
        recebaEmailRepository.saveAndFlush(recebaEmail);

        int databaseSizeBeforeUpdate = recebaEmailRepository.findAll().size();

        // Update the recebaEmail using partial update
        RecebaEmail partialUpdatedRecebaEmail = new RecebaEmail();
        partialUpdatedRecebaEmail.setId(recebaEmail.getId());

        restRecebaEmailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRecebaEmail.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRecebaEmail))
            )
            .andExpect(status().isOk());

        // Validate the RecebaEmail in the database
        List<RecebaEmail> recebaEmailList = recebaEmailRepository.findAll();
        assertThat(recebaEmailList).hasSize(databaseSizeBeforeUpdate);
        RecebaEmail testRecebaEmail = recebaEmailList.get(recebaEmailList.size() - 1);
        assertThat(testRecebaEmail.getOpcaoEmail()).isEqualTo(DEFAULT_OPCAO_EMAIL);
    }

    @Test
    @Transactional
    void fullUpdateRecebaEmailWithPatch() throws Exception {
        // Initialize the database
        recebaEmailRepository.saveAndFlush(recebaEmail);

        int databaseSizeBeforeUpdate = recebaEmailRepository.findAll().size();

        // Update the recebaEmail using partial update
        RecebaEmail partialUpdatedRecebaEmail = new RecebaEmail();
        partialUpdatedRecebaEmail.setId(recebaEmail.getId());

        partialUpdatedRecebaEmail.opcaoEmail(UPDATED_OPCAO_EMAIL);

        restRecebaEmailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRecebaEmail.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRecebaEmail))
            )
            .andExpect(status().isOk());

        // Validate the RecebaEmail in the database
        List<RecebaEmail> recebaEmailList = recebaEmailRepository.findAll();
        assertThat(recebaEmailList).hasSize(databaseSizeBeforeUpdate);
        RecebaEmail testRecebaEmail = recebaEmailList.get(recebaEmailList.size() - 1);
        assertThat(testRecebaEmail.getOpcaoEmail()).isEqualTo(UPDATED_OPCAO_EMAIL);
    }

    @Test
    @Transactional
    void patchNonExistingRecebaEmail() throws Exception {
        int databaseSizeBeforeUpdate = recebaEmailRepository.findAll().size();
        recebaEmail.setId(count.incrementAndGet());

        // Create the RecebaEmail
        RecebaEmailDTO recebaEmailDTO = recebaEmailMapper.toDto(recebaEmail);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRecebaEmailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, recebaEmailDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(recebaEmailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RecebaEmail in the database
        List<RecebaEmail> recebaEmailList = recebaEmailRepository.findAll();
        assertThat(recebaEmailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRecebaEmail() throws Exception {
        int databaseSizeBeforeUpdate = recebaEmailRepository.findAll().size();
        recebaEmail.setId(count.incrementAndGet());

        // Create the RecebaEmail
        RecebaEmailDTO recebaEmailDTO = recebaEmailMapper.toDto(recebaEmail);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRecebaEmailMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(recebaEmailDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RecebaEmail in the database
        List<RecebaEmail> recebaEmailList = recebaEmailRepository.findAll();
        assertThat(recebaEmailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRecebaEmail() throws Exception {
        int databaseSizeBeforeUpdate = recebaEmailRepository.findAll().size();
        recebaEmail.setId(count.incrementAndGet());

        // Create the RecebaEmail
        RecebaEmailDTO recebaEmailDTO = recebaEmailMapper.toDto(recebaEmail);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRecebaEmailMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(recebaEmailDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RecebaEmail in the database
        List<RecebaEmail> recebaEmailList = recebaEmailRepository.findAll();
        assertThat(recebaEmailList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRecebaEmail() throws Exception {
        // Initialize the database
        recebaEmailRepository.saveAndFlush(recebaEmail);

        int databaseSizeBeforeDelete = recebaEmailRepository.findAll().size();

        // Delete the recebaEmail
        restRecebaEmailMockMvc
            .perform(delete(ENTITY_API_URL_ID, recebaEmail.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RecebaEmail> recebaEmailList = recebaEmailRepository.findAll();
        assertThat(recebaEmailList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
