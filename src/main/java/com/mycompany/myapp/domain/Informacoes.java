package com.mycompany.myapp.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Informacoes.
 */
@Entity
@Table(name = "informacoes")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Informacoes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "cpf")
    private String CPF;

    @Column(name = "email")
    private String email;

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
    private String hora;

    @Column(name = "tipo_de_informacao")
    private String tipoDeInformacao;

    @Column(name = "local")
    private String local;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Informacoes id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Informacoes name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

    public Informacoes nomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
        return this;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCPF() {
        return this.CPF;
    }

    public Informacoes CPF(String CPF) {
        this.CPF = CPF;
        return this;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return this.email;
    }

    public Informacoes email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdade() {
        return this.idade;
    }

    public Informacoes idade(Integer idade) {
        this.idade = idade;
        return this;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getSintomas() {
        return this.sintomas;
    }

    public Informacoes sintomas(String sintomas) {
        this.sintomas = sintomas;
        return this;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public Integer getQtdVacinas() {
        return this.qtdVacinas;
    }

    public Informacoes qtdVacinas(Integer qtdVacinas) {
        this.qtdVacinas = qtdVacinas;
        return this;
    }

    public void setQtdVacinas(Integer qtdVacinas) {
        this.qtdVacinas = qtdVacinas;
    }

    public String getCidade() {
        return this.cidade;
    }

    public Informacoes cidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return this.estado;
    }

    public Informacoes estado(String estado) {
        this.estado = estado;
        return this;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public Informacoes endereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return this.complemento;
    }

    public Informacoes complemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCEP() {
        return this.CEP;
    }

    public Informacoes CEP(String CEP) {
        this.CEP = CEP;
        return this;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getNomePosto() {
        return this.nomePosto;
    }

    public Informacoes nomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
        return this;
    }

    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
    }

    public LocalDate getData() {
        return this.data;
    }

    public Informacoes data(LocalDate data) {
        this.data = data;
        return this;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHora() {
        return this.hora;
    }

    public Informacoes hora(String hora) {
        this.hora = hora;
        return this;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoDeInformacao() {
        return this.tipoDeInformacao;
    }

    public Informacoes tipoDeInformacao(String tipoDeInformacao) {
        this.tipoDeInformacao = tipoDeInformacao;
        return this;
    }

    public void setTipoDeInformacao(String tipoDeInformacao) {
        this.tipoDeInformacao = tipoDeInformacao;
    }

    public String getLocal() {
        return this.local;
    }

    public Informacoes local(String local) {
        this.local = local;
        return this;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Informacoes)) {
            return false;
        }
        return id != null && id.equals(((Informacoes) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Informacoes{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nomeCompleto='" + getNomeCompleto() + "'" +
            ", CPF='" + getCPF() + "'" +
            ", email='" + getEmail() + "'" +
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
            ", tipoDeInformacao='" + getTipoDeInformacao() + "'" +
            ", local='" + getLocal() + "'" +
            "}";
    }
}
