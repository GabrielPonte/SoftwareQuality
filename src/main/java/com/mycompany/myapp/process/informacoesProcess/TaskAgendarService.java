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
public class TaskAgendarService {

    private final TaskInstanceService taskInstanceService;

    private final InformacoesService informacoesService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final InformacoesProcessRepository informacoesProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskAgendarMapper taskAgendarMapper;

    private final InformacoesProcessMapper informacoesProcessMapper;

    public TaskAgendarService(
        TaskInstanceService taskInstanceService,
        InformacoesService informacoesService,
        TaskInstanceRepository taskInstanceRepository,
        InformacoesProcessRepository informacoesProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskAgendarMapper taskAgendarMapper,
        InformacoesProcessMapper informacoesProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.informacoesService = informacoesService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.informacoesProcessRepository = informacoesProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskAgendarMapper = taskAgendarMapper;
        this.informacoesProcessMapper = informacoesProcessMapper;
    }

    public TaskAgendarContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        InformacoesProcessDTO informacoesProcess = informacoesProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskAgendarMapper::toInformacoesProcessDTO)
            .orElseThrow();

        TaskAgendarContextDTO taskAgendarContext = new TaskAgendarContextDTO();
        taskAgendarContext.setTaskInstance(taskInstanceDTO);
        taskAgendarContext.setInformacoesProcess(informacoesProcess);

        return taskAgendarContext;
    }

    public TaskAgendarContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskAgendarContextDTO taskAgendarContext) {
        InformacoesDTO informacoesDTO = informacoesService
            .findOne(taskAgendarContext.getInformacoesProcess().getInformacoes().getId())
            .orElseThrow();
        informacoesDTO.setData(taskAgendarContext.getInformacoesProcess().getInformacoes().getData());
        informacoesDTO.setHora(taskAgendarContext.getInformacoesProcess().getInformacoes().getHora());
        informacoesService.save(informacoesDTO);
    }

    public void complete(TaskAgendarContextDTO taskAgendarContext) {
        save(taskAgendarContext);
        InformacoesProcessDTO informacoesProcess = informacoesProcessRepository
            .findByProcessInstanceId(taskAgendarContext.getInformacoesProcess().getProcessInstance().getId())
            .map(informacoesProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskAgendarContext.getTaskInstance(), informacoesProcess);
    }
}
