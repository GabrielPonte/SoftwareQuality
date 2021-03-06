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

    @Column(name = "data")
    private LocalDate data;

    @Column(name = "hora")
    private String hora;

    @ManyToOne
    private LocalDesejado localDesejado;

    @ManyToOne
    private OpcaoEscolhida opcaoEscolhida;

    @ManyToOne
    private PostoSaude postoSaude;

    @ManyToOne
    private RecebaEmail recebaEmail;

    @ManyToOne
    private TipoInformacao tipoInformacao;

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

    public LocalDesejado getLocalDesejado() {
        return this.localDesejado;
    }

    public Informacoes localDesejado(LocalDesejado localDesejado) {
        this.setLocalDesejado(localDesejado);
        return this;
    }

    public void setLocalDesejado(LocalDesejado localDesejado) {
        this.localDesejado = localDesejado;
    }

    public OpcaoEscolhida getOpcaoEscolhida() {
        return this.opcaoEscolhida;
    }

    public Informacoes opcaoEscolhida(OpcaoEscolhida opcaoEscolhida) {
        this.setOpcaoEscolhida(opcaoEscolhida);
        return this;
    }

    public void setOpcaoEscolhida(OpcaoEscolhida opcaoEscolhida) {
        this.opcaoEscolhida = opcaoEscolhida;
    }

    public PostoSaude getPostoSaude() {
        return this.postoSaude;
    }

    public Informacoes postoSaude(PostoSaude postoSaude) {
        this.setPostoSaude(postoSaude);
        return this;
    }

    public void setPostoSaude(PostoSaude postoSaude) {
        this.postoSaude = postoSaude;
    }

    public RecebaEmail getRecebaEmail() {
        return this.recebaEmail;
    }

    public Informacoes recebaEmail(RecebaEmail recebaEmail) {
        this.setRecebaEmail(recebaEmail);
        return this;
    }

    public void setRecebaEmail(RecebaEmail recebaEmail) {
        this.recebaEmail = recebaEmail;
    }

    public TipoInformacao getTipoInformacao() {
        return this.tipoInformacao;
    }

    public Informacoes tipoInformacao(TipoInformacao tipoInformacao) {
        this.setTipoInformacao(tipoInformacao);
        return this;
    }

    public void setTipoInformacao(TipoInformacao tipoInformacao) {
        this.tipoInformacao = tipoInformacao;
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
            ", data='" + getData() + "'" +
            ", hora='" + getHora() + "'" +
            "}";
    }
}
