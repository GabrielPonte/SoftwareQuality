package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.service.AgendamentoProcessService;
import com.mycompany.myapp.service.dto.AgendamentoProcessDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.AgendamentoProcess}.
 */
@RestController
@RequestMapping("/api")
public class AgendamentoProcessResource {

    private final Logger log = LoggerFactory.getLogger(AgendamentoProcessResource.class);

    private static final String ENTITY_NAME = "agendamentoProcess";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AgendamentoProcessService agendamentoProcessService;

    public AgendamentoProcessResource(AgendamentoProcessService agendamentoProcessService) {
        this.agendamentoProcessService = agendamentoProcessService;
    }

    /**
     * {@code POST  /agendamento-processes} : Create a new agendamentoProcess.
     *
     * @param agendamentoProcessDTO the agendamentoProcessDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new agendamentoProcessDTO, or with status {@code 400 (Bad Request)} if the agendamentoProcess has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/agendamento-processes")
    public ResponseEntity<AgendamentoProcessDTO> create(@RequestBody AgendamentoProcessDTO agendamentoProcessDTO)
        throws URISyntaxException {
        log.debug("REST request to save AgendamentoProcess : {}", agendamentoProcessDTO);
        if (agendamentoProcessDTO.getId() != null) {
            throw new BadRequestAlertException("A new agendamentoProcess cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgendamentoProcessDTO result = agendamentoProcessService.create(agendamentoProcessDTO);
        return ResponseEntity
            .created(new URI("/api/agendamento-processes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /agendamento-processes} : get all the agendamentoProcesss.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of agendamentoProcesss in body.
     */
    @GetMapping("/agendamento-processes")
    public List<AgendamentoProcessDTO> getAllAgendamentoProcesss() {
        log.debug("REST request to get all AgendamentoProcesss");
        return agendamentoProcessService.findAll();
    }

    /**
     * {@code GET  /agendamento-processes/:id} : get the "id" agendamentoProcess.
     *
     * @param processInstanceId the id of the agendamentoProcessDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the agendamentoProcessDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/agendamento-processes/{processInstanceId}")
    public ResponseEntity<AgendamentoProcessDTO> getByProcessInstanceId(@PathVariable Long processInstanceId) {
        log.debug("REST request to get AgendamentoProcess by processInstanceId : {}", processInstanceId);
        Optional<AgendamentoProcessDTO> agendamentoProcessDTO = agendamentoProcessService.findByProcessInstanceId(processInstanceId);
        return ResponseUtil.wrapOrNotFound(agendamentoProcessDTO);
    }
}
