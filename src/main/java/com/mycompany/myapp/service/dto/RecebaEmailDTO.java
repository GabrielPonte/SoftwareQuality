package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.RecebaEmail} entity.
 */
public class RecebaEmailDTO implements Serializable {

    private Long id;

    private String opcaoEmail;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpcaoEmail() {
        return opcaoEmail;
    }

    public void setOpcaoEmail(String opcaoEmail) {
        this.opcaoEmail = opcaoEmail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecebaEmailDTO)) {
            return false;
        }

        RecebaEmailDTO recebaEmailDTO = (RecebaEmailDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, recebaEmailDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecebaEmailDTO{" +
            "id=" + getId() +
            ", opcaoEmail='" + getOpcaoEmail() + "'" +
            "}";
    }
}
