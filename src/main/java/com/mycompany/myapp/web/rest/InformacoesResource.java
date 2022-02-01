package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.InformacoesRepository;
import com.mycompany.myapp.service.InformacoesService;
import com.mycompany.myapp.service.dto.InformacoesDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Informacoes}.
 */
@RestController
@RequestMapping("/api")
public class InformacoesResource {

    private final Logger log = LoggerFactory.getLogger(InformacoesResource.class);

    private final InformacoesService informacoesService;

    private final InformacoesRepository informacoesRepository;

    public InformacoesResource(InformacoesService informacoesService, InformacoesRepository informacoesRepository) {
        this.informacoesService = informacoesService;
        this.informacoesRepository = informacoesRepository;
    }

    /**
     * {@code GET  /informacoes} : get all the informacoes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of informacoes in body.
     */
    @GetMapping("/informacoes")
    public List<InformacoesDTO> getAllInformacoes() {
        log.debug("REST request to get all Informacoes");
        return informacoesService.findAll();
    }

    /**
     * {@code GET  /informacoes/:id} : get the "id" informacoes.
     *
     * @param id the id of the informacoesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the informacoesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/informacoes/{id}")
    public ResponseEntity<InformacoesDTO> getInformacoes(@PathVariable Long id) {
        log.debug("REST request to get Informacoes : {}", id);
        Optional<InformacoesDTO> informacoesDTO = informacoesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(informacoesDTO);
    }
}
