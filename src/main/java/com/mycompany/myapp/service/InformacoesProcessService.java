package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.InformacoesProcess;
import com.mycompany.myapp.repository.InformacoesProcessRepository;
import com.mycompany.myapp.repository.InformacoesRepository;
import com.mycompany.myapp.service.dto.InformacoesProcessDTO;
import com.mycompany.myapp.service.mapper.InformacoesProcessMapper;
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
 * Service Implementation for managing {@link InformacoesProcess}.
 */
@Service
@Transactional
public class InformacoesProcessService {

    public static final String BPMN_PROCESS_DEFINITION_ID = "InformacoesProcess";

    private final Logger log = LoggerFactory.getLogger(InformacoesProcessService.class);

    private final ProcessInstanceService processInstanceService;

    private final InformacoesRepository informacoesRepository;

    private final InformacoesProcessRepository informacoesProcessRepository;

    private final InformacoesProcessMapper informacoesProcessMapper;

    public InformacoesProcessService(
        ProcessInstanceService processInstanceService,
        InformacoesRepository informacoesRepository,
        InformacoesProcessRepository informacoesProcessRepository,
        InformacoesProcessMapper informacoesProcessMapper
    ) {
        this.processInstanceService = processInstanceService;
        this.informacoesRepository = informacoesRepository;
        this.informacoesProcessRepository = informacoesProcessRepository;
        this.informacoesProcessMapper = informacoesProcessMapper;
    }

    /**
     * Save a informacoesProcess.
     *
     * @param informacoesProcessDTO the entity to save.
     * @return the persisted entity.
     */
    public InformacoesProcessDTO create(InformacoesProcessDTO informacoesProcessDTO) {
        log.debug("Request to save InformacoesProcess : {}", informacoesProcessDTO);

        InformacoesProcess informacoesProcess = informacoesProcessMapper.toEntity(informacoesProcessDTO);

        //Saving the domainEntity
        informacoesRepository.save(informacoesProcess.getInformacoes());

        //Creating the process instance in the Camunda and setting it in the process entity
        ProcessInstance processInstance = processInstanceService.create(
            BPMN_PROCESS_DEFINITION_ID,
            "Informacoes#" + informacoesProcess.getInformacoes().getId(),
            informacoesProcess
        );
        informacoesProcess.setProcessInstance(processInstance);

        //Saving the process entity
        informacoesProcess = informacoesProcessRepository.save(informacoesProcess);
        return informacoesProcessMapper.toDto(informacoesProcess);
    }

    /**
     * Get all the informacoesProcesss.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<InformacoesProcessDTO> findAll() {
        log.debug("Request to get all InformacoesProcesss");
        return informacoesProcessRepository
            .findAll()
            .stream()
            .map(informacoesProcessMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one informacoesProcess by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InformacoesProcessDTO> findOne(Long id) {
        log.debug("Request to get InformacoesProcess : {}", id);
        return informacoesProcessRepository.findById(id).map(informacoesProcessMapper::toDto);
    }

    /**
     * Get one informacoesProcess by id.
     *
     * @param processInstanceId the id of the processInstance associated to the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<InformacoesProcessDTO> findByProcessInstanceId(Long processInstanceId) {
        log.debug("Request to get InformacoesProcess by  processInstanceId: {}", processInstanceId);
        return informacoesProcessRepository.findByProcessInstanceId(processInstanceId).map(informacoesProcessMapper::toDto);
    }
}
