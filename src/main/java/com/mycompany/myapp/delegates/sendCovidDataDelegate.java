package com.mycompany.myapp.covid.delegates;

import com.mycompany.myapp.domain.Informacoes;
import com.mycompany.myapp.domain.LocalDesejado;
import com.mycompany.myapp.domain.OpcaoEscolhida;
import com.mycompany.myapp.domain.PostoSaude;
import com.mycompany.myapp.domain.RecebaEmail;
import com.mycompany.myapp.domain.TipoInformacao;
import com.mycompany.myapp.repository.LocalDesejadoRepository;
import com.mycompany.myapp.repository.OpcaoEscolhidaRepository;
import com.mycompany.myapp.repository.PostoSaudeRepository;
import com.mycompany.myapp.repository.RecebaEmailRepository;
import com.mycompany.myapp.repository.TipoInformacaoRepository;
import com.mycompany.myapp.service.MailService;
import com.mycompany.myapp.service.dto.InformacoesDTO;
import com.mycompany.myapp.service.dto.InformacoesProcessDTO;
import com.mycompany.myapp.service.dto.LocalDesejadoDTO;
import com.mycompany.myapp.service.dto.OpcaoEscolhidaDTO;
import com.mycompany.myapp.service.dto.PostoSaudeDTO;
import com.mycompany.myapp.service.dto.RecebaEmailDTO;
import com.mycompany.myapp.service.dto.TipoInformacaoDTO;
import java.util.Locale;
import java.util.Random;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Component
public class sendCovidDataDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;

    private final LocalDesejadoRepository localDesejadoRepo;
    private final TipoInformacaoRepository tipoInformacaoRepo;
    private final RecebaEmailRepository recebaEmailRepo;
    private final OpcaoEscolhidaRepository opcaoEscolhidaRepo;
    private final PostoSaudeRepository postoSaudeRepo;

    public sendCovidDataDelegate(
        LocalDesejadoRepository localDesejadoRepo,
        TipoInformacaoRepository tipoInformacaoRepo,
        RecebaEmailRepository recebaEmailRepo,
        OpcaoEscolhidaRepository opcaoEscolhidaRepo,
        PostoSaudeRepository postoSaudeRepo
    ) {
        this.localDesejadoRepo = localDesejadoRepo;
        this.tipoInformacaoRepo = tipoInformacaoRepo;
        this.recebaEmailRepo = recebaEmailRepo;
        this.opcaoEscolhidaRepo = opcaoEscolhidaRepo;
        this.postoSaudeRepo = postoSaudeRepo;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        InformacoesProcessDTO pi = (InformacoesProcessDTO) delegateExecution.getVariable("processInstance");
        InformacoesDTO informacoesDTO = pi.getInformacoes();
        Integer int_random = (Integer) delegateExecution.getVariable("int_random");
        String covidInformationType = "error";
        String covidinformationLocal = "error";
        if (informacoesDTO.getTipoInformacao() != null) {
            TipoInformacao tipoInformacao = this.tipoInformacaoRepo.findById(informacoesDTO.getTipoInformacao().getId()).get();
            covidInformationType = tipoInformacao.getTipoInfo();
        }
        if (informacoesDTO.getLocalDesejado() != null) {
            LocalDesejado localDesejado = this.localDesejadoRepo.findById(informacoesDTO.getLocalDesejado().getId()).get();
            covidinformationLocal = localDesejado.getLocalCovid();
        }
        String to = pi.getInformacoes().getEmail();
        String subject = "[VACINA-JÁ] Resumo das informações in " + covidinformationLocal;
        Context context = new Context(Locale.getDefault());
        context.setVariable("Informacoes", informacoesDTO);
        context.setVariable("int_random", int_random);
        context.setVariable("covidInformationType", covidInformationType);
        context.setVariable("covidinformationLocal", covidinformationLocal);
        String content = templateEngine.process("InformacoesProcess/InformacoesSummaryEmail", context);
        mailService.sendEmail(to, subject, content, false, true);
    }
}
