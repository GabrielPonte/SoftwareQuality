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
public class TaskRecebaEmailC19Service {

    private final TaskInstanceService taskInstanceService;

    private final InformacoesService informacoesService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final InformacoesProcessRepository informacoesProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskRecebaEmailC19Mapper taskRecebaEmailC19Mapper;

    private final InformacoesProcessMapper informacoesProcessMapper;

    public TaskRecebaEmailC19Service(
        TaskInstanceService taskInstanceService,
        InformacoesService informacoesService,
        TaskInstanceRepository taskInstanceRepository,
        InformacoesProcessRepository informacoesProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskRecebaEmailC19Mapper taskRecebaEmailC19Mapper,
        InformacoesProcessMapper informacoesProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.informacoesService = informacoesService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.informacoesProcessRepository = informacoesProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskRecebaEmailC19Mapper = taskRecebaEmailC19Mapper;
        this.informacoesProcessMapper = informacoesProcessMapper;
    }

    public TaskRecebaEmailC19ContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        InformacoesProcessDTO informacoesProcess = informacoesProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskRecebaEmailC19Mapper::toInformacoesProcessDTO)
            .orElseThrow();

        TaskRecebaEmailC19ContextDTO taskRecebaEmailC19Context = new TaskRecebaEmailC19ContextDTO();
        taskRecebaEmailC19Context.setTaskInstance(taskInstanceDTO);
        taskRecebaEmailC19Context.setInformacoesProcess(informacoesProcess);

        return taskRecebaEmailC19Context;
    }

    public TaskRecebaEmailC19ContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskRecebaEmailC19ContextDTO taskRecebaEmailC19Context) {
        InformacoesDTO informacoesDTO = informacoesService
            .findOne(taskRecebaEmailC19Context.getInformacoesProcess().getInformacoes().getId())
            .orElseThrow();
        informacoesDTO.setReceberEmail(taskRecebaEmailC19Context.getInformacoesProcess().getInformacoes().getReceberEmail());
        informacoesDTO.setRecebaEmail(taskRecebaEmailC19Context.getInformacoesProcess().getInformacoes().getRecebaEmail());
        informacoesService.save(informacoesDTO);
    }

    public void complete(TaskRecebaEmailC19ContextDTO taskRecebaEmailC19Context) {
        save(taskRecebaEmailC19Context);
        InformacoesProcessDTO informacoesProcess = informacoesProcessRepository
            .findByProcessInstanceId(taskRecebaEmailC19Context.getInformacoesProcess().getProcessInstance().getId())
            .map(informacoesProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskRecebaEmailC19Context.getTaskInstance(), informacoesProcess);
    }
}
