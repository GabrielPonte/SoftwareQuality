package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.PostoSaude} entity.
 */
public class PostoSaudeDTO implements Serializable {

    private Long id;

    private String nomePosto;

    private String estadoPosto;

    private String cidadePosto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomePosto() {
        return nomePosto;
    }

    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
    }

    public String getEstadoPosto() {
        return estadoPosto;
    }

    public void setEstadoPosto(String estadoPosto) {
        this.estadoPosto = estadoPosto;
    }

    public String getCidadePosto() {
        return cidadePosto;
    }

    public void setCidadePosto(String cidadePosto) {
        this.cidadePosto = cidadePosto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PostoSaudeDTO)) {
            return false;
        }

        PostoSaudeDTO postoSaudeDTO = (PostoSaudeDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, postoSaudeDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PostoSaudeDTO{" +
            "id=" + getId() +
            ", nomePosto='" + getNomePosto() + "'" +
            ", estadoPosto='" + getEstadoPosto() + "'" +
            ", cidadePosto='" + getCidadePosto() + "'" +
            "}";
    }
}
