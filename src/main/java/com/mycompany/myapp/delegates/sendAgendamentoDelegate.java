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
public class sendAgendamentoDelegate implements JavaDelegate {

    @Autowired
    MailService mailService;

    @Autowired
    SpringTemplateEngine templateEngine;

    private final LocalDesejadoRepository localDesejadoRepo;
    private final TipoInformacaoRepository tipoInformacaoRepo;
    private final RecebaEmailRepository recebaEmailRepo;
    private final OpcaoEscolhidaRepository opcaoEscolhidaRepo;
    private final PostoSaudeRepository postoSaudeRepo;

    public sendAgendamentoDelegate(
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

        String nomePosto = "error";
        String estadoPosto = "error";
        String cidadePosto = "error";

        if (informacoesDTO.getPostoSaude() != null) {
            PostoSaude postoSaude = this.postoSaudeRepo.findById(informacoesDTO.getPostoSaude().getId()).get();
            nomePosto = postoSaude.getNomePosto();
            estadoPosto = postoSaude.getEstadoPosto();
            cidadePosto = postoSaude.getCidadePosto();
        }
        String nomeCompleto = pi.getInformacoes().getNomeCompleto();
        String to = pi.getInformacoes().getEmail();
        String subject = "[VACINA-JÁ] Confirmação do agendamento do paciente " + nomeCompleto;
        Context context = new Context(Locale.getDefault());

        context.setVariable("Informacoes", informacoesDTO);
        context.setVariable("nomePosto", nomePosto);
        context.setVariable("estadoPosto", estadoPosto);
        context.setVariable("cidadePosto", cidadePosto);

        String content = templateEngine.process("InformacoesProcess/AgendamentoSummaryEmail", context);
        mailService.sendEmail(to, subject, content, false, true);
    }
}
