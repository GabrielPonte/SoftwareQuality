package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.OpcaoEscolhida;
import com.mycompany.myapp.repository.OpcaoEscolhidaRepository;
import com.mycompany.myapp.service.dto.OpcaoEscolhidaDTO;
import com.mycompany.myapp.service.mapper.OpcaoEscolhidaMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link OpcaoEscolhida}.
 */
@Service
@Transactional
public class OpcaoEscolhidaService {

    private final Logger log = LoggerFactory.getLogger(OpcaoEscolhidaService.class);

    private final OpcaoEscolhidaRepository opcaoEscolhidaRepository;

    private final OpcaoEscolhidaMapper opcaoEscolhidaMapper;

    public OpcaoEscolhidaService(OpcaoEscolhidaRepository opcaoEscolhidaRepository, OpcaoEscolhidaMapper opcaoEscolhidaMapper) {
        this.opcaoEscolhidaRepository = opcaoEscolhidaRepository;
        this.opcaoEscolhidaMapper = opcaoEscolhidaMapper;
    }

    /**
     * Save a opcaoEscolhida.
     *
     * @param opcaoEscolhidaDTO the entity to save.
     * @return the persisted entity.
     */
    public OpcaoEscolhidaDTO save(OpcaoEscolhidaDTO opcaoEscolhidaDTO) {
        log.debug("Request to save OpcaoEscolhida : {}", opcaoEscolhidaDTO);
        OpcaoEscolhida opcaoEscolhida = opcaoEscolhidaMapper.toEntity(opcaoEscolhidaDTO);
        opcaoEscolhida = opcaoEscolhidaRepository.save(opcaoEscolhida);
        return opcaoEscolhidaMapper.toDto(opcaoEscolhida);
    }

    /**
     * Partially update a opcaoEscolhida.
     *
     * @param opcaoEscolhidaDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OpcaoEscolhidaDTO> partialUpdate(OpcaoEscolhidaDTO opcaoEscolhidaDTO) {
        log.debug("Request to partially update OpcaoEscolhida : {}", opcaoEscolhidaDTO);

        return opcaoEscolhidaRepository
            .findById(opcaoEscolhidaDTO.getId())
            .map(
                existingOpcaoEscolhida -> {
                    opcaoEscolhidaMapper.partialUpdate(existingOpcaoEscolhida, opcaoEscolhidaDTO);
                    return existingOpcaoEscolhida;
                }
            )
            .map(opcaoEscolhidaRepository::save)
            .map(opcaoEscolhidaMapper::toDto);
    }

    /**
     * Get all the opcaoEscolhidas.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OpcaoEscolhidaDTO> findAll() {
        log.debug("Request to get all OpcaoEscolhidas");
        return opcaoEscolhidaRepository
            .findAll()
            .stream()
            .map(opcaoEscolhidaMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one opcaoEscolhida by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OpcaoEscolhidaDTO> findOne(Long id) {
        log.debug("Request to get OpcaoEscolhida : {}", id);
        return opcaoEscolhidaRepository.findById(id).map(opcaoEscolhidaMapper::toDto);
    }

    /**
     * Delete the opcaoEscolhida by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OpcaoEscolhida : {}", id);
        opcaoEscolhidaRepository.deleteById(id);
    }
}
