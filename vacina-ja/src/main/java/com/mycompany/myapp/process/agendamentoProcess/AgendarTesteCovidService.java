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
public class AgendarTesteCovidService {

    private final TaskInstanceService taskInstanceService;

    private final AgendamentoService agendamentoService;

    private final TaskInstanceRepository taskInstanceRepository;

    private final AgendamentoProcessRepository agendamentoProcessRepository;

    private final TaskInstanceMapper taskInstanceMapper;

    private final AgendarTesteCovidMapper agendarTesteCovidMapper;

    private final AgendamentoProcessMapper agendamentoProcessMapper;

    public AgendarTesteCovidService(
        TaskInstanceService taskInstanceService,
        AgendamentoService agendamentoService,
        TaskInstanceRepository taskInstanceRepository,
        AgendamentoProcessRepository agendamentoProcessRepository,
        TaskInstanceMapper taskInstanceMapper,
        AgendarTesteCovidMapper agendarTesteCovidMapper,
        AgendamentoProcessMapper agendamentoProcessMapper
    ) {
        this.taskInstanceService = taskInstanceService;
        this.agendamentoService = agendamentoService;
        this.taskInstanceRepository = taskInstanceRepository;
        this.agendamentoProcessRepository = agendamentoProcessRepository;
        this.taskInstanceMapper = taskInstanceMapper;
        this.agendarTesteCovidMapper = agendarTesteCovidMapper;
        this.agendamentoProcessMapper = agendamentoProcessMapper;
    }

    public AgendarTesteCovidContextDTO loadContext(Long taskInstanceId) {
        TaskInstanceDTO taskInstanceDTO = taskInstanceRepository
            .findById(taskInstanceId)
            .map(taskInstanceMapper::toDTOLoadTaskContext)
            .orElseThrow();

        AgendamentoProcessDTO agendamentoProcess = agendamentoProcessRepository
            .findByProcessInstanceId(taskInstanceDTO.getProcessInstance().getId())
            .map(agendarTesteCovidMapper::toAgendamentoProcessDTO)
            .orElseThrow();

        AgendarTesteCovidContextDTO agendarTesteCovidContext = new AgendarTesteCovidContextDTO();
        agendarTesteCovidContext.setTaskInstance(taskInstanceDTO);
        agendarTesteCovidContext.setAgendamentoProcess(agendamentoProcess);

        return agendarTesteCovidContext;
    }

    public AgendarTesteCovidContextDTO claim(Long taskInstanceId) {
        taskInstanceService.claim(taskInstanceId);
        return loadContext(taskInstanceId);
    }

    public void save(AgendarTesteCovidContextDTO agendarTesteCovidContext) {
        AgendamentoDTO agendamentoDTO = agendamentoService
            .findOne(agendarTesteCovidContext.getAgendamentoProcess().getAgendamento().getId())
            .orElseThrow();
        agendamentoDTO.setData(agendarTesteCovidContext.getAgendamentoProcess().getAgendamento().getData());
        agendamentoDTO.setHora(agendarTesteCovidContext.getAgendamentoProcess().getAgendamento().getHora());
        agendamentoService.save(agendamentoDTO);
    }

    public void complete(AgendarTesteCovidContextDTO agendarTesteCovidContext) {
        save(agendarTesteCovidContext);
        AgendamentoProcessDTO agendamentoProcess = agendamentoProcessRepository
            .findByProcessInstanceId(agendarTesteCovidContext.getAgendamentoProcess().getProcessInstance().getId())
            .map(agendamentoProcessMapper::toDto)
            .orElseThrow();
        taskInstanceService.complete(agendarTesteCovidContext.getTaskInstance(), agendamentoProcess);
    }
}
