package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.OpcaoEscolhida;
import com.mycompany.myapp.repository.OpcaoEscolhidaRepository;
import com.mycompany.myapp.service.dto.OpcaoEscolhidaDTO;
import com.mycompany.myapp.service.mapper.OpcaoEscolhidaMapper;
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
 * Integration tests for the {@link OpcaoEscolhidaResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OpcaoEscolhidaResourceIT {

    private static final String DEFAULT_OPCAO_INICIAL = "AAAAAAAAAA";
    private static final String UPDATED_OPCAO_INICIAL = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/opcao-escolhidas";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OpcaoEscolhidaRepository opcaoEscolhidaRepository;

    @Autowired
    private OpcaoEscolhidaMapper opcaoEscolhidaMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOpcaoEscolhidaMockMvc;

    private OpcaoEscolhida opcaoEscolhida;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OpcaoEscolhida createEntity(EntityManager em) {
        OpcaoEscolhida opcaoEscolhida = new OpcaoEscolhida().opcaoInicial(DEFAULT_OPCAO_INICIAL);
        return opcaoEscolhida;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OpcaoEscolhida createUpdatedEntity(EntityManager em) {
        OpcaoEscolhida opcaoEscolhida = new OpcaoEscolhida().opcaoInicial(UPDATED_OPCAO_INICIAL);
        return opcaoEscolhida;
    }

    @BeforeEach
    public void initTest() {
        opcaoEscolhida = createEntity(em);
    }

    @Test
    @Transactional
    void createOpcaoEscolhida() throws Exception {
        int databaseSizeBeforeCreate = opcaoEscolhidaRepository.findAll().size();
        // Create the OpcaoEscolhida
        OpcaoEscolhidaDTO opcaoEscolhidaDTO = opcaoEscolhidaMapper.toDto(opcaoEscolhida);
        restOpcaoEscolhidaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(opcaoEscolhidaDTO))
            )
            .andExpect(status().isCreated());

        // Validate the OpcaoEscolhida in the database
        List<OpcaoEscolhida> opcaoEscolhidaList = opcaoEscolhidaRepository.findAll();
        assertThat(opcaoEscolhidaList).hasSize(databaseSizeBeforeCreate + 1);
        OpcaoEscolhida testOpcaoEscolhida = opcaoEscolhidaList.get(opcaoEscolhidaList.size() - 1);
        assertThat(testOpcaoEscolhida.getOpcaoInicial()).isEqualTo(DEFAULT_OPCAO_INICIAL);
    }

    @Test
    @Transactional
    void createOpcaoEscolhidaWithExistingId() throws Exception {
        // Create the OpcaoEscolhida with an existing ID
        opcaoEscolhida.setId(1L);
        OpcaoEscolhidaDTO opcaoEscolhidaDTO = opcaoEscolhidaMapper.toDto(opcaoEscolhida);

        int databaseSizeBeforeCreate = opcaoEscolhidaRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOpcaoEscolhidaMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(opcaoEscolhidaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OpcaoEscolhida in the database
        List<OpcaoEscolhida> opcaoEscolhidaList = opcaoEscolhidaRepository.findAll();
        assertThat(opcaoEscolhidaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOpcaoEscolhidas() throws Exception {
        // Initialize the database
        opcaoEscolhidaRepository.saveAndFlush(opcaoEscolhida);

        // Get all the opcaoEscolhidaList
        restOpcaoEscolhidaMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(opcaoEscolhida.getId().intValue())))
            .andExpect(jsonPath("$.[*].opcaoInicial").value(hasItem(DEFAULT_OPCAO_INICIAL)));
    }

    @Test
    @Transactional
    void getOpcaoEscolhida() throws Exception {
        // Initialize the database
        opcaoEscolhidaRepository.saveAndFlush(opcaoEscolhida);

        // Get the opcaoEscolhida
        restOpcaoEscolhidaMockMvc
            .perform(get(ENTITY_API_URL_ID, opcaoEscolhida.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(opcaoEscolhida.getId().intValue()))
            .andExpect(jsonPath("$.opcaoInicial").value(DEFAULT_OPCAO_INICIAL));
    }

    @Test
    @Transactional
    void getNonExistingOpcaoEscolhida() throws Exception {
        // Get the opcaoEscolhida
        restOpcaoEscolhidaMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewOpcaoEscolhida() throws Exception {
        // Initialize the database
        opcaoEscolhidaRepository.saveAndFlush(opcaoEscolhida);

        int databaseSizeBeforeUpdate = opcaoEscolhidaRepository.findAll().size();

        // Update the opcaoEscolhida
        OpcaoEscolhida updatedOpcaoEscolhida = opcaoEscolhidaRepository.findById(opcaoEscolhida.getId()).get();
        // Disconnect from session so that the updates on updatedOpcaoEscolhida are not directly saved in db
        em.detach(updatedOpcaoEscolhida);
        updatedOpcaoEscolhida.opcaoInicial(UPDATED_OPCAO_INICIAL);
        OpcaoEscolhidaDTO opcaoEscolhidaDTO = opcaoEscolhidaMapper.toDto(updatedOpcaoEscolhida);

        restOpcaoEscolhidaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, opcaoEscolhidaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(opcaoEscolhidaDTO))
            )
            .andExpect(status().isOk());

        // Validate the OpcaoEscolhida in the database
        List<OpcaoEscolhida> opcaoEscolhidaList = opcaoEscolhidaRepository.findAll();
        assertThat(opcaoEscolhidaList).hasSize(databaseSizeBeforeUpdate);
        OpcaoEscolhida testOpcaoEscolhida = opcaoEscolhidaList.get(opcaoEscolhidaList.size() - 1);
        assertThat(testOpcaoEscolhida.getOpcaoInicial()).isEqualTo(UPDATED_OPCAO_INICIAL);
    }

    @Test
    @Transactional
    void putNonExistingOpcaoEscolhida() throws Exception {
        int databaseSizeBeforeUpdate = opcaoEscolhidaRepository.findAll().size();
        opcaoEscolhida.setId(count.incrementAndGet());

        // Create the OpcaoEscolhida
        OpcaoEscolhidaDTO opcaoEscolhidaDTO = opcaoEscolhidaMapper.toDto(opcaoEscolhida);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOpcaoEscolhidaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, opcaoEscolhidaDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(opcaoEscolhidaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OpcaoEscolhida in the database
        List<OpcaoEscolhida> opcaoEscolhidaList = opcaoEscolhidaRepository.findAll();
        assertThat(opcaoEscolhidaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOpcaoEscolhida() throws Exception {
        int databaseSizeBeforeUpdate = opcaoEscolhidaRepository.findAll().size();
        opcaoEscolhida.setId(count.incrementAndGet());

        // Create the OpcaoEscolhida
        OpcaoEscolhidaDTO opcaoEscolhidaDTO = opcaoEscolhidaMapper.toDto(opcaoEscolhida);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOpcaoEscolhidaMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(opcaoEscolhidaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OpcaoEscolhida in the database
        List<OpcaoEscolhida> opcaoEscolhidaList = opcaoEscolhidaRepository.findAll();
        assertThat(opcaoEscolhidaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOpcaoEscolhida() throws Exception {
        int databaseSizeBeforeUpdate = opcaoEscolhidaRepository.findAll().size();
        opcaoEscolhida.setId(count.incrementAndGet());

        // Create the OpcaoEscolhida
        OpcaoEscolhidaDTO opcaoEscolhidaDTO = opcaoEscolhidaMapper.toDto(opcaoEscolhida);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOpcaoEscolhidaMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(opcaoEscolhidaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OpcaoEscolhida in the database
        List<OpcaoEscolhida> opcaoEscolhidaList = opcaoEscolhidaRepository.findAll();
        assertThat(opcaoEscolhidaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOpcaoEscolhidaWithPatch() throws Exception {
        // Initialize the database
        opcaoEscolhidaRepository.saveAndFlush(opcaoEscolhida);

        int databaseSizeBeforeUpdate = opcaoEscolhidaRepository.findAll().size();

        // Update the opcaoEscolhida using partial update
        OpcaoEscolhida partialUpdatedOpcaoEscolhida = new OpcaoEscolhida();
        partialUpdatedOpcaoEscolhida.setId(opcaoEscolhida.getId());

        partialUpdatedOpcaoEscolhida.opcaoInicial(UPDATED_OPCAO_INICIAL);

        restOpcaoEscolhidaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOpcaoEscolhida.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOpcaoEscolhida))
            )
            .andExpect(status().isOk());

        // Validate the OpcaoEscolhida in the database
        List<OpcaoEscolhida> opcaoEscolhidaList = opcaoEscolhidaRepository.findAll();
        assertThat(opcaoEscolhidaList).hasSize(databaseSizeBeforeUpdate);
        OpcaoEscolhida testOpcaoEscolhida = opcaoEscolhidaList.get(opcaoEscolhidaList.size() - 1);
        assertThat(testOpcaoEscolhida.getOpcaoInicial()).isEqualTo(UPDATED_OPCAO_INICIAL);
    }

    @Test
    @Transactional
    void fullUpdateOpcaoEscolhidaWithPatch() throws Exception {
        // Initialize the database
        opcaoEscolhidaRepository.saveAndFlush(opcaoEscolhida);

        int databaseSizeBeforeUpdate = opcaoEscolhidaRepository.findAll().size();

        // Update the opcaoEscolhida using partial update
        OpcaoEscolhida partialUpdatedOpcaoEscolhida = new OpcaoEscolhida();
        partialUpdatedOpcaoEscolhida.setId(opcaoEscolhida.getId());

        partialUpdatedOpcaoEscolhida.opcaoInicial(UPDATED_OPCAO_INICIAL);

        restOpcaoEscolhidaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOpcaoEscolhida.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOpcaoEscolhida))
            )
            .andExpect(status().isOk());

        // Validate the OpcaoEscolhida in the database
        List<OpcaoEscolhida> opcaoEscolhidaList = opcaoEscolhidaRepository.findAll();
        assertThat(opcaoEscolhidaList).hasSize(databaseSizeBeforeUpdate);
        OpcaoEscolhida testOpcaoEscolhida = opcaoEscolhidaList.get(opcaoEscolhidaList.size() - 1);
        assertThat(testOpcaoEscolhida.getOpcaoInicial()).isEqualTo(UPDATED_OPCAO_INICIAL);
    }

    @Test
    @Transactional
    void patchNonExistingOpcaoEscolhida() throws Exception {
        int databaseSizeBeforeUpdate = opcaoEscolhidaRepository.findAll().size();
        opcaoEscolhida.setId(count.incrementAndGet());

        // Create the OpcaoEscolhida
        OpcaoEscolhidaDTO opcaoEscolhidaDTO = opcaoEscolhidaMapper.toDto(opcaoEscolhida);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOpcaoEscolhidaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, opcaoEscolhidaDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(opcaoEscolhidaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OpcaoEscolhida in the database
        List<OpcaoEscolhida> opcaoEscolhidaList = opcaoEscolhidaRepository.findAll();
        assertThat(opcaoEscolhidaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOpcaoEscolhida() throws Exception {
        int databaseSizeBeforeUpdate = opcaoEscolhidaRepository.findAll().size();
        opcaoEscolhida.setId(count.incrementAndGet());

        // Create the OpcaoEscolhida
        OpcaoEscolhidaDTO opcaoEscolhidaDTO = opcaoEscolhidaMapper.toDto(opcaoEscolhida);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOpcaoEscolhidaMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(opcaoEscolhidaDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OpcaoEscolhida in the database
        List<OpcaoEscolhida> opcaoEscolhidaList = opcaoEscolhidaRepository.findAll();
        assertThat(opcaoEscolhidaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOpcaoEscolhida() throws Exception {
        int databaseSizeBeforeUpdate = opcaoEscolhidaRepository.findAll().size();
        opcaoEscolhida.setId(count.incrementAndGet());

        // Create the OpcaoEscolhida
        OpcaoEscolhidaDTO opcaoEscolhidaDTO = opcaoEscolhidaMapper.toDto(opcaoEscolhida);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOpcaoEscolhidaMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(opcaoEscolhidaDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OpcaoEscolhida in the database
        List<OpcaoEscolhida> opcaoEscolhidaList = opcaoEscolhidaRepository.findAll();
        assertThat(opcaoEscolhidaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOpcaoEscolhida() throws Exception {
        // Initialize the database
        opcaoEscolhidaRepository.saveAndFlush(opcaoEscolhida);

        int databaseSizeBeforeDelete = opcaoEscolhidaRepository.findAll().size();

        // Delete the opcaoEscolhida
        restOpcaoEscolhidaMockMvc
            .perform(delete(ENTITY_API_URL_ID, opcaoEscolhida.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OpcaoEscolhida> opcaoEscolhidaList = opcaoEscolhidaRepository.findAll();
        assertThat(opcaoEscolhidaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
