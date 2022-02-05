package com.mycompany.myapp.process.informacoesProcess;

import com.mycompany.myapp.service.dto.InformacoesProcessDTO;
import org.akip.service.dto.TaskInstanceDTO;

public class TaskRecebaEmailC19ContextDTO {

    private InformacoesProcessDTO informacoesProcess;
    private TaskInstanceDTO taskInstance;

    public InformacoesProcessDTO getInformacoesProcess() {
        return informacoesProcess;
    }

    public void setInformacoesProcess(InformacoesProcessDTO informacoesProcess) {
        this.informacoesProcess = informacoesProcess;
    }

    public TaskInstanceDTO getTaskInstance() {
        return taskInstance;
    }

    public void setTaskInstance(TaskInstanceDTO taskInstance) {
        this.taskInstance = taskInstance;
    }
}
