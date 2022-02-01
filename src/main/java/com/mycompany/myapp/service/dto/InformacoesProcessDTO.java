package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;
import org.akip.service.dto.ProcessInstanceDTO;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.InformacoesProcess} entity.
 */
public class InformacoesProcessDTO implements Serializable {

    private Long id;

    private ProcessInstanceDTO processInstance;

    private InformacoesDTO informacoes;

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

    public InformacoesDTO getInformacoes() {
        return informacoes;
    }

    public void setInformacoes(InformacoesDTO informacoes) {
        this.informacoes = informacoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InformacoesProcessDTO)) {
            return false;
        }

        InformacoesProcessDTO informacoesProcessDTO = (InformacoesProcessDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, informacoesProcessDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InformacoesProcessDTO{" +
            "id=" + getId() +
            ", processInstance=" + getProcessInstance() +
            ", informacoes=" + getInformacoes() +
            "}";
    }
}
