package com.mycompany.myapp.covid.delegates;

import com.mycompany.myapp.domain.Informacoes;
import com.mycompany.myapp.service.dto.InformacoesProcessDTO;
import java.time.LocalDate;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class agendamentoDelegate implements JavaDelegate {

    // @Autowired
    // InformationTypeService informationTypeService;

    // @Autowired
    // InformationLocalService informationLocalService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        InformacoesProcessDTO pi = (InformacoesProcessDTO) delegateExecution.getVariable("processInstance");
        String nomeCompleto = pi.getInformacoes().getNomeCompleto();
        String email = pi.getInformacoes().getEmail();
        Integer idade = pi.getInformacoes().getIdade();
        String cpf = pi.getInformacoes().getCpf();
        String cep = pi.getInformacoes().getCep();
        String endereco = pi.getInformacoes().getEndereco();
        String complemento = pi.getInformacoes().getComplemento();
        Integer qtdVacinas = pi.getInformacoes().getQtdVacinas();
        String sintomas = pi.getInformacoes().getSintomas();
        String cidade = pi.getInformacoes().getCidade();
        String estado = pi.getInformacoes().getEstado();
        String nomeDoPosto = pi.getInformacoes().getNomeDoPosto();
        String estadoDoPosto = pi.getInformacoes().getEstadoDoPosto();
        String cidadeDoPosto = pi.getInformacoes().getCidadeDoPosto();
        LocalDate localDate = pi.getInformacoes().getData();
        String hora = pi.getInformacoes().getHora();

        //print data
        System.out.println("=================================================");
        System.out.println("             AGENDAMENTO CONFIRMADO              ");
        System.out.println("=================================================");
        System.out.println("Dados Pessoais");
        System.out.println("=================================================");
        System.out.println("Nome Completo: " + nomeCompleto);
        System.out.println("Idade: " + idade);
        System.out.println("CPF: " + cpf);
        System.out.println("Email: " + email);
        System.out.println("Quantidade de vacinas recebidas: " + qtdVacinas);
        System.out.println("Sintomas: " + sintomas);
        System.out.println("=================================================");
        System.out.println("Residência");
        System.out.println("=================================================");
        System.out.println("CEP: " + cep);
        System.out.println("Estado: " + estado);
        System.out.println("Cidade: " + cidade);
        System.out.println("Endereço: " + endereco);
        System.out.println("Complemento: " + complemento);
        System.out.println("=================================================");
        System.out.println("Agendamento para teste de covid");
        System.out.println("=================================================");
        System.out.println("Posto: " + nomeDoPosto);
        System.out.println("Local: " + estadoDoPosto + ", " + cidadeDoPosto);
        System.out.println("Data: " + localDate);
        System.out.println("Hora: " + hora);
        System.out.println("=================================================");
        // // //Confirming the flight
        // // informationTypeService.confirmFlight(pi.getInformacoes().getTipoDeInformacao());

        // // //Confirming the hotel booking
        // // informationLocalService.confirmReservation(pi.getInformacoes().getLocal());

    }
}
