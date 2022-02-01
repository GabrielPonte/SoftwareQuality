package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.akip.service.dto.ProcessInstanceDTO;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.AgendamentoProcess} entity.
 */
public class AgendamentoProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private AgendamentoDTO agendamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessInstanceDTO getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(ProcessInstanceDTO processInstance) {
        this.processInstance = processInstance;
    }

    public AgendamentoDTO getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(AgendamentoDTO agendamento) {
        this.agendamento = agendamento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AgendamentoProcessDTO)) {
            return false;
        }

        AgendamentoProcessDTO agendamentoProcessDTO = (AgendamentoProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, agendamentoProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AgendamentoProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", agendamento=" + getAgendamento() +
            "}";
    }
}
