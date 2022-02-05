package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TipoInformacaoRepository;
import com.mycompany.myapp.service.TipoInformacaoService;
import com.mycompany.myapp.service.dto.TipoInformacaoDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.TipoInformacao}.
 */
@RestController
@RequestMapping("/api")
public class TipoInformacaoResource {

    private final Logger log = LoggerFactory.getLogger(TipoInformacaoResource.class);

    private static final String ENTITY_NAME = "tipoInformacao";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TipoInformacaoService tipoInformacaoService;

    private final TipoInformacaoRepository tipoInformacaoRepository;

    public TipoInformacaoResource(TipoInformacaoService tipoInformacaoService, TipoInformacaoRepository tipoInformacaoRepository) {
        this.tipoInformacaoService = tipoInformacaoService;
        this.tipoInformacaoRepository = tipoInformacaoRepository;
    }

    /**
     * {@code POST  /tipo-informacaos} : Create a new tipoInformacao.
     *
     * @param tipoInformacaoDTO the tipoInformacaoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tipoInformacaoDTO, or with status {@code 400 (Bad Request)} if the tipoInformacao has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tipo-informacaos")
    public ResponseEntity<TipoInformacaoDTO> createTipoInformacao(@RequestBody TipoInformacaoDTO tipoInformacaoDTO)
        throws URISyntaxException {
        log.debug("REST request to save TipoInformacao : {}", tipoInformacaoDTO);
        if (tipoInformacaoDTO.getId() != null) {
            throw new BadRequestAlertException("A new tipoInformacao cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TipoInformacaoDTO result = tipoInformacaoService.save(tipoInformacaoDTO);
        return ResponseEntity
            .created(new URI("/api/tipo-informacaos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tipo-informacaos/:id} : Updates an existing tipoInformacao.
     *
     * @param id the id of the tipoInformacaoDTO to save.
     * @param tipoInformacaoDTO the tipoInformacaoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoInformacaoDTO,
     * or with status {@code 400 (Bad Request)} if the tipoInformacaoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tipoInformacaoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tipo-informacaos/{id}")
    public ResponseEntity<TipoInformacaoDTO> updateTipoInformacao(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TipoInformacaoDTO tipoInformacaoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TipoInformacao : {}, {}", id, tipoInformacaoDTO);
        if (tipoInformacaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tipoInformacaoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tipoInformacaoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TipoInformacaoDTO result = tipoInformacaoService.save(tipoInformacaoDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoInformacaoDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /tipo-informacaos/:id} : Partial updates given fields of an existing tipoInformacao, field will ignore if it is null
     *
     * @param id the id of the tipoInformacaoDTO to save.
     * @param tipoInformacaoDTO the tipoInformacaoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tipoInformacaoDTO,
     * or with status {@code 400 (Bad Request)} if the tipoInformacaoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tipoInformacaoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tipoInformacaoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/tipo-informacaos/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<TipoInformacaoDTO> partialUpdateTipoInformacao(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TipoInformacaoDTO tipoInformacaoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TipoInformacao partially : {}, {}", id, tipoInformacaoDTO);
        if (tipoInformacaoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tipoInformacaoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tipoInformacaoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TipoInformacaoDTO> result = tipoInformacaoService.partialUpdate(tipoInformacaoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tipoInformacaoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /tipo-informacaos} : get all the tipoInformacaos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tipoInformacaos in body.
     */
    @GetMapping("/tipo-informacaos")
    public List<TipoInformacaoDTO> getAllTipoInformacaos() {
        log.debug("REST request to get all TipoInformacaos");
        return tipoInformacaoService.findAll();
    }

    /**
     * {@code GET  /tipo-informacaos/:id} : get the "id" tipoInformacao.
     *
     * @param id the id of the tipoInformacaoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tipoInformacaoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tipo-informacaos/{id}")
    public ResponseEntity<TipoInformacaoDTO> getTipoInformacao(@PathVariable Long id) {
        log.debug("REST request to get TipoInformacao : {}", id);
        Optional<TipoInformacaoDTO> tipoInformacaoDTO = tipoInformacaoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tipoInformacaoDTO);
    }

    /**
     * {@code DELETE  /tipo-informacaos/:id} : delete the "id" tipoInformacao.
     *
     * @param id the id of the tipoInformacaoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tipo-informacaos/{id}")
    public ResponseEntity<Void> deleteTipoInformacao(@PathVariable Long id) {
        log.debug("REST request to delete TipoInformacao : {}", id);
        tipoInformacaoService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
