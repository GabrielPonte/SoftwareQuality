package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.AgendamentoProcess;
import com.mycompany.myapp.repository.AgendamentoProcessRepository;
import com.mycompany.myapp.repository.AgendamentoRepository;
import com.mycompany.myapp.service.dto.AgendamentoProcessDTO;
import com.mycompany.myapp.service.mapper.AgendamentoProcessMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.akip.domain.ProcessInstance;
import org.akip.service.ProcessInstanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link AgendamentoProcess}.
 */
@Service
@Transactional
public class AgendamentoProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "AgendamentoProcess";

    private final Logger log = LoggerFactory.getLogger(AgendamentoProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final AgendamentoRepository agendamentoRepository;

    private final AgendamentoProcessRepository agendamentoProcessRepository;

    private final AgendamentoProcessMapper agendamentoProcessMapper;

    public AgendamentoProcessService(
        ProcessInstanceService processInstanceService,
        AgendamentoRepository agendamentoRepository,
        AgendamentoProcessRepository agendamentoProcessRepository,
        AgendamentoProcessMapper agendamentoProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.agendamentoRepository = agendamentoRepository;
        this.agendamentoProcessRepository = agendamentoProcessRepository;
        this.agendamentoProcessMapper = agendamentoProcessMapper;
    }

    /**
     * Save a agendamentoProcess.
     *
     * @param agendamentoProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public AgendamentoProcessDTO create(AgendamentoProcessDTO agendamentoProcessDTO) {
        log.debug("Request to save AgendamentoProcess : {}", agendamentoProcessDTO);

        AgendamentoProcess agendamentoProcess = agendamentoProcessMapper.toEntity(agendamentoProcessDTO);

        //Saving the domainEntity
        agendamentoRepository.save(agendamentoProcess.getAgendamento());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Agendamento#" + agendamentoProcess.getAgendamento().getId(),
            agendamentoProcess
        );
        agendamentoProcess.setProcessInstance(processInstance);

        //Saving the process entity
        agendamentoProcess = agendamentoProcessRepository.save(agendamentoProcess);
        return agendamentoProcessMapper.toDto(agendamentoProcess);
    }

    /**
     * Get all the agendamentoProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AgendamentoProcessDTO> findAll() {
        log.debug("Request to get all AgendamentoProcesss");
        return agendamentoProcessRepository
            .findAll()
            .stream()
            .map(agendamentoProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one agendamentoProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AgendamentoProcessDTO> findOne(Long id) {
        log.debug("Request to get AgendamentoProcess : {}", id);
        return agendamentoProcessRepository.findById(id).map(agendamentoProcessMapper::toDto);
    }

    /**
     * Get one agendamentoProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AgendamentoProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get AgendamentoProcess by  processInstanceId: {}", processInstanceId);
        return agendamentoProcessRepository.findByProcessInstanceId(processInstanceId).map(agendamentoProcessMapper::toDto);
    }
}
