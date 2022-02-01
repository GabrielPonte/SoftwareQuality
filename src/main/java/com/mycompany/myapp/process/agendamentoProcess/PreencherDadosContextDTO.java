package com.mycompany.myapp.process.agendamentoProcess;

import com.mycompany.myapp.service.dto.AgendamentoProcessDTO;
import org.akip.service.dto.TaskInstanceDTO;

public class PreencherDadosContextDTO {

    private AgendamentoProcessDTO agendamentoProcess;
    private TaskInstanceDTO taskInstance;

    public AgendamentoProcessDTO getAgendamentoProcess() {
        return agendamentoProcess;
    }

    public void setAgendamentoProcess(AgendamentoProcessDTO agendamentoProcess) {
        this.agendamentoProcess = agendamentoProcess;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}
