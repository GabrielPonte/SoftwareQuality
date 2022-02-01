package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Agendamento.
 */
@Entity
@Table(name = "agendamento")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Agendamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "cpf")
    private String CPF;

    @Column(name = "idade")
    private Integer idade;

    @Column(name = "sintomas")
    private String sintomas;

    @Column(name = "qtd_vacinas")
    private Integer qtdVacinas;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "cep")
    private String CEP;

    @Column(name = "nome_posto")
    private String nomePosto;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "hora")
    private LocalDate hora;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agendamento id(Long id) {
        this.id = id;
        return this;
    }

    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

    public Agendamento nomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
        return this;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCPF() {
        return this.CPF;
    }

    public Agendamento CPF(String CPF) {
        this.CPF = CPF;
        return this;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public Integer getIdade() {
        return this.idade;
    }

    public Agendamento idade(Integer idade) {
        this.idade = idade;
        return this;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getSintomas() {
        return this.sintomas;
    }

    public Agendamento sintomas(String sintomas) {
        this.sintomas = sintomas;
        return this;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public Integer getQtdVacinas() {
        return this.qtdVacinas;
    }

    public Agendamento qtdVacinas(Integer qtdVacinas) {
        this.qtdVacinas = qtdVacinas;
        return this;
    }

    public void setQtdVacinas(Integer qtdVacinas) {
        this.qtdVacinas = qtdVacinas;
    }

    public String getCidade() {
        return this.cidade;
    }

    public Agendamento cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public Agendamento estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public Agendamento endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public Agendamento complemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCEP() {
        return this.CEP;
    }

    public Agendamento CEP(String CEP) {
        this.CEP = CEP;
        return this;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getNomePosto() {
        return this.nomePosto;
    }

    public Agendamento nomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
        return this;
    }

    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
    }

    public LocalDate getData() {
        return this.data;
    }

    public Agendamento data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalDate getHora() {
        return this.hora;
    }

    public Agendamento hora(LocalDate hora) {
        this.hora = hora;
        return this;
    }

    public void setHora(LocalDate hora) {
        this.hora = hora;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Agendamento)) {
            return false;
        }
        return id != null && id.equals(((Agendamento) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Agendamento{" +
            "id=" + getId() +
            ", nomeCompleto='" + getNomeCompleto() + "'" +
            ", CPF='" + getCPF() + "'" +
            ", idade=" + getIdade() +
            ", sintomas='" + getSintomas() + "'" +
            ", qtdVacinas=" + getQtdVacinas() +
            ", cidade='" + getCidade() + "'" +
            ", estado='" + getEstado() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", complemento='" + getComplemento() + "'" +
            ", CEP='" + getCEP() + "'" +
            ", nomePosto='" + getNomePosto() + "'" +
            ", data='" + getData() + "'" +
            ", hora='" + getHora() + "'" +
            "}";
    }
}
