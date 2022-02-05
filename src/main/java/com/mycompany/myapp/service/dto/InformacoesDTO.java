package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Informacoes} entity.
 */
public class InformacoesDTO implements Serializable {

    private Long id;

    private String nomeCompleto;

    private String cpf;

    private String email;

    private Integer idade;

    private String sintomas;

    private Integer qtdVacinas;

    private String cidade;

    private String estado;

    private String endereco;

    private String complemento;

    private String cep;

    private LocalDate data;

    private String hora;

    private LocalDesejadoDTO localDesejado;

    private OpcaoEscolhidaDTO opcaoEscolhida;

    private PostoSaudeDTO postoSaude;

    private RecebaEmailDTO recebaEmail;

    private TipoInformacaoDTO tipoInformacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public Integer getQtdVacinas() {
        return qtdVacinas;
    }

    public void setQtdVacinas(Integer qtdVacinas) {
        this.qtdVacinas = qtdVacinas;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public LocalDesejadoDTO getLocalDesejado() {
        return localDesejado;
    }

    public void setLocalDesejado(LocalDesejadoDTO localDesejado) {
        this.localDesejado = localDesejado;
    }

    public OpcaoEscolhidaDTO getOpcaoEscolhida() {
        return opcaoEscolhida;
    }

    public void setOpcaoEscolhida(OpcaoEscolhidaDTO opcaoEscolhida) {
        this.opcaoEscolhida = opcaoEscolhida;
    }

    public PostoSaudeDTO getPostoSaude() {
        return postoSaude;
    }

    public void setPostoSaude(PostoSaudeDTO postoSaude) {
        this.postoSaude = postoSaude;
    }

    public RecebaEmailDTO getRecebaEmail() {
        return recebaEmail;
    }

    public void setRecebaEmail(RecebaEmailDTO recebaEmail) {
        this.recebaEmail = recebaEmail;
    }

    public TipoInformacaoDTO getTipoInformacao() {
        return tipoInformacao;
    }

    public void setTipoInformacao(TipoInformacaoDTO tipoInformacao) {
        this.tipoInformacao = tipoInformacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InformacoesDTO)) {
            return false;
        }

        InformacoesDTO informacoesDTO = (InformacoesDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, informacoesDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InformacoesDTO{" +
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
            ", localDesejado=" + getLocalDesejado() +
            ", opcaoEscolhida=" + getOpcaoEscolhida() +
            ", postoSaude=" + getPostoSaude() +
            ", recebaEmail=" + getRecebaEmail() +
            ", tipoInformacao=" + getTipoInformacao() +
            "}";
    }
}
