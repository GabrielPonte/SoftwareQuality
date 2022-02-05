package com.mycompany.myapp.process.informacoesProcess;

import com.mycompany.myapp.repository.InformacoesProcessRepository;
import com.mycompany.myapp.service.InformacoesService;
import com.mycompany.myapp.service.dto.InformacoesDTO;
import com.mycompany.myapp.service.dto.InformacoesProcessDTO;
import com.mycompany.myapp.service.mapper.InformacoesProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class TaskRecebaLocalC19Service {

    private final TaskInstanceService taskInstanceService;

    private final InformacoesService informacoesService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final InformacoesProcessRepository informacoesProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskRecebaLocalC19Mapper taskRecebaLocalC19Mapper;

    private final InformacoesProcessMapper informacoesProcessMapper;

    public TaskRecebaLocalC19Service(
        TaskInstanceService taskInstanceService,
        InformacoesService informacoesService,
        TaskInstanceRepository taskInstanceRepository,
        InformacoesProcessRepository informacoesProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskRecebaLocalC19Mapper taskRecebaLocalC19Mapper,
        InformacoesProcessMapper informacoesProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.informacoesService = informacoesService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.informacoesProcessRepository = informacoesProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskRecebaLocalC19Mapper = taskRecebaLocalC19Mapper;
        this.informacoesProcessMapper = informacoesProcessMapper;
    }

    public TaskRecebaLocalC19ContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        InformacoesProcessDTO informacoesProcess = informacoesProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskRecebaLocalC19Mapper::toInformacoesProcessDTO)
            .orElseThrow();

        TaskRecebaLocalC19ContextDTO taskRecebaLocalC19Context = new TaskRecebaLocalC19ContextDTO();
        taskRecebaLocalC19Context.setTaskInstance(taskInstanceDTO);
        taskRecebaLocalC19Context.setInformacoesProcess(informacoesProcess);

        return taskRecebaLocalC19Context;
    }

    public TaskRecebaLocalC19ContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskRecebaLocalC19ContextDTO taskRecebaLocalC19Context) {
        InformacoesDTO informacoesDTO = informacoesService
            .findOne(taskRecebaLocalC19Context.getInformacoesProcess().getInformacoes().getId())
            .orElseThrow();
        informacoesDTO.setLocalDesejado(taskRecebaLocalC19Context.getInformacoesProcess().getInformacoes().getLocalDesejado());
        informacoesService.save(informacoesDTO);
    }

    public void complete(TaskRecebaLocalC19ContextDTO taskRecebaLocalC19Context) {
        save(taskRecebaLocalC19Context);
        InformacoesProcessDTO informacoesProcess = informacoesProcessRepository
            .findByProcessInstanceId(taskRecebaLocalC19Context.getInformacoesProcess().getProcessInstance().getId())
            .map(informacoesProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskRecebaLocalC19Context.getTaskInstance(), informacoesProcess);
    }
}
