package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.LocalDesejado} entity.
 */
public class LocalDesejadoDTO implements Serializable {

    private Long id;

    private String localCovid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalCovid() {
        return localCovid;
    }

    public void setLocalCovid(String localCovid) {
        this.localCovid = localCovid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocalDesejadoDTO)) {
            return false;
        }

        LocalDesejadoDTO localDesejadoDTO = (LocalDesejadoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, localDesejadoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LocalDesejadoDTO{" +
            "id=" + getId() +
            ", localCovid='" + getLocalCovid() + "'" +
            "}";
    }
}
