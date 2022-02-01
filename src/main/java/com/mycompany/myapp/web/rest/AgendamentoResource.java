package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.AgendamentoRepository;
import com.mycompany.myapp.service.AgendamentoService;
import com.mycompany.myapp.service.dto.AgendamentoDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Agendamento}.
 */
@RestController
@RequestMapping("/api")
public class AgendamentoResource {

    private final Logger log = LoggerFactory.getLogger(AgendamentoResource.class);

    private final AgendamentoService agendamentoService;

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoResource(AgendamentoService agendamentoService, AgendamentoRepository agendamentoRepository) {
        this.agendamentoService = agendamentoService;
        this.agendamentoRepository = agendamentoRepository;
    }

    /**
     * {@code GET  /agendamentos} : get all the agendamentos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of agendamentos in body.
     */
    @GetMapping("/agendamentos")
    public List<AgendamentoDTO> getAllAgendamentos() {
        log.debug("REST request to get all Agendamentos");
        return agendamentoService.findAll();
    }

    /**
     * {@code GET  /agendamentos/:id} : get the "id" agendamento.
     *
     * @param id the id of the agendamentoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the agendamentoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/agendamentos/{id}")
    public ResponseEntity<AgendamentoDTO> getAgendamento(@PathVariable Long id) {
        log.debug("REST request to get Agendamento : {}", id);
        Optional<AgendamentoDTO> agendamentoDTO = agendamentoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(agendamentoDTO);
    }
}
