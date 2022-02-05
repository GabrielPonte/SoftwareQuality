package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.TipoInformacao;
import com.mycompany.myapp.repository.TipoInformacaoRepository;
import com.mycompany.myapp.service.dto.TipoInformacaoDTO;
import com.mycompany.myapp.service.mapper.TipoInformacaoMapper;
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
 * Integration tests for the {@link TipoInformacaoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class TipoInformacaoResourceIT {

    private static final String DEFAULT_TIPO_INFO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_INFO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/tipo-informacaos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private TipoInformacaoRepository tipoInformacaoRepository;

    @Autowired
    private TipoInformacaoMapper tipoInformacaoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTipoInformacaoMockMvc;

    private TipoInformacao tipoInformacao;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoInformacao createEntity(EntityManager em) {
        TipoInformacao tipoInformacao = new TipoInformacao().tipoInfo(DEFAULT_TIPO_INFO);
        return tipoInformacao;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TipoInformacao createUpdatedEntity(EntityManager em) {
        TipoInformacao tipoInformacao = new TipoInformacao().tipoInfo(UPDATED_TIPO_INFO);
        return tipoInformacao;
    }

    @BeforeEach
    public void initTest() {
        tipoInformacao = createEntity(em);
    }

    @Test
    @Transactional
    void createTipoInformacao() throws Exception {
        int databaseSizeBeforeCreate = tipoInformacaoRepository.findAll().size();
        // Create the TipoInformacao
        TipoInformacaoDTO tipoInformacaoDTO = tipoInformacaoMapper.toDto(tipoInformacao);
        restTipoInformacaoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tipoInformacaoDTO))
            )
            .andExpect(status().isCreated());

        // Validate the TipoInformacao in the database
        List<TipoInformacao> tipoInformacaoList = tipoInformacaoRepository.findAll();
        assertThat(tipoInformacaoList).hasSize(databaseSizeBeforeCreate + 1);
        TipoInformacao testTipoInformacao = tipoInformacaoList.get(tipoInformacaoList.size() - 1);
        assertThat(testTipoInformacao.getTipoInfo()).isEqualTo(DEFAULT_TIPO_INFO);
    }

    @Test
    @Transactional
    void createTipoInformacaoWithExistingId() throws Exception {
        // Create the TipoInformacao with an existing ID
        tipoInformacao.setId(1L);
        TipoInformacaoDTO tipoInformacaoDTO = tipoInformacaoMapper.toDto(tipoInformacao);

        int databaseSizeBeforeCreate = tipoInformacaoRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restTipoInformacaoMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tipoInformacaoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TipoInformacao in the database
        List<TipoInformacao> tipoInformacaoList = tipoInformacaoRepository.findAll();
        assertThat(tipoInformacaoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllTipoInformacaos() throws Exception {
        // Initialize the database
        tipoInformacaoRepository.saveAndFlush(tipoInformacao);

        // Get all the tipoInformacaoList
        restTipoInformacaoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tipoInformacao.getId().intValue())))
            .andExpect(jsonPath("$.[*].tipoInfo").value(hasItem(DEFAULT_TIPO_INFO)));
    }

    @Test
    @Transactional
    void getTipoInformacao() throws Exception {
        // Initialize the database
        tipoInformacaoRepository.saveAndFlush(tipoInformacao);

        // Get the tipoInformacao
        restTipoInformacaoMockMvc
            .perform(get(ENTITY_API_URL_ID, tipoInformacao.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(tipoInformacao.getId().intValue()))
            .andExpect(jsonPath("$.tipoInfo").value(DEFAULT_TIPO_INFO));
    }

    @Test
    @Transactional
    void getNonExistingTipoInformacao() throws Exception {
        // Get the tipoInformacao
        restTipoInformacaoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewTipoInformacao() throws Exception {
        // Initialize the database
        tipoInformacaoRepository.saveAndFlush(tipoInformacao);

        int databaseSizeBeforeUpdate = tipoInformacaoRepository.findAll().size();

        // Update the tipoInformacao
        TipoInformacao updatedTipoInformacao = tipoInformacaoRepository.findById(tipoInformacao.getId()).get();
        // Disconnect from session so that the updates on updatedTipoInformacao are not directly saved in db
        em.detach(updatedTipoInformacao);
        updatedTipoInformacao.tipoInfo(UPDATED_TIPO_INFO);
        TipoInformacaoDTO tipoInformacaoDTO = tipoInformacaoMapper.toDto(updatedTipoInformacao);

        restTipoInformacaoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tipoInformacaoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tipoInformacaoDTO))
            )
            .andExpect(status().isOk());

        // Validate the TipoInformacao in the database
        List<TipoInformacao> tipoInformacaoList = tipoInformacaoRepository.findAll();
        assertThat(tipoInformacaoList).hasSize(databaseSizeBeforeUpdate);
        TipoInformacao testTipoInformacao = tipoInformacaoList.get(tipoInformacaoList.size() - 1);
        assertThat(testTipoInformacao.getTipoInfo()).isEqualTo(UPDATED_TIPO_INFO);
    }

    @Test
    @Transactional
    void putNonExistingTipoInformacao() throws Exception {
        int databaseSizeBeforeUpdate = tipoInformacaoRepository.findAll().size();
        tipoInformacao.setId(count.incrementAndGet());

        // Create the TipoInformacao
        TipoInformacaoDTO tipoInformacaoDTO = tipoInformacaoMapper.toDto(tipoInformacao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoInformacaoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, tipoInformacaoDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tipoInformacaoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TipoInformacao in the database
        List<TipoInformacao> tipoInformacaoList = tipoInformacaoRepository.findAll();
        assertThat(tipoInformacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchTipoInformacao() throws Exception {
        int databaseSizeBeforeUpdate = tipoInformacaoRepository.findAll().size();
        tipoInformacao.setId(count.incrementAndGet());

        // Create the TipoInformacao
        TipoInformacaoDTO tipoInformacaoDTO = tipoInformacaoMapper.toDto(tipoInformacao);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTipoInformacaoMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(tipoInformacaoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TipoInformacao in the database
        List<TipoInformacao> tipoInformacaoList = tipoInformacaoRepository.findAll();
        assertThat(tipoInformacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamTipoInformacao() throws Exception {
        int databaseSizeBeforeUpdate = tipoInformacaoRepository.findAll().size();
        tipoInformacao.setId(count.incrementAndGet());

        // Create the TipoInformacao
        TipoInformacaoDTO tipoInformacaoDTO = tipoInformacaoMapper.toDto(tipoInformacao);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTipoInformacaoMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(tipoInformacaoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TipoInformacao in the database
        List<TipoInformacao> tipoInformacaoList = tipoInformacaoRepository.findAll();
        assertThat(tipoInformacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateTipoInformacaoWithPatch() throws Exception {
        // Initialize the database
        tipoInformacaoRepository.saveAndFlush(tipoInformacao);

        int databaseSizeBeforeUpdate = tipoInformacaoRepository.findAll().size();

        // Update the tipoInformacao using partial update
        TipoInformacao partialUpdatedTipoInformacao = new TipoInformacao();
        partialUpdatedTipoInformacao.setId(tipoInformacao.getId());

        partialUpdatedTipoInformacao.tipoInfo(UPDATED_TIPO_INFO);

        restTipoInformacaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTipoInformacao.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTipoInformacao))
            )
            .andExpect(status().isOk());

        // Validate the TipoInformacao in the database
        List<TipoInformacao> tipoInformacaoList = tipoInformacaoRepository.findAll();
        assertThat(tipoInformacaoList).hasSize(databaseSizeBeforeUpdate);
        TipoInformacao testTipoInformacao = tipoInformacaoList.get(tipoInformacaoList.size() - 1);
        assertThat(testTipoInformacao.getTipoInfo()).isEqualTo(UPDATED_TIPO_INFO);
    }

    @Test
    @Transactional
    void fullUpdateTipoInformacaoWithPatch() throws Exception {
        // Initialize the database
        tipoInformacaoRepository.saveAndFlush(tipoInformacao);

        int databaseSizeBeforeUpdate = tipoInformacaoRepository.findAll().size();

        // Update the tipoInformacao using partial update
        TipoInformacao partialUpdatedTipoInformacao = new TipoInformacao();
        partialUpdatedTipoInformacao.setId(tipoInformacao.getId());

        partialUpdatedTipoInformacao.tipoInfo(UPDATED_TIPO_INFO);

        restTipoInformacaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedTipoInformacao.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedTipoInformacao))
            )
            .andExpect(status().isOk());

        // Validate the TipoInformacao in the database
        List<TipoInformacao> tipoInformacaoList = tipoInformacaoRepository.findAll();
        assertThat(tipoInformacaoList).hasSize(databaseSizeBeforeUpdate);
        TipoInformacao testTipoInformacao = tipoInformacaoList.get(tipoInformacaoList.size() - 1);
        assertThat(testTipoInformacao.getTipoInfo()).isEqualTo(UPDATED_TIPO_INFO);
    }

    @Test
    @Transactional
    void patchNonExistingTipoInformacao() throws Exception {
        int databaseSizeBeforeUpdate = tipoInformacaoRepository.findAll().size();
        tipoInformacao.setId(count.incrementAndGet());

        // Create the TipoInformacao
        TipoInformacaoDTO tipoInformacaoDTO = tipoInformacaoMapper.toDto(tipoInformacao);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTipoInformacaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, tipoInformacaoDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tipoInformacaoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TipoInformacao in the database
        List<TipoInformacao> tipoInformacaoList = tipoInformacaoRepository.findAll();
        assertThat(tipoInformacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchTipoInformacao() throws Exception {
        int databaseSizeBeforeUpdate = tipoInformacaoRepository.findAll().size();
        tipoInformacao.setId(count.incrementAndGet());

        // Create the TipoInformacao
        TipoInformacaoDTO tipoInformacaoDTO = tipoInformacaoMapper.toDto(tipoInformacao);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTipoInformacaoMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tipoInformacaoDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the TipoInformacao in the database
        List<TipoInformacao> tipoInformacaoList = tipoInformacaoRepository.findAll();
        assertThat(tipoInformacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamTipoInformacao() throws Exception {
        int databaseSizeBeforeUpdate = tipoInformacaoRepository.findAll().size();
        tipoInformacao.setId(count.incrementAndGet());

        // Create the TipoInformacao
        TipoInformacaoDTO tipoInformacaoDTO = tipoInformacaoMapper.toDto(tipoInformacao);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restTipoInformacaoMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(tipoInformacaoDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the TipoInformacao in the database
        List<TipoInformacao> tipoInformacaoList = tipoInformacaoRepository.findAll();
        assertThat(tipoInformacaoList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteTipoInformacao() throws Exception {
        // Initialize the database
        tipoInformacaoRepository.saveAndFlush(tipoInformacao);

        int databaseSizeBeforeDelete = tipoInformacaoRepository.findAll().size();

        // Delete the tipoInformacao
        restTipoInformacaoMockMvc
            .perform(delete(ENTITY_API_URL_ID, tipoInformacao.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TipoInformacao> tipoInformacaoList = tipoInformacaoRepository.findAll();
        assertThat(tipoInformacaoList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
