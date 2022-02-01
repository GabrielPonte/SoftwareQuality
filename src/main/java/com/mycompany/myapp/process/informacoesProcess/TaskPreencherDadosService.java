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
public class TaskPreencherDadosService {

    private final TaskInstanceService taskInstanceService;

    private final InformacoesService informacoesService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final InformacoesProcessRepository informacoesProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final TaskPreencherDadosMapper taskPreencherDadosMapper;

    private final InformacoesProcessMapper informacoesProcessMapper;

    public TaskPreencherDadosService(
        TaskInstanceService taskInstanceService,
        InformacoesService informacoesService,
        TaskInstanceRepository taskInstanceRepository,
        InformacoesProcessRepository informacoesProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        TaskPreencherDadosMapper taskPreencherDadosMapper,
        InformacoesProcessMapper informacoesProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.informacoesService = informacoesService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.informacoesProcessRepository = informacoesProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.taskPreencherDadosMapper = taskPreencherDadosMapper;
        this.informacoesProcessMapper = informacoesProcessMapper;
    }

    public TaskPreencherDadosContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        InformacoesProcessDTO informacoesProcess = informacoesProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(taskPreencherDadosMapper::toInformacoesProcessDTO)
            .orElseThrow();

        TaskPreencherDadosContextDTO taskPreencherDadosContext = new TaskPreencherDadosContextDTO();
        taskPreencherDadosContext.setTaskInstance(taskInstanceDTO);
        taskPreencherDadosContext.setInformacoesProcess(informacoesProcess);

        return taskPreencherDadosContext;
    }

    public TaskPreencherDadosContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(TaskPreencherDadosContextDTO taskPreencherDadosContext) {
        InformacoesDTO informacoesDTO = informacoesService
            .findOne(taskPreencherDadosContext.getInformacoesProcess().getInformacoes().getId())
            .orElseThrow();
        informacoesDTO.setNomeCompleto(taskPreencherDadosContext.getInformacoesProcess().getInformacoes().getNomeCompleto());
        informacoesDTO.setEmail(taskPreencherDadosContext.getInformacoesProcess().getInformacoes().getEmail());
        informacoesDTO.setIdade(taskPreencherDadosContext.getInformacoesProcess().getInformacoes().getIdade());
        informacoesDTO.setCPF(taskPreencherDadosContext.getInformacoesProcess().getInformacoes().getCPF());
        informacoesDTO.setCEP(taskPreencherDadosContext.getInformacoesProcess().getInformacoes().getCEP());
        informacoesDTO.setEndereco(taskPreencherDadosContext.getInformacoesProcess().getInformacoes().getEndereco());
        informacoesDTO.setComplemento(taskPreencherDadosContext.getInformacoesProcess().getInformacoes().getComplemento());
        informacoesDTO.setQtdVacinas(taskPreencherDadosContext.getInformacoesProcess().getInformacoes().getQtdVacinas());
        informacoesDTO.setSintomas(taskPreencherDadosContext.getInformacoesProcess().getInformacoes().getSintomas());
        informacoesService.save(informacoesDTO);
    }

    public void complete(TaskPreencherDadosContextDTO taskPreencherDadosContext) {
        save(taskPreencherDadosContext);
        InformacoesProcessDTO informacoesProcess = informacoesProcessRepository
            .findByProcessInstanceId(taskPreencherDadosContext.getInformacoesProcess().getProcessInstance().getId())
            .map(informacoesProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(taskPreencherDadosContext.getTaskInstance(), informacoesProcess);
    }
}
