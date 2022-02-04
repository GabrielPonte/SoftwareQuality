package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A LocalDesejado.
 */
@Entity
@Table(name = "local_desejado")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LocalDesejado implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "local_covid")
    private String localCovid;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDesejado id(Long id) {
        this.id = id;
        return this;
    }

    public String getLocalCovid() {
        return this.localCovid;
    }

    public LocalDesejado localCovid(String localCovid) {
        this.localCovid = localCovid;
        return this;
    }

    public void setLocalCovid(String localCovid) {
        this.localCovid = localCovid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LocalDesejado)) {
            return false;
        }
        return id != null && id.equals(((LocalDesejado) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LocalDesejado{" +
            "id=" + getId() +
            ", localCovid='" + getLocalCovid() + "'" +
            "}";
    }
}
