package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.PostoSaudeRepository;
import com.mycompany.myapp.service.PostoSaudeService;
import com.mycompany.myapp.service.dto.PostoSaudeDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.PostoSaude}.
 */
@RestController
@RequestMapping("/api")
public class PostoSaudeResource {

    private final Logger log = LoggerFactory.getLogger(PostoSaudeResource.class);

    private static final String ENTITY_NAME = "postoSaude";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PostoSaudeService postoSaudeService;

    private final PostoSaudeRepository postoSaudeRepository;

    public PostoSaudeResource(PostoSaudeService postoSaudeService, PostoSaudeRepository postoSaudeRepository) {
        this.postoSaudeService = postoSaudeService;
        this.postoSaudeRepository = postoSaudeRepository;
    }

    /**
     * {@code POST  /posto-saudes} : Create a new postoSaude.
     *
     * @param postoSaudeDTO the postoSaudeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new postoSaudeDTO, or with status {@code 400 (Bad Request)} if the postoSaude has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/posto-saudes")
    public ResponseEntity<PostoSaudeDTO> createPostoSaude(@RequestBody PostoSaudeDTO postoSaudeDTO) throws URISyntaxException {
        log.debug("REST request to save PostoSaude : {}", postoSaudeDTO);
        if (postoSaudeDTO.getId() != null) {
            throw new BadRequestAlertException("A new postoSaude cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PostoSaudeDTO result = postoSaudeService.save(postoSaudeDTO);
        return ResponseEntity
            .created(new URI("/api/posto-saudes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /posto-saudes/:id} : Updates an existing postoSaude.
     *
     * @param id the id of the postoSaudeDTO to save.
     * @param postoSaudeDTO the postoSaudeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated postoSaudeDTO,
     * or with status {@code 400 (Bad Request)} if the postoSaudeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the postoSaudeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/posto-saudes/{id}")
    public ResponseEntity<PostoSaudeDTO> updatePostoSaude(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PostoSaudeDTO postoSaudeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PostoSaude : {}, {}", id, postoSaudeDTO);
        if (postoSaudeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, postoSaudeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!postoSaudeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PostoSaudeDTO result = postoSaudeService.save(postoSaudeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, postoSaudeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /posto-saudes/:id} : Partial updates given fields of an existing postoSaude, field will ignore if it is null
     *
     * @param id the id of the postoSaudeDTO to save.
     * @param postoSaudeDTO the postoSaudeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated postoSaudeDTO,
     * or with status {@code 400 (Bad Request)} if the postoSaudeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the postoSaudeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the postoSaudeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/posto-saudes/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<PostoSaudeDTO> partialUpdatePostoSaude(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PostoSaudeDTO postoSaudeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PostoSaude partially : {}, {}", id, postoSaudeDTO);
        if (postoSaudeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, postoSaudeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!postoSaudeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PostoSaudeDTO> result = postoSaudeService.partialUpdate(postoSaudeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, postoSaudeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /posto-saudes} : get all the postoSaudes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of postoSaudes in body.
     */
    @GetMapping("/posto-saudes")
    public List<PostoSaudeDTO> getAllPostoSaudes() {
        log.debug("REST request to get all PostoSaudes");
        return postoSaudeService.findAll();
    }

    /**
     * {@code GET  /posto-saudes/:id} : get the "id" postoSaude.
     *
     * @param id the id of the postoSaudeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the postoSaudeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/posto-saudes/{id}")
    public ResponseEntity<PostoSaudeDTO> getPostoSaude(@PathVariable Long id) {
        log.debug("REST request to get PostoSaude : {}", id);
        Optional<PostoSaudeDTO> postoSaudeDTO = postoSaudeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(postoSaudeDTO);
    }

    /**
     * {@code DELETE  /posto-saudes/:id} : delete the "id" postoSaude.
     *
     * @param id the id of the postoSaudeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/posto-saudes/{id}")
    public ResponseEntity<Void> deletePostoSaude(@PathVariable Long id) {
        log.debug("REST request to delete PostoSaude : {}", id);
        postoSaudeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
