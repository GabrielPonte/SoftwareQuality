package com.mycompany.myapp.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A DTO for the {@link com.mycompany.myapp.domain.Agendamento} entity.
 */
public class AgendamentoDTO implements Serializable {

    private Long id;

    private String nomeCompleto;

    private String CPF;

    private Integer idade;

    private String sintomas;

    private Integer qtdVacinas;

    private String cidade;

    private String estado;

    private String endereco;

    private String complemento;

    private String CEP;

    private String nomePosto;

    private LocalDate data;

    private String hora;

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

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
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

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getNomePosto() {
        return nomePosto;
    }

    public void setNomePosto(String nomePosto) {
        this.nomePosto = nomePosto;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AgendamentoDTO)) {
            return false;
        }

        AgendamentoDTO agendamentoDTO = (AgendamentoDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, agendamentoDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AgendamentoDTO{" +
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
