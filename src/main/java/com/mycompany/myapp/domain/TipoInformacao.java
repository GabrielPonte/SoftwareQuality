package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A TipoInformacao.
 */
@Entity
@Table(name = "tipo_informacao")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TipoInformacao implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "tipo_info")
    private String tipoInfo;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoInformacao id(Long id) {
        this.id = id;
        return this;
    }

    public String getTipoInfo() {
        return this.tipoInfo;
    }

    public TipoInformacao tipoInfo(String tipoInfo) {
        this.tipoInfo = tipoInfo;
        return this;
    }

    public void setTipoInfo(String tipoInfo) {
        this.tipoInfo = tipoInfo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TipoInformacao)) {
            return false;
        }
        return id != null && id.equals(((TipoInformacao) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TipoInformacao{" +
            "id=" + getId() +
            ", tipoInfo='" + getTipoInfo() + "'" +
            "}";
    }
}
