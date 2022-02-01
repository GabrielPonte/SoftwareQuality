package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Agendamento;
import com.mycompany.myapp.repository.AgendamentoRepository;
import com.mycompany.myapp.service.dto.AgendamentoDTO;
import com.mycompany.myapp.service.mapper.AgendamentoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Agendamento}.
 */
@Service
@Transactional
public class AgendamentoService {

    private final Logger log = LoggerFactory.getLogger(AgendamentoService.class);

    private final AgendamentoRepository agendamentoRepository;

    private final AgendamentoMapper agendamentoMapper;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, AgendamentoMapper agendamentoMapper) {
        this.agendamentoRepository = agendamentoRepository;
        this.agendamentoMapper = agendamentoMapper;
    }

    /**
     * Save a agendamento.
     *
     * @param agendamentoDTO the entity to save.
     * @return the persisted entity.
     */
    public AgendamentoDTO save(AgendamentoDTO agendamentoDTO) {
        log.debug("Request to save Agendamento : {}", agendamentoDTO);
        Agendamento agendamento = agendamentoMapper.toEntity(agendamentoDTO);
        agendamento = agendamentoRepository.save(agendamento);
        return agendamentoMapper.toDto(agendamento);
    }

    /**
     * Partially update a agendamento.
     *
     * @param agendamentoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<AgendamentoDTO> partialUpdate(AgendamentoDTO agendamentoDTO) {
        log.debug("Request to partially update Agendamento : {}", agendamentoDTO);

        return agendamentoRepository
            .findById(agendamentoDTO.getId())
            .map(
                existingAgendamento -> {
                    agendamentoMapper.partialUpdate(existingAgendamento, agendamentoDTO);
                    return existingAgendamento;
                }
            )
            .map(agendamentoRepository::save)
            .map(agendamentoMapper::toDto);
    }

    /**
     * Get all the agendamentos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AgendamentoDTO> findAll() {
        log.debug("Request to get all Agendamentos");
        return agendamentoRepository.findAll().stream().map(agendamentoMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one agendamento by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AgendamentoDTO> findOne(Long id) {
        log.debug("Request to get Agendamento : {}", id);
        return agendamentoRepository.findById(id).map(agendamentoMapper::toDto);
    }

    /**
     * Delete the agendamento by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Agendamento : {}", id);
        agendamentoRepository.deleteById(id);
    }
}
