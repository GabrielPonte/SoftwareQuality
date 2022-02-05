package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A OpcaoEscolhida.
 */
@Entity
@Table(name = "opcao_escolhida")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class OpcaoEscolhida implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "opcao_inicial")
    private String opcaoInicial;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OpcaoEscolhida id(Long id) {
        this.id = id;
        return this;
    }

    public String getOpcaoInicial() {
        return this.opcaoInicial;
    }

    public OpcaoEscolhida opcaoInicial(String opcaoInicial) {
        this.opcaoInicial = opcaoInicial;
        return this;
    }

    public void setOpcaoInicial(String opcaoInicial) {
        this.opcaoInicial = opcaoInicial;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OpcaoEscolhida)) {
            return false;
        }
        return id != null && id.equals(((OpcaoEscolhida) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OpcaoEscolhida{" +
            "id=" + getId() +
            ", opcaoInicial='" + getOpcaoInicial() + "'" +
            "}";
    }
}
