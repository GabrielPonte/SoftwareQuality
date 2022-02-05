package com.mycompany.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A RecebaEmail.
 */
@Entity
@Table(name = "receba_email")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class RecebaEmail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "opcao_email")
    private String opcaoEmail;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecebaEmail id(Long id) {
        this.id = id;
        return this;
    }

    public String getOpcaoEmail() {
        return this.opcaoEmail;
    }

    public RecebaEmail opcaoEmail(String opcaoEmail) {
        this.opcaoEmail = opcaoEmail;
        return this;
    }

    public void setOpcaoEmail(String opcaoEmail) {
        this.opcaoEmail = opcaoEmail;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecebaEmail)) {
            return false;
        }
        return id != null && id.equals(((RecebaEmail) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecebaEmail{" +
            "id=" + getId() +
            ", opcaoEmail='" + getOpcaoEmail() + "'" +
            "}";
    }
}
