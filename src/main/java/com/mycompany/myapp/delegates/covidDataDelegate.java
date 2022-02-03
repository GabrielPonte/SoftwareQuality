package com.mycompany.myapp.covid.delegates;

import com.mycompany.myapp.domain.Informacoes;
import com.mycompany.myapp.service.dto.InformacoesProcessDTO;
import java.util.Random;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class covidDataDelegate implements JavaDelegate {

    // @Autowired
    // InformationTypeService informationTypeService;

    // @Autowired
    // InformationLocalService informationLocalService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        InformacoesProcessDTO pi = (InformacoesProcessDTO) delegateExecution.getVariable("processInstance");
        String covidInformationType = pi.getInformacoes().getTipoDeInformacao();
        String covidinformationLocal = pi.getInformacoes().getLocal();
        //random number
        Random rand = new Random(); //instance of random class
        Integer upperbound = 1000000;
        Integer int_random = rand.nextInt(upperbound);
        delegateExecution.setVariable("int_random", int_random);

        //print data
        System.out.println("=================================================");
        System.out.println("============== SISTEMA VACINA JÁ ================");
        System.out.println("=================================================");
        System.out.println("COVID INFORMATION DATA in " + covidinformationLocal + ":");
        System.out.println("      " + covidInformationType + ": " + int_random + ".");
        System.out.println("=================================================");
        // // //Confirming the flight
        // // informationTypeService.confirmFlight(pi.getInformacoes().getTipoDeInformacao());

        // // //Confirming the hotel booking
        // // informationLocalService.confirmReservation(pi.getInformacoes().getLocal());

    }
}
