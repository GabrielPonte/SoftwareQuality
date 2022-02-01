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
public class TaskBuscaPostoService {

    private final TaskInstanceService taskInstanceService;

    private final InformacoesService informacoesService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final InformacoesProcessRepository informacoesProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskBuscaPostoMapper taskBuscaPostoMapper;

    private final InformacoesProcessMapper informacoesProcessMapper;

    public TaskBuscaPostoService(
        TaskInstanceService taskInstanceService,
        InformacoesService informacoesService,
        TaskInstanceRepository taskInstanceRepository,
        InformacoesProcessRepository informacoesProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskBuscaPostoMapper taskBuscaPostoMapper,
        InformacoesProcessMapper informacoesProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.informacoesService = informacoesService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.informacoesProcessRepository = informacoesProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskBuscaPostoMapper = taskBuscaPostoMapper;
        this.informacoesProcessMapper = informacoesProcessMapper;
    }

    public TaskBuscaPostoContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        InformacoesProcessDTO informacoesProcess = informacoesProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskBuscaPostoMapper::toInformacoesProcessDTO)
            .orElseThrow();

        TaskBuscaPostoContextDTO taskBuscaPostoContext = new TaskBuscaPostoContextDTO();
        taskBuscaPostoContext.setTaskInstance(taskInstanceDTO);
        taskBuscaPostoContext.setInformacoesProcess(informacoesProcess);

        return taskBuscaPostoContext;
    }

    public TaskBuscaPostoContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskBuscaPostoContextDTO taskBuscaPostoContext) {
        InformacoesDTO informacoesDTO = informacoesService
            .findOne(taskBuscaPostoContext.getInformacoesProcess().getInformacoes().getId())
            .orElseThrow();
        informacoesDTO.setCidade(taskBuscaPostoContext.getInformacoesProcess().getInformacoes().getCidade());
        informacoesDTO.setEstado(taskBuscaPostoContext.getInformacoesProcess().getInformacoes().getEstado());
        informacoesDTO.setNomePosto(taskBuscaPostoContext.getInformacoesProcess().getInformacoes().getNomePosto());
        informacoesService.save(informacoesDTO);
    }

    public void complete(TaskBuscaPostoContextDTO taskBuscaPostoContext) {
        save(taskBuscaPostoContext);
        InformacoesProcessDTO informacoesProcess = informacoesProcessRepository
            .findByProcessInstanceId(taskBuscaPostoContext.getInformacoesProcess().getProcessInstance().getId())
            .map(informacoesProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskBuscaPostoContext.getTaskInstance(), informacoesProcess);
    }
}
