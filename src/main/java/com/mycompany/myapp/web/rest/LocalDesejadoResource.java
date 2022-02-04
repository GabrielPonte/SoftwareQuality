package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.LocalDesejadoRepository;
import com.mycompany.myapp.service.LocalDesejadoService;
import com.mycompany.myapp.service.dto.LocalDesejadoDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.LocalDesejado}.
 */
@RestController
@RequestMapping("/api")
public class LocalDesejadoResource {

    private final Logger log = LoggerFactory.getLogger(LocalDesejadoResource.class);

    private static final String ENTITY_NAME = "localDesejado";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LocalDesejadoService localDesejadoService;

    private final LocalDesejadoRepository localDesejadoRepository;

    public LocalDesejadoResource(LocalDesejadoService localDesejadoService, LocalDesejadoRepository localDesejadoRepository) {
        this.localDesejadoService = localDesejadoService;
        this.localDesejadoRepository = localDesejadoRepository;
    }

    /**
     * {@code POST  /local-desejados} : Create a new localDesejado.
     *
     * @param localDesejadoDTO the localDesejadoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new localDesejadoDTO, or with status {@code 400 (Bad Request)} if the localDesejado has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/local-desejados")
    public ResponseEntity<LocalDesejadoDTO> createLocalDesejado(@RequestBody LocalDesejadoDTO localDesejadoDTO) throws URISyntaxException {
        log.debug("REST request to save LocalDesejado : {}", localDesejadoDTO);
        if (localDesejadoDTO.getId() != null) {
            throw new BadRequestAlertException("A new localDesejado cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LocalDesejadoDTO result = localDesejadoService.save(localDesejadoDTO);
        return ResponseEntity
            .created(new URI("/api/local-desejados/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /local-desejados/:id} : Updates an existing localDesejado.
     *
     * @param id the id of the localDesejadoDTO to save.
     * @param localDesejadoDTO the localDesejadoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated localDesejadoDTO,
     * or with status {@code 400 (Bad Request)} if the localDesejadoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the localDesejadoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/local-desejados/{id}")
    public ResponseEntity<LocalDesejadoDTO> updateLocalDesejado(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LocalDesejadoDTO localDesejadoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update LocalDesejado : {}, {}", id, localDesejadoDTO);
        if (localDesejadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, localDesejadoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!localDesejadoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LocalDesejadoDTO result = localDesejadoService.save(localDesejadoDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, localDesejadoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /local-desejados/:id} : Partial updates given fields of an existing localDesejado, field will ignore if it is null
     *
     * @param id the id of the localDesejadoDTO to save.
     * @param localDesejadoDTO the localDesejadoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated localDesejadoDTO,
     * or with status {@code 400 (Bad Request)} if the localDesejadoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the localDesejadoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the localDesejadoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/local-desejados/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<LocalDesejadoDTO> partialUpdateLocalDesejado(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LocalDesejadoDTO localDesejadoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update LocalDesejado partially : {}, {}", id, localDesejadoDTO);
        if (localDesejadoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, localDesejadoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!localDesejadoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LocalDesejadoDTO> result = localDesejadoService.partialUpdate(localDesejadoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, localDesejadoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /local-desejados} : get all the localDesejados.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of localDesejados in body.
     */
    @GetMapping("/local-desejados")
    public List<LocalDesejadoDTO> getAllLocalDesejados() {
        log.debug("REST request to get all LocalDesejados");
        return localDesejadoService.findAll();
    }

    /**
     * {@code GET  /local-desejados/:id} : get the "id" localDesejado.
     *
     * @param id the id of the localDesejadoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the localDesejadoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/local-desejados/{id}")
    public ResponseEntity<LocalDesejadoDTO> getLocalDesejado(@PathVariable Long id) {
        log.debug("REST request to get LocalDesejado : {}", id);
        Optional<LocalDesejadoDTO> localDesejadoDTO = localDesejadoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(localDesejadoDTO);
    }

    /**
     * {@code DELETE  /local-desejados/:id} : delete the "id" localDesejado.
     *
     * @param id the id of the localDesejadoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/local-desejados/{id}")
    public ResponseEntity<Void> deleteLocalDesejado(@PathVariable Long id) {
        log.debug("REST request to delete LocalDesejado : {}", id);
        localDesejadoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
