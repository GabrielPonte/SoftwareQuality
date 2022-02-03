package com.mycompany.myapp.covid.delegates;

import com.mycompany.myapp.domain.Informacoes;
import com.mycompany.myapp.service.MailService;
import com.mycompany.myapp.service.dto.InformacoesDTO;
import com.mycompany.myapp.service.dto.InformacoesProcessDTO;
import java.util.Locale;
import java.util.Random;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class sendAgendamentoDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        InformacoesProcessDTO pi = (InformacoesProcessDTO) delegateExecution.getVariable("processInstance");
        InformacoesDTO Informacoes = pi.getInformacoes();
        String nomeCompleto = pi.getInformacoes().getNomeCompleto();
        String to = pi.getInformacoes().getEmail();
        String subject = "[VACINA-JÁ] Confirmação do agendamento do paciente " + nomeCompleto;
        Context context = new Context(Locale.getDefault());
        context.setVariable("Informacoes", Informacoes);
        String content = templateEngine.process("InformacoesProcess/AgendamentoSummaryEmail", context);
        mailService.sendEmail(to, subject, content, false, true);
    }
}
