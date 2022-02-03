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

    @Column(name = "opcao")
    private String opcao;

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "cpf")
    private String cpf;

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
    private String cep;

    @Column(name = "nome_do_posto")
    private String nomeDoPosto;

    @Column(name = "estado_do_posto")
    private String estadoDoPosto;

    @Column(name = "cidade_do_posto")
    private String cidadeDoPosto;

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "hora")
    private String hora;

    @Column(name = "tipo_de_informacao")
    private String tipoDeInformacao;

    @Column(name = "local")
    private String local;

    @Column(name = "receber_email")
    private String receberEmail;

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

    public String getOpcao() {
        return this.opcao;
    }

    public Informacoes opcao(String opcao) {
        this.opcao = opcao;
        return this;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
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

    public String getCpf() {
        return this.cpf;
    }

    public Informacoes cpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getCep() {
        return this.cep;
    }

    public Informacoes cep(String cep) {
        this.cep = cep;
        return this;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNomeDoPosto() {
        return this.nomeDoPosto;
    }

    public Informacoes nomeDoPosto(String nomeDoPosto) {
        this.nomeDoPosto = nomeDoPosto;
        return this;
    }

    public void setNomeDoPosto(String nomeDoPosto) {
        this.nomeDoPosto = nomeDoPosto;
    }

    public String getEstadoDoPosto() {
        return this.estadoDoPosto;
    }

    public Informacoes estadoDoPosto(String estadoDoPosto) {
        this.estadoDoPosto = estadoDoPosto;
        return this;
    }

    public void setEstadoDoPosto(String estadoDoPosto) {
        this.estadoDoPosto = estadoDoPosto;
    }

    public String getCidadeDoPosto() {
        return this.cidadeDoPosto;
    }

    public Informacoes cidadeDoPosto(String cidadeDoPosto) {
        this.cidadeDoPosto = cidadeDoPosto;
        return this;
    }

    public void setCidadeDoPosto(String cidadeDoPosto) {
        this.cidadeDoPosto = cidadeDoPosto;
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

    public String getReceberEmail() {
        return this.receberEmail;
    }

    public Informacoes receberEmail(String receberEmail) {
        this.receberEmail = receberEmail;
        return this;
    }

    public void setReceberEmail(String receberEmail) {
        this.receberEmail = receberEmail;
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
            ", opcao='" + getOpcao() + "'" +
            ", nomeCompleto='" + getNomeCompleto() + "'" +
            ", cpf='" + getCpf() + "'" +
            ", email='" + getEmail() + "'" +
            ", idade=" + getIdade() +
            ", sintomas='" + getSintomas() + "'" +
            ", qtdVacinas=" + getQtdVacinas() +
            ", cidade='" + getCidade() + "'" +
            ", estado='" + getEstado() + "'" +
            ", endereco='" + getEndereco() + "'" +
            ", complemento='" + getComplemento() + "'" +
            ", cep='" + getCep() + "'" +
            ", nomeDoPosto='" + getNomeDoPosto() + "'" +
            ", estadoDoPosto='" + getEstadoDoPosto() + "'" +
            ", cidadeDoPosto='" + getCidadeDoPosto() + "'" +
            ", data='" + getData() + "'" +
            ", hora='" + getHora() + "'" +
            ", tipoDeInformacao='" + getTipoDeInformacao() + "'" +
            ", local='" + getLocal() + "'" +
            ", receberEmail='" + getReceberEmail() + "'" +
            "}";
    }
}
