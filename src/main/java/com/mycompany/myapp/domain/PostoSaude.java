package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A PostoSaude.
 */
@Entity
@Table(name = "posto_saude")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class PostoSaude implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nome_posto")
    private String nomePosto;

    @Column(name = "estado_posto")
    private String estadoPosto;

    @Column(name = "cidade_posto")
    private String cidadePosto;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PostoSaude id(Long id) {
        this.id = id;
        return this;
    }

    public String getNomePosto() {
        return this.nomePosto;
    }

    public PostoSaude nomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
        return this;
    }

    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
    }

    public String getEstadoPosto() {
        return this.estadoPosto;
    }

    public PostoSaude estadoPosto(String estadoPosto) {
        this.estadoPosto = estadoPosto;
        return this;
    }

    public void setEstadoPosto(String estadoPosto) {
        this.estadoPosto = estadoPosto;
    }

    public String getCidadePosto() {
        return this.cidadePosto;
    }

    public PostoSaude cidadePosto(String cidadePosto) {
        this.cidadePosto = cidadePosto;
        return this;
    }

    public void setCidadePosto(String cidadePosto) {
        this.cidadePosto = cidadePosto;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PostoSaude)) {
            return false;
        }
        return id != null && id.equals(((PostoSaude) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PostoSaude{" +
            "id=" + getId() +
            ", nomePosto='" + getNomePosto() + "'" +
            ", estadoPosto='" + getEstadoPosto() + "'" +
            ", cidadePosto='" + getCidadePosto() + "'" +
            "}";
    }
}
