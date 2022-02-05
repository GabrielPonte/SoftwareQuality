package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.PostoSaude;
import com.mycompany.myapp.repository.PostoSaudeRepository;
import com.mycompany.myapp.service.dto.PostoSaudeDTO;
import com.mycompany.myapp.service.mapper.PostoSaudeMapper;
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
 * Integration tests for the {@link PostoSaudeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class PostoSaudeResourceIT {

    private static final String DEFAULT_NOME_POSTO = "AAAAAAAAAA";
    private static final String UPDATED_NOME_POSTO = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO_POSTO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO_POSTO = "BBBBBBBBBB";

    private static final String DEFAULT_CIDADE_POSTO = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE_POSTO = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/posto-saudes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private PostoSaudeRepository postoSaudeRepository;

    @Autowired
    private PostoSaudeMapper postoSaudeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPostoSaudeMockMvc;

    private PostoSaude postoSaude;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PostoSaude createEntity(EntityManager em) {
        PostoSaude postoSaude = new PostoSaude()
            .nomePosto(DEFAULT_NOME_POSTO)
            .estadoPosto(DEFAULT_ESTADO_POSTO)
            .cidadePosto(DEFAULT_CIDADE_POSTO);
        return postoSaude;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PostoSaude createUpdatedEntity(EntityManager em) {
        PostoSaude postoSaude = new PostoSaude()
            .nomePosto(UPDATED_NOME_POSTO)
            .estadoPosto(UPDATED_ESTADO_POSTO)
            .cidadePosto(UPDATED_CIDADE_POSTO);
        return postoSaude;
    }

    @BeforeEach
    public void initTest() {
        postoSaude = createEntity(em);
    }

    @Test
    @Transactional
    void createPostoSaude() throws Exception {
        int databaseSizeBeforeCreate = postoSaudeRepository.findAll().size();
        // Create the PostoSaude
        PostoSaudeDTO postoSaudeDTO = postoSaudeMapper.toDto(postoSaude);
        restPostoSaudeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postoSaudeDTO)))
            .andExpect(status().isCreated());

        // Validate the PostoSaude in the database
        List<PostoSaude> postoSaudeList = postoSaudeRepository.findAll();
        assertThat(postoSaudeList).hasSize(databaseSizeBeforeCreate + 1);
        PostoSaude testPostoSaude = postoSaudeList.get(postoSaudeList.size() - 1);
        assertThat(testPostoSaude.getNomePosto()).isEqualTo(DEFAULT_NOME_POSTO);
        assertThat(testPostoSaude.getEstadoPosto()).isEqualTo(DEFAULT_ESTADO_POSTO);
        assertThat(testPostoSaude.getCidadePosto()).isEqualTo(DEFAULT_CIDADE_POSTO);
    }

    @Test
    @Transactional
    void createPostoSaudeWithExistingId() throws Exception {
        // Create the PostoSaude with an existing ID
        postoSaude.setId(1L);
        PostoSaudeDTO postoSaudeDTO = postoSaudeMapper.toDto(postoSaude);

        int databaseSizeBeforeCreate = postoSaudeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restPostoSaudeMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postoSaudeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PostoSaude in the database
        List<PostoSaude> postoSaudeList = postoSaudeRepository.findAll();
        assertThat(postoSaudeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllPostoSaudes() throws Exception {
        // Initialize the database
        postoSaudeRepository.saveAndFlush(postoSaude);

        // Get all the postoSaudeList
        restPostoSaudeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(postoSaude.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomePosto").value(hasItem(DEFAULT_NOME_POSTO)))
            .andExpect(jsonPath("$.[*].estadoPosto").value(hasItem(DEFAULT_ESTADO_POSTO)))
            .andExpect(jsonPath("$.[*].cidadePosto").value(hasItem(DEFAULT_CIDADE_POSTO)));
    }

    @Test
    @Transactional
    void getPostoSaude() throws Exception {
        // Initialize the database
        postoSaudeRepository.saveAndFlush(postoSaude);

        // Get the postoSaude
        restPostoSaudeMockMvc
            .perform(get(ENTITY_API_URL_ID, postoSaude.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(postoSaude.getId().intValue()))
            .andExpect(jsonPath("$.nomePosto").value(DEFAULT_NOME_POSTO))
            .andExpect(jsonPath("$.estadoPosto").value(DEFAULT_ESTADO_POSTO))
            .andExpect(jsonPath("$.cidadePosto").value(DEFAULT_CIDADE_POSTO));
    }

    @Test
    @Transactional
    void getNonExistingPostoSaude() throws Exception {
        // Get the postoSaude
        restPostoSaudeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putNewPostoSaude() throws Exception {
        // Initialize the database
        postoSaudeRepository.saveAndFlush(postoSaude);

        int databaseSizeBeforeUpdate = postoSaudeRepository.findAll().size();

        // Update the postoSaude
        PostoSaude updatedPostoSaude = postoSaudeRepository.findById(postoSaude.getId()).get();
        // Disconnect from session so that the updates on updatedPostoSaude are not directly saved in db
        em.detach(updatedPostoSaude);
        updatedPostoSaude.nomePosto(UPDATED_NOME_POSTO).estadoPosto(UPDATED_ESTADO_POSTO).cidadePosto(UPDATED_CIDADE_POSTO);
        PostoSaudeDTO postoSaudeDTO = postoSaudeMapper.toDto(updatedPostoSaude);

        restPostoSaudeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, postoSaudeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(postoSaudeDTO))
            )
            .andExpect(status().isOk());

        // Validate the PostoSaude in the database
        List<PostoSaude> postoSaudeList = postoSaudeRepository.findAll();
        assertThat(postoSaudeList).hasSize(databaseSizeBeforeUpdate);
        PostoSaude testPostoSaude = postoSaudeList.get(postoSaudeList.size() - 1);
        assertThat(testPostoSaude.getNomePosto()).isEqualTo(UPDATED_NOME_POSTO);
        assertThat(testPostoSaude.getEstadoPosto()).isEqualTo(UPDATED_ESTADO_POSTO);
        assertThat(testPostoSaude.getCidadePosto()).isEqualTo(UPDATED_CIDADE_POSTO);
    }

    @Test
    @Transactional
    void putNonExistingPostoSaude() throws Exception {
        int databaseSizeBeforeUpdate = postoSaudeRepository.findAll().size();
        postoSaude.setId(count.incrementAndGet());

        // Create the PostoSaude
        PostoSaudeDTO postoSaudeDTO = postoSaudeMapper.toDto(postoSaude);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPostoSaudeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, postoSaudeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(postoSaudeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PostoSaude in the database
        List<PostoSaude> postoSaudeList = postoSaudeRepository.findAll();
        assertThat(postoSaudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchPostoSaude() throws Exception {
        int databaseSizeBeforeUpdate = postoSaudeRepository.findAll().size();
        postoSaude.setId(count.incrementAndGet());

        // Create the PostoSaude
        PostoSaudeDTO postoSaudeDTO = postoSaudeMapper.toDto(postoSaude);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPostoSaudeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(postoSaudeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PostoSaude in the database
        List<PostoSaude> postoSaudeList = postoSaudeRepository.findAll();
        assertThat(postoSaudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamPostoSaude() throws Exception {
        int databaseSizeBeforeUpdate = postoSaudeRepository.findAll().size();
        postoSaude.setId(count.incrementAndGet());

        // Create the PostoSaude
        PostoSaudeDTO postoSaudeDTO = postoSaudeMapper.toDto(postoSaude);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPostoSaudeMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(postoSaudeDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the PostoSaude in the database
        List<PostoSaude> postoSaudeList = postoSaudeRepository.findAll();
        assertThat(postoSaudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdatePostoSaudeWithPatch() throws Exception {
        // Initialize the database
        postoSaudeRepository.saveAndFlush(postoSaude);

        int databaseSizeBeforeUpdate = postoSaudeRepository.findAll().size();

        // Update the postoSaude using partial update
        PostoSaude partialUpdatedPostoSaude = new PostoSaude();
        partialUpdatedPostoSaude.setId(postoSaude.getId());

        partialUpdatedPostoSaude.nomePosto(UPDATED_NOME_POSTO).estadoPosto(UPDATED_ESTADO_POSTO);

        restPostoSaudeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPostoSaude.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPostoSaude))
            )
            .andExpect(status().isOk());

        // Validate the PostoSaude in the database
        List<PostoSaude> postoSaudeList = postoSaudeRepository.findAll();
        assertThat(postoSaudeList).hasSize(databaseSizeBeforeUpdate);
        PostoSaude testPostoSaude = postoSaudeList.get(postoSaudeList.size() - 1);
        assertThat(testPostoSaude.getNomePosto()).isEqualTo(UPDATED_NOME_POSTO);
        assertThat(testPostoSaude.getEstadoPosto()).isEqualTo(UPDATED_ESTADO_POSTO);
        assertThat(testPostoSaude.getCidadePosto()).isEqualTo(DEFAULT_CIDADE_POSTO);
    }

    @Test
    @Transactional
    void fullUpdatePostoSaudeWithPatch() throws Exception {
        // Initialize the database
        postoSaudeRepository.saveAndFlush(postoSaude);

        int databaseSizeBeforeUpdate = postoSaudeRepository.findAll().size();

        // Update the postoSaude using partial update
        PostoSaude partialUpdatedPostoSaude = new PostoSaude();
        partialUpdatedPostoSaude.setId(postoSaude.getId());

        partialUpdatedPostoSaude.nomePosto(UPDATED_NOME_POSTO).estadoPosto(UPDATED_ESTADO_POSTO).cidadePosto(UPDATED_CIDADE_POSTO);

        restPostoSaudeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedPostoSaude.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedPostoSaude))
            )
            .andExpect(status().isOk());

        // Validate the PostoSaude in the database
        List<PostoSaude> postoSaudeList = postoSaudeRepository.findAll();
        assertThat(postoSaudeList).hasSize(databaseSizeBeforeUpdate);
        PostoSaude testPostoSaude = postoSaudeList.get(postoSaudeList.size() - 1);
        assertThat(testPostoSaude.getNomePosto()).isEqualTo(UPDATED_NOME_POSTO);
        assertThat(testPostoSaude.getEstadoPosto()).isEqualTo(UPDATED_ESTADO_POSTO);
        assertThat(testPostoSaude.getCidadePosto()).isEqualTo(UPDATED_CIDADE_POSTO);
    }

    @Test
    @Transactional
    void patchNonExistingPostoSaude() throws Exception {
        int databaseSizeBeforeUpdate = postoSaudeRepository.findAll().size();
        postoSaude.setId(count.incrementAndGet());

        // Create the PostoSaude
        PostoSaudeDTO postoSaudeDTO = postoSaudeMapper.toDto(postoSaude);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPostoSaudeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, postoSaudeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(postoSaudeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PostoSaude in the database
        List<PostoSaude> postoSaudeList = postoSaudeRepository.findAll();
        assertThat(postoSaudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchPostoSaude() throws Exception {
        int databaseSizeBeforeUpdate = postoSaudeRepository.findAll().size();
        postoSaude.setId(count.incrementAndGet());

        // Create the PostoSaude
        PostoSaudeDTO postoSaudeDTO = postoSaudeMapper.toDto(postoSaude);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPostoSaudeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(postoSaudeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the PostoSaude in the database
        List<PostoSaude> postoSaudeList = postoSaudeRepository.findAll();
        assertThat(postoSaudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamPostoSaude() throws Exception {
        int databaseSizeBeforeUpdate = postoSaudeRepository.findAll().size();
        postoSaude.setId(count.incrementAndGet());

        // Create the PostoSaude
        PostoSaudeDTO postoSaudeDTO = postoSaudeMapper.toDto(postoSaude);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restPostoSaudeMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(postoSaudeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the PostoSaude in the database
        List<PostoSaude> postoSaudeList = postoSaudeRepository.findAll();
        assertThat(postoSaudeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deletePostoSaude() throws Exception {
        // Initialize the database
        postoSaudeRepository.saveAndFlush(postoSaude);

        int databaseSizeBeforeDelete = postoSaudeRepository.findAll().size();

        // Delete the postoSaude
        restPostoSaudeMockMvc
            .perform(delete(ENTITY_API_URL_ID, postoSaude.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PostoSaude> postoSaudeList = postoSaudeRepository.findAll();
        assertThat(postoSaudeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
