package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.OpcaoEscolhida} entity.
 */
public class OpcaoEscolhidaDTO implements Serializable {

    private Long id;

    private String opcaoInicial;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpcaoInicial() {
        return opcaoInicial;
    }

    public void setOpcaoInicial(String opcaoInicial) {
        this.opcaoInicial = opcaoInicial;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OpcaoEscolhidaDTO)) {
            return false;
        }

        OpcaoEscolhidaDTO opcaoEscolhidaDTO = (OpcaoEscolhidaDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, opcaoEscolhidaDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OpcaoEscolhidaDTO{" +
            "id=" + getId() +
            ", opcaoInicial='" + getOpcaoInicial() + "'" +
            "}";
    }
}
