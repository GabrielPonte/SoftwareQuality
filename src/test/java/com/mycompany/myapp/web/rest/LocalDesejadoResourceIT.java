package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.LocalDesejado;
import com.mycompany.myapp.repository.LocalDesejadoRepository;
import com.mycompany.myapp.service.dto.LocalDesejadoDTO;
import com.mycompany.myapp.service.mapper.LocalDesejadoMapper;
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
 * Integration tests for the {@link LocalDesejadoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class LocalDesejadoResourceIT {

    private static final String DEFAULT_LOCAL_COVID = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_COVID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/local-desejados";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private LocalDesejadoRepository localDesejadoRepository;

    @Autowired
    private LocalDesejadoMapper localDesejadoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLocalDesejadoMockMvc;

    private LocalDesejado localDesejado;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LocalDesejado createEntity(EntityManager em) {
        LocalDesejado localDesejado = new LocalDesejado().localCovid(DEFAULT_LOCAL_COVID);
        return localDesejado;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LocalDesejado createUpdatedEntity(EntityManager em) {
        LocalDesejado localDesejado = new LocalDesejado().localCovid(UPDATED_LOCAL_COVID);
        return localDesejado;
    }

    @BeforeEach
    public void initTest() {
        localDesejado = createEntity(em);
    }

    @Test
    @Transactional
    void createLocalDesejado() throws Exception {
        int databaseSizeBeforeCreate = localDesejadoRepository.findAll().size();
        // Create the LocalDesejado
        LocalDesejadoDTO localDesejadoDTO = localDesejadoMapper.toDto(localDesejado);
        restLocalDesejadoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(localDesejadoDTO))
            )
            .andExpect(status().isCreated());

        // Validate the LocalDesejado in the database
        List<LocalDesejado> localDesejadoList = localDesejadoRepository.findAll();
        assertThat(localDesejadoList).hasSize(databaseSizeBeforeCreate + 1);
        LocalDesejado testLocalDesejado = localDesejadoList.get(localDesejadoList.size() - 1);
        assertThat(testLocalDesejado.getLocalCovid()).isEqualTo(DEFAULT_LOCAL_COVID);
    }

    @Test
    @Transactional
    void createLocalDesejadoWithExistingId() throws Exception {
        // Create the LocalDesejado with an existing ID
        localDesejado.setId(1L);
        LocalDesejadoDTO localDesejadoDTO = localDesejadoMapper.toDto(localDesejado);

        int databaseSizeBeforeCreate = localDesejadoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restLocalDesejadoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(localDesejadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LocalDesejado in the database
        List<LocalDesejado> localDesejadoList = localDesejadoRepository.findAll();
        assertThat(localDesejadoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllLocalDesejados() throws Exception {
        // Initialize the database
        localDesejadoRepository.saveAndFlush(localDesejado);

        // Get all the localDesejadoList
        restLocalDesejadoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(localDesejado.getId().intValue())))
            .andExpect(jsonPath("$.[*].localCovid").value(hasItem(DEFAULT_LOCAL_COVID)));
    }

    @Test
    @Transactional
    void getLocalDesejado() throws Exception {
        // Initialize the database
        localDesejadoRepository.saveAndFlush(localDesejado);

        // Get the localDesejado
        restLocalDesejadoMockMvc
            .perform(get(ENTITY_API_URL_ID, localDesejado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(localDesejado.getId().intValue()))
            .andExpect(jsonPath("$.localCovid").value(DEFAULT_LOCAL_COVID));
    }

    @Test
    @Transactional
    void getNonExistingLocalDesejado() throws Exception {
        // Get the localDesejado
        restLocalDesejadoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewLocalDesejado() throws Exception {
        // Initialize the database
        localDesejadoRepository.saveAndFlush(localDesejado);

        int databaseSizeBeforeUpdate = localDesejadoRepository.findAll().size();

        // Update the localDesejado
        LocalDesejado updatedLocalDesejado = localDesejadoRepository.findById(localDesejado.getId()).get();
        // Disconnect from session so that the updates on updatedLocalDesejado are not directly saved in db
        em.detach(updatedLocalDesejado);
        updatedLocalDesejado.localCovid(UPDATED_LOCAL_COVID);
        LocalDesejadoDTO localDesejadoDTO = localDesejadoMapper.toDto(updatedLocalDesejado);

        restLocalDesejadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, localDesejadoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(localDesejadoDTO))
            )
            .andExpect(status().isOk());

        // Validate the LocalDesejado in the database
        List<LocalDesejado> localDesejadoList = localDesejadoRepository.findAll();
        assertThat(localDesejadoList).hasSize(databaseSizeBeforeUpdate);
        LocalDesejado testLocalDesejado = localDesejadoList.get(localDesejadoList.size() - 1);
        assertThat(testLocalDesejado.getLocalCovid()).isEqualTo(UPDATED_LOCAL_COVID);
    }

    @Test
    @Transactional
    void putNonExistingLocalDesejado() throws Exception {
        int databaseSizeBeforeUpdate = localDesejadoRepository.findAll().size();
        localDesejado.setId(count.incrementAndGet());

        // Create the LocalDesejado
        LocalDesejadoDTO localDesejadoDTO = localDesejadoMapper.toDto(localDesejado);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocalDesejadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, localDesejadoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(localDesejadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LocalDesejado in the database
        List<LocalDesejado> localDesejadoList = localDesejadoRepository.findAll();
        assertThat(localDesejadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchLocalDesejado() throws Exception {
        int databaseSizeBeforeUpdate = localDesejadoRepository.findAll().size();
        localDesejado.setId(count.incrementAndGet());

        // Create the LocalDesejado
        LocalDesejadoDTO localDesejadoDTO = localDesejadoMapper.toDto(localDesejado);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLocalDesejadoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(localDesejadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LocalDesejado in the database
        List<LocalDesejado> localDesejadoList = localDesejadoRepository.findAll();
        assertThat(localDesejadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamLocalDesejado() throws Exception {
        int databaseSizeBeforeUpdate = localDesejadoRepository.findAll().size();
        localDesejado.setId(count.incrementAndGet());

        // Create the LocalDesejado
        LocalDesejadoDTO localDesejadoDTO = localDesejadoMapper.toDto(localDesejado);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLocalDesejadoMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(localDesejadoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LocalDesejado in the database
        List<LocalDesejado> localDesejadoList = localDesejadoRepository.findAll();
        assertThat(localDesejadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateLocalDesejadoWithPatch() throws Exception {
        // Initialize the database
        localDesejadoRepository.saveAndFlush(localDesejado);

        int databaseSizeBeforeUpdate = localDesejadoRepository.findAll().size();

        // Update the localDesejado using partial update
        LocalDesejado partialUpdatedLocalDesejado = new LocalDesejado();
        partialUpdatedLocalDesejado.setId(localDesejado.getId());

        restLocalDesejadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLocalDesejado.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLocalDesejado))
            )
            .andExpect(status().isOk());

        // Validate the LocalDesejado in the database
        List<LocalDesejado> localDesejadoList = localDesejadoRepository.findAll();
        assertThat(localDesejadoList).hasSize(databaseSizeBeforeUpdate);
        LocalDesejado testLocalDesejado = localDesejadoList.get(localDesejadoList.size() - 1);
        assertThat(testLocalDesejado.getLocalCovid()).isEqualTo(DEFAULT_LOCAL_COVID);
    }

    @Test
    @Transactional
    void fullUpdateLocalDesejadoWithPatch() throws Exception {
        // Initialize the database
        localDesejadoRepository.saveAndFlush(localDesejado);

        int databaseSizeBeforeUpdate = localDesejadoRepository.findAll().size();

        // Update the localDesejado using partial update
        LocalDesejado partialUpdatedLocalDesejado = new LocalDesejado();
        partialUpdatedLocalDesejado.setId(localDesejado.getId());

        partialUpdatedLocalDesejado.localCovid(UPDATED_LOCAL_COVID);

        restLocalDesejadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedLocalDesejado.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedLocalDesejado))
            )
            .andExpect(status().isOk());

        // Validate the LocalDesejado in the database
        List<LocalDesejado> localDesejadoList = localDesejadoRepository.findAll();
        assertThat(localDesejadoList).hasSize(databaseSizeBeforeUpdate);
        LocalDesejado testLocalDesejado = localDesejadoList.get(localDesejadoList.size() - 1);
        assertThat(testLocalDesejado.getLocalCovid()).isEqualTo(UPDATED_LOCAL_COVID);
    }

    @Test
    @Transactional
    void patchNonExistingLocalDesejado() throws Exception {
        int databaseSizeBeforeUpdate = localDesejadoRepository.findAll().size();
        localDesejado.setId(count.incrementAndGet());

        // Create the LocalDesejado
        LocalDesejadoDTO localDesejadoDTO = localDesejadoMapper.toDto(localDesejado);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLocalDesejadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, localDesejadoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(localDesejadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LocalDesejado in the database
        List<LocalDesejado> localDesejadoList = localDesejadoRepository.findAll();
        assertThat(localDesejadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchLocalDesejado() throws Exception {
        int databaseSizeBeforeUpdate = localDesejadoRepository.findAll().size();
        localDesejado.setId(count.incrementAndGet());

        // Create the LocalDesejado
        LocalDesejadoDTO localDesejadoDTO = localDesejadoMapper.toDto(localDesejado);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLocalDesejadoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(localDesejadoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the LocalDesejado in the database
        List<LocalDesejado> localDesejadoList = localDesejadoRepository.findAll();
        assertThat(localDesejadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamLocalDesejado() throws Exception {
        int databaseSizeBeforeUpdate = localDesejadoRepository.findAll().size();
        localDesejado.setId(count.incrementAndGet());

        // Create the LocalDesejado
        LocalDesejadoDTO localDesejadoDTO = localDesejadoMapper.toDto(localDesejado);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restLocalDesejadoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(localDesejadoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the LocalDesejado in the database
        List<LocalDesejado> localDesejadoList = localDesejadoRepository.findAll();
        assertThat(localDesejadoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteLocalDesejado() throws Exception {
        // Initialize the database
        localDesejadoRepository.saveAndFlush(localDesejado);

        int databaseSizeBeforeDelete = localDesejadoRepository.findAll().size();

        // Delete the localDesejado
        restLocalDesejadoMockMvc
            .perform(delete(ENTITY_API_URL_ID, localDesejado.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LocalDesejado> localDesejadoList = localDesejadoRepository.findAll();
        assertThat(localDesejadoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
