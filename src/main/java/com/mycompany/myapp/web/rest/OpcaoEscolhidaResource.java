package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.OpcaoEscolhidaRepository;
import com.mycompany.myapp.service.OpcaoEscolhidaService;
import com.mycompany.myapp.service.dto.OpcaoEscolhidaDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.OpcaoEscolhida}.
 */
@RestController
@RequestMapping("/api")
public class OpcaoEscolhidaResource {

    private final Logger log = LoggerFactory.getLogger(OpcaoEscolhidaResource.class);

    private static final String ENTITY_NAME = "opcaoEscolhida";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OpcaoEscolhidaService opcaoEscolhidaService;

    private final OpcaoEscolhidaRepository opcaoEscolhidaRepository;

    public OpcaoEscolhidaResource(OpcaoEscolhidaService opcaoEscolhidaService, OpcaoEscolhidaRepository opcaoEscolhidaRepository) {
        this.opcaoEscolhidaService = opcaoEscolhidaService;
        this.opcaoEscolhidaRepository = opcaoEscolhidaRepository;
    }

    /**
     * {@code POST  /opcao-escolhidas} : Create a new opcaoEscolhida.
     *
     * @param opcaoEscolhidaDTO the opcaoEscolhidaDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new opcaoEscolhidaDTO, or with status {@code 400 (Bad Request)} if the opcaoEscolhida has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/opcao-escolhidas")
    public ResponseEntity<OpcaoEscolhidaDTO> createOpcaoEscolhida(@RequestBody OpcaoEscolhidaDTO opcaoEscolhidaDTO)
        throws URISyntaxException {
        log.debug("REST request to save OpcaoEscolhida : {}", opcaoEscolhidaDTO);
        if (opcaoEscolhidaDTO.getId() != null) {
            throw new BadRequestAlertException("A new opcaoEscolhida cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OpcaoEscolhidaDTO result = opcaoEscolhidaService.save(opcaoEscolhidaDTO);
        return ResponseEntity
            .created(new URI("/api/opcao-escolhidas/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /opcao-escolhidas/:id} : Updates an existing opcaoEscolhida.
     *
     * @param id the id of the opcaoEscolhidaDTO to save.
     * @param opcaoEscolhidaDTO the opcaoEscolhidaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated opcaoEscolhidaDTO,
     * or with status {@code 400 (Bad Request)} if the opcaoEscolhidaDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the opcaoEscolhidaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/opcao-escolhidas/{id}")
    public ResponseEntity<OpcaoEscolhidaDTO> updateOpcaoEscolhida(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OpcaoEscolhidaDTO opcaoEscolhidaDTO
    ) throws URISyntaxException {
        log.debug("REST request to update OpcaoEscolhida : {}, {}", id, opcaoEscolhidaDTO);
        if (opcaoEscolhidaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, opcaoEscolhidaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!opcaoEscolhidaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OpcaoEscolhidaDTO result = opcaoEscolhidaService.save(opcaoEscolhidaDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, opcaoEscolhidaDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /opcao-escolhidas/:id} : Partial updates given fields of an existing opcaoEscolhida, field will ignore if it is null
     *
     * @param id the id of the opcaoEscolhidaDTO to save.
     * @param opcaoEscolhidaDTO the opcaoEscolhidaDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated opcaoEscolhidaDTO,
     * or with status {@code 400 (Bad Request)} if the opcaoEscolhidaDTO is not valid,
     * or with status {@code 404 (Not Found)} if the opcaoEscolhidaDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the opcaoEscolhidaDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/opcao-escolhidas/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<OpcaoEscolhidaDTO> partialUpdateOpcaoEscolhida(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OpcaoEscolhidaDTO opcaoEscolhidaDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update OpcaoEscolhida partially : {}, {}", id, opcaoEscolhidaDTO);
        if (opcaoEscolhidaDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, opcaoEscolhidaDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!opcaoEscolhidaRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OpcaoEscolhidaDTO> result = opcaoEscolhidaService.partialUpdate(opcaoEscolhidaDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, opcaoEscolhidaDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /opcao-escolhidas} : get all the opcaoEscolhidas.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of opcaoEscolhidas in body.
     */
    @GetMapping("/opcao-escolhidas")
    public List<OpcaoEscolhidaDTO> getAllOpcaoEscolhidas() {
        log.debug("REST request to get all OpcaoEscolhidas");
        return opcaoEscolhidaService.findAll();
    }

    /**
     * {@code GET  /opcao-escolhidas/:id} : get the "id" opcaoEscolhida.
     *
     * @param id the id of the opcaoEscolhidaDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the opcaoEscolhidaDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/opcao-escolhidas/{id}")
    public ResponseEntity<OpcaoEscolhidaDTO> getOpcaoEscolhida(@PathVariable Long id) {
        log.debug("REST request to get OpcaoEscolhida : {}", id);
        Optional<OpcaoEscolhidaDTO> opcaoEscolhidaDTO = opcaoEscolhidaService.findOne(id);
        return ResponseUtil.wrapOrNotFound(opcaoEscolhidaDTO);
    }

    /**
     * {@code DELETE  /opcao-escolhidas/:id} : delete the "id" opcaoEscolhida.
     *
     * @param id the id of the opcaoEscolhidaDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/opcao-escolhidas/{id}")
    public ResponseEntity<Void> deleteOpcaoEscolhida(@PathVariable Long id) {
        log.debug("REST request to delete OpcaoEscolhida : {}", id);
        opcaoEscolhidaService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
