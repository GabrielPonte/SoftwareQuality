package com.mycompany.myapp.process.agendamentoProcess;

import com.mycompany.myapp.repository.AgendamentoProcessRepository;
import com.mycompany.myapp.service.AgendamentoService;
import com.mycompany.myapp.service.dto.AgendamentoDTO;
import com.mycompany.myapp.service.dto.AgendamentoProcessDTO;
import com.mycompany.myapp.service.mapper.AgendamentoProcessMapper;
import org.akip.repository.TaskInstanceRepository;
import org.akip.service.TaskInstanceService;
import org.akip.service.dto.TaskInstanceDTO;
import org.akip.service.mapper.TaskInstanceMapper;
import org.springframework.stereotype.Component;

@Component
public class PreencherDadosService {

    private final TaskInstanceService taskInstanceService;

    private final AgendamentoService agendamentoService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final AgendamentoProcessRepository agendamentoProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final PreencherDadosMapper preencherDadosMapper;

    private final AgendamentoProcessMapper agendamentoProcessMapper;

    public PreencherDadosService(
        TaskInstanceService taskInstanceService,
        AgendamentoService agendamentoService,
        TaskInstanceRepository taskInstanceRepository,
        AgendamentoProcessRepository agendamentoProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        PreencherDadosMapper preencherDadosMapper,
        AgendamentoProcessMapper agendamentoProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.agendamentoService = agendamentoService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.agendamentoProcessRepository = agendamentoProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.preencherDadosMapper = preencherDadosMapper;
        this.agendamentoProcessMapper = agendamentoProcessMapper;
    }

    public PreencherDadosContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        AgendamentoProcessDTO agendamentoProcess = agendamentoProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(preencherDadosMapper::toAgendamentoProcessDTO)
            .orElseThrow();

        PreencherDadosContextDTO preencherDadosContext = new PreencherDadosContextDTO();
        preencherDadosContext.setTaskInstance(taskInstanceDTO);
        preencherDadosContext.setAgendamentoProcess(agendamentoProcess);

        return preencherDadosContext;
    }

    public PreencherDadosContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(PreencherDadosContextDTO preencherDadosContext) {
        AgendamentoDTO agendamentoDTO = agendamentoService
            .findOne(preencherDadosContext.getAgendamentoProcess().getAgendamento().getId())
            .orElseThrow();
        agendamentoDTO.setNomeCompleto(preencherDadosContext.getAgendamentoProcess().getAgendamento().getNomeCompleto());
        agendamentoDTO.setIdade(preencherDadosContext.getAgendamentoProcess().getAgendamento().getIdade());
        agendamentoDTO.setCPF(preencherDadosContext.getAgendamentoProcess().getAgendamento().getCPF());
        agendamentoDTO.setCEP(preencherDadosContext.getAgendamentoProcess().getAgendamento().getCEP());
        agendamentoDTO.setEndereco(preencherDadosContext.getAgendamentoProcess().getAgendamento().getEndereco());
        agendamentoDTO.setComplemento(preencherDadosContext.getAgendamentoProcess().getAgendamento().getComplemento());
        agendamentoDTO.setQtdVacinas(preencherDadosContext.getAgendamentoProcess().getAgendamento().getQtdVacinas());
        agendamentoDTO.setSintomas(preencherDadosContext.getAgendamentoProcess().getAgendamento().getSintomas());
        agendamentoService.save(agendamentoDTO);
    }

    public void complete(PreencherDadosContextDTO preencherDadosContext) {
        save(preencherDadosContext);
        AgendamentoProcessDTO agendamentoProcess = agendamentoProcessRepository
            .findByProcessInstanceId(preencherDadosContext.getAgendamentoProcess().getProcessInstance().getId())
            .map(agendamentoProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(preencherDadosContext.getTaskInstance(), agendamentoProcess);
    }
}
