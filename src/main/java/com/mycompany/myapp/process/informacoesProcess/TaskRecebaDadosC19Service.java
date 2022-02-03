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
public class TaskRecebaDadosC19Service {

    private final TaskInstanceService taskInstanceService;

    private final InformacoesService informacoesService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final InformacoesProcessRepository informacoesProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskRecebaDadosC19Mapper taskRecebaDadosC19Mapper;

    private final InformacoesProcessMapper informacoesProcessMapper;

    public TaskRecebaDadosC19Service(
        TaskInstanceService taskInstanceService,
        InformacoesService informacoesService,
        TaskInstanceRepository taskInstanceRepository,
        InformacoesProcessRepository informacoesProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskRecebaDadosC19Mapper taskRecebaDadosC19Mapper,
        InformacoesProcessMapper informacoesProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.informacoesService = informacoesService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.informacoesProcessRepository = informacoesProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskRecebaDadosC19Mapper = taskRecebaDadosC19Mapper;
        this.informacoesProcessMapper = informacoesProcessMapper;
    }

    public TaskRecebaDadosC19ContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        InformacoesProcessDTO informacoesProcess = informacoesProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskRecebaDadosC19Mapper::toInformacoesProcessDTO)
            .orElseThrow();

        TaskRecebaDadosC19ContextDTO taskRecebaDadosC19Context = new TaskRecebaDadosC19ContextDTO();
        taskRecebaDadosC19Context.setTaskInstance(taskInstanceDTO);
        taskRecebaDadosC19Context.setInformacoesProcess(informacoesProcess);

        return taskRecebaDadosC19Context;
    }

    public TaskRecebaDadosC19ContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskRecebaDadosC19ContextDTO taskRecebaDadosC19Context) {
        InformacoesDTO informacoesDTO = informacoesService
            .findOne(taskRecebaDadosC19Context.getInformacoesProcess().getInformacoes().getId())
            .orElseThrow();
        informacoesDTO.setTipoDeInformacao(taskRecebaDadosC19Context.getInformacoesProcess().getInformacoes().getTipoDeInformacao());
        informacoesDTO.setLocal(taskRecebaDadosC19Context.getInformacoesProcess().getInformacoes().getLocal());
        informacoesDTO.setReceberEmail(taskRecebaDadosC19Context.getInformacoesProcess().getInformacoes().getReceberEmail());
        informacoesService.save(informacoesDTO);
    }

    public void complete(TaskRecebaDadosC19ContextDTO taskRecebaDadosC19Context) {
        save(taskRecebaDadosC19Context);
        InformacoesProcessDTO informacoesProcess = informacoesProcessRepository
            .findByProcessInstanceId(taskRecebaDadosC19Context.getInformacoesProcess().getProcessInstance().getId())
            .map(informacoesProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskRecebaDadosC19Context.getTaskInstance(), informacoesProcess);
    }
}
