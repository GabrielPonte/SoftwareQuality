package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.InformacoesProcessService;
import com.mycompany.myapp.service.dto.InformacoesProcessDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.InformacoesProcess}.
 */
@RestController
@RequestMapping("/api")
public class InformacoesProcessResource {

    private final Logger log = LoggerFactory.getLogger(InformacoesProcessResource.class);

    private static final String ENTITY_NAME = "informacoesProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InformacoesProcessService informacoesProcessService;

    public InformacoesProcessResource(InformacoesProcessService informacoesProcessService) {
        this.informacoesProcessService = informacoesProcessService;
    }

    /**
     * {@code POST  /informacoes-processes} : Create a new informacoesProcess.
     *
     * @param informacoesProcessDTO the informacoesProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new informacoesProcessDTO, or with status {@code 400 (Bad Request)} if the informacoesProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/informacoes-processes")
    public ResponseEntity<InformacoesProcessDTO> create(@RequestBody InformacoesProcessDTO informacoesProcessDTO)
        throws URISyntaxException {
        log.debug("REST request to save InformacoesProcess : {}", informacoesProcessDTO);
        if (informacoesProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new informacoesProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InformacoesProcessDTO result = informacoesProcessService.create(informacoesProcessDTO);
        return ResponseEntity
            .created(new URI("/api/informacoes-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /informacoes-processes} : get all the informacoesProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of informacoesProcesss in body.
     */
    @GetMapping("/informacoes-processes")
    public List<InformacoesProcessDTO> getAllInformacoesProcesss() {
        log.debug("REST request to get all InformacoesProcesss");
        return informacoesProcessService.findAll();
    }

    /**
     * {@code GET  /informacoes-processes/:id} : get the "id" informacoesProcess.
     *
     * @param processInstanceId the id of the informacoesProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the informacoesProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/informacoes-processes/{processInstanceId}")
    public ResponseEntity<InformacoesProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get InformacoesProcess by processInstanceId : {}", processInstanceId);
        Optional<InformacoesProcessDTO> informacoesProcessDTO = informacoesProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(informacoesProcessDTO);
    }
}
