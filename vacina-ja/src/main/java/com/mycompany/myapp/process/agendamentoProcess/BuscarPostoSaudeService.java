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
public class BuscarPostoSaudeService {

    private final TaskInstanceService taskInstanceService;

    private final AgendamentoService agendamentoService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final AgendamentoProcessRepository agendamentoProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final BuscarPostoSaudeMapper buscarPostoSaudeMapper;

    private final AgendamentoProcessMapper agendamentoProcessMapper;

    public BuscarPostoSaudeService(
        TaskInstanceService taskInstanceService,
        AgendamentoService agendamentoService,
        TaskInstanceRepository taskInstanceRepository,
        AgendamentoProcessRepository agendamentoProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        BuscarPostoSaudeMapper buscarPostoSaudeMapper,
        AgendamentoProcessMapper agendamentoProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.agendamentoService = agendamentoService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.agendamentoProcessRepository = agendamentoProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.buscarPostoSaudeMapper = buscarPostoSaudeMapper;
        this.agendamentoProcessMapper = agendamentoProcessMapper;
    }

    public BuscarPostoSaudeContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        AgendamentoProcessDTO agendamentoProcess = agendamentoProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(buscarPostoSaudeMapper::toAgendamentoProcessDTO)
            .orElseThrow();

        BuscarPostoSaudeContextDTO buscarPostoSaudeContext = new BuscarPostoSaudeContextDTO();
        buscarPostoSaudeContext.setTaskInstance(taskInstanceDTO);
        buscarPostoSaudeContext.setAgendamentoProcess(agendamentoProcess);

        return buscarPostoSaudeContext;
    }

    public BuscarPostoSaudeContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(BuscarPostoSaudeContextDTO buscarPostoSaudeContext) {
        AgendamentoDTO agendamentoDTO = agendamentoService
            .findOne(buscarPostoSaudeContext.getAgendamentoProcess().getAgendamento().getId())
            .orElseThrow();
        agendamentoDTO.setCidade(buscarPostoSaudeContext.getAgendamentoProcess().getAgendamento().getCidade());
        agendamentoDTO.setEstado(buscarPostoSaudeContext.getAgendamentoProcess().getAgendamento().getEstado());
        agendamentoDTO.setNomePosto(buscarPostoSaudeContext.getAgendamentoProcess().getAgendamento().getNomePosto());
        agendamentoService.save(agendamentoDTO);
    }

    public void complete(BuscarPostoSaudeContextDTO buscarPostoSaudeContext) {
        save(buscarPostoSaudeContext);
        AgendamentoProcessDTO agendamentoProcess = agendamentoProcessRepository
            .findByProcessInstanceId(buscarPostoSaudeContext.getAgendamentoProcess().getProcessInstance().getId())
            .map(agendamentoProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(buscarPostoSaudeContext.getTaskInstance(), agendamentoProcess);
    }
}
