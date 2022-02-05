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
public class TaskInitialOptionService {

    private final TaskInstanceService taskInstanceService;

    private final InformacoesService informacoesService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final InformacoesProcessRepository informacoesProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskInitialOptionMapper taskInitialOptionMapper;

    private final InformacoesProcessMapper informacoesProcessMapper;

    public TaskInitialOptionService(
        TaskInstanceService taskInstanceService,
        InformacoesService informacoesService,
        TaskInstanceRepository taskInstanceRepository,
        InformacoesProcessRepository informacoesProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskInitialOptionMapper taskInitialOptionMapper,
        InformacoesProcessMapper informacoesProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.informacoesService = informacoesService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.informacoesProcessRepository = informacoesProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskInitialOptionMapper = taskInitialOptionMapper;
        this.informacoesProcessMapper = informacoesProcessMapper;
    }

    public TaskInitialOptionContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        InformacoesProcessDTO informacoesProcess = informacoesProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskInitialOptionMapper::toInformacoesProcessDTO)
            .orElseThrow();

        TaskInitialOptionContextDTO taskInitialOptionContext = new TaskInitialOptionContextDTO();
        taskInitialOptionContext.setTaskInstance(taskInstanceDTO);
        taskInitialOptionContext.setInformacoesProcess(informacoesProcess);

        return taskInitialOptionContext;
    }

    public TaskInitialOptionContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskInitialOptionContextDTO taskInitialOptionContext) {
        InformacoesDTO informacoesDTO = informacoesService
            .findOne(taskInitialOptionContext.getInformacoesProcess().getInformacoes().getId())
            .orElseThrow();
        informacoesDTO.setOpcaoEscolhida(taskInitialOptionContext.getInformacoesProcess().getInformacoes().getOpcaoEscolhida());
        informacoesService.save(informacoesDTO);
    }

    public void complete(TaskInitialOptionContextDTO taskInitialOptionContext) {
        save(taskInitialOptionContext);
        InformacoesProcessDTO informacoesProcess = informacoesProcessRepository
            .findByProcessInstanceId(taskInitialOptionContext.getInformacoesProcess().getProcessInstance().getId())
            .map(informacoesProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskInitialOptionContext.getTaskInstance(), informacoesProcess);
    }
}
