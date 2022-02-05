package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.TipoInformacao} entity.
 */
public class TipoInformacaoDTO implements Serializable {

    private Long id;

    private String tipoInfo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoInfo() {
        return tipoInfo;
    }

    public void setTipoInfo(String tipoInfo) {
        this.tipoInfo = tipoInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoInformacaoDTO)) {
            return false;
        }

        TipoInformacaoDTO tipoInformacaoDTO = (TipoInformacaoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, tipoInformacaoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipoInformacaoDTO{" +
            "id=" + getId() +
            ", tipoInfo='" + getTipoInfo() + "'" +
            "}";
    }
}
