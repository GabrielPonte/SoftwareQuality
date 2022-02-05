package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.RecebaEmailRepository;
import com.mycompany.myapp.service.RecebaEmailService;
import com.mycompany.myapp.service.dto.RecebaEmailDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.RecebaEmail}.
 */
@RestController
@RequestMapping("/api")
public class RecebaEmailResource {

    private final Logger log = LoggerFactory.getLogger(RecebaEmailResource.class);

    private static final String ENTITY_NAME = "recebaEmail";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RecebaEmailService recebaEmailService;

    private final RecebaEmailRepository recebaEmailRepository;

    public RecebaEmailResource(RecebaEmailService recebaEmailService, RecebaEmailRepository recebaEmailRepository) {
        this.recebaEmailService = recebaEmailService;
        this.recebaEmailRepository = recebaEmailRepository;
    }

    /**
     * {@code POST  /receba-emails} : Create a new recebaEmail.
     *
     * @param recebaEmailDTO the recebaEmailDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new recebaEmailDTO, or with status {@code 400 (Bad Request)} if the recebaEmail has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/receba-emails")
    public ResponseEntity<RecebaEmailDTO> createRecebaEmail(@RequestBody RecebaEmailDTO recebaEmailDTO) throws URISyntaxException {
        log.debug("REST request to save RecebaEmail : {}", recebaEmailDTO);
        if (recebaEmailDTO.getId() != null) {
            throw new BadRequestAlertException("A new recebaEmail cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RecebaEmailDTO result = recebaEmailService.save(recebaEmailDTO);
        return ResponseEntity
            .created(new URI("/api/receba-emails/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /receba-emails/:id} : Updates an existing recebaEmail.
     *
     * @param id the id of the recebaEmailDTO to save.
     * @param recebaEmailDTO the recebaEmailDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recebaEmailDTO,
     * or with status {@code 400 (Bad Request)} if the recebaEmailDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the recebaEmailDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/receba-emails/{id}")
    public ResponseEntity<RecebaEmailDTO> updateRecebaEmail(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RecebaEmailDTO recebaEmailDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RecebaEmail : {}, {}", id, recebaEmailDTO);
        if (recebaEmailDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, recebaEmailDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recebaEmailRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RecebaEmailDTO result = recebaEmailService.save(recebaEmailDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recebaEmailDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /receba-emails/:id} : Partial updates given fields of an existing recebaEmail, field will ignore if it is null
     *
     * @param id the id of the recebaEmailDTO to save.
     * @param recebaEmailDTO the recebaEmailDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated recebaEmailDTO,
     * or with status {@code 400 (Bad Request)} if the recebaEmailDTO is not valid,
     * or with status {@code 404 (Not Found)} if the recebaEmailDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the recebaEmailDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/receba-emails/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<RecebaEmailDTO> partialUpdateRecebaEmail(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RecebaEmailDTO recebaEmailDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RecebaEmail partially : {}, {}", id, recebaEmailDTO);
        if (recebaEmailDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, recebaEmailDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!recebaEmailRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RecebaEmailDTO> result = recebaEmailService.partialUpdate(recebaEmailDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, recebaEmailDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /receba-emails} : get all the recebaEmails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of recebaEmails in body.
     */
    @GetMapping("/receba-emails")
    public List<RecebaEmailDTO> getAllRecebaEmails() {
        log.debug("REST request to get all RecebaEmails");
        return recebaEmailService.findAll();
    }

    /**
     * {@code GET  /receba-emails/:id} : get the "id" recebaEmail.
     *
     * @param id the id of the recebaEmailDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the recebaEmailDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/receba-emails/{id}")
    public ResponseEntity<RecebaEmailDTO> getRecebaEmail(@PathVariable Long id) {
        log.debug("REST request to get RecebaEmail : {}", id);
        Optional<RecebaEmailDTO> recebaEmailDTO = recebaEmailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(recebaEmailDTO);
    }

    /**
     * {@code DELETE  /receba-emails/:id} : delete the "id" recebaEmail.
     *
     * @param id the id of the recebaEmailDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/receba-emails/{id}")
    public ResponseEntity<Void> deleteRecebaEmail(@PathVariable Long id) {
        log.debug("REST request to delete RecebaEmail : {}", id);
        recebaEmailService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
