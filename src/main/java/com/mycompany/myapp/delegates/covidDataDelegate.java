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
import com.mycompany.myapp.service.dto.InformacoesDTO;
import com.mycompany.myapp.service.dto.InformacoesProcessDTO;
import com.mycompany.myapp.service.dto.LocalDesejadoDTO;
import com.mycompany.myapp.service.dto.OpcaoEscolhidaDTO;
import com.mycompany.myapp.service.dto.PostoSaudeDTO;
import com.mycompany.myapp.service.dto.RecebaEmailDTO;
import com.mycompany.myapp.service.dto.TipoInformacaoDTO;
import java.util.Random;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class covidDataDelegate implements JavaDelegate {

    private final LocalDesejadoRepository localDesejadoRepo;
    private final TipoInformacaoRepository tipoInformacaoRepo;
    private final RecebaEmailRepository recebaEmailRepo;
    private final OpcaoEscolhidaRepository opcaoEscolhidaRepo;
    private final PostoSaudeRepository postoSaudeRepo;

    public covidDataDelegate(
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

        String covidInformationType = "error";
        String covidinformationLocal = "error";
        String email_str = "error";
        String escolha_str = "error";
        String posto_str = "error";

        if (informacoesDTO.getTipoInformacao() != null) {
            TipoInformacao tipoInformacao = this.tipoInformacaoRepo.findById(informacoesDTO.getTipoInformacao().getId()).get();
            covidInformationType = tipoInformacao.getTipoInfo();
        }

        if (informacoesDTO.getLocalDesejado() != null) {
            LocalDesejado localDesejado = this.localDesejadoRepo.findById(informacoesDTO.getLocalDesejado().getId()).get();
            covidinformationLocal = localDesejado.getLocalCovid();
        }

        if (informacoesDTO.getRecebaEmail() != null) {
            RecebaEmail recebaEmail = this.recebaEmailRepo.findById(informacoesDTO.getRecebaEmail().getId()).get();
            email_str = recebaEmail.getOpcaoEmail();
        }

        if (informacoesDTO.getOpcaoEscolhida() != null) {
            OpcaoEscolhida opcaoEscolhida = this.opcaoEscolhidaRepo.findById(informacoesDTO.getOpcaoEscolhida().getId()).get();
            escolha_str = opcaoEscolhida.getOpcaoInicial();
        }

        if (informacoesDTO.getPostoSaude() != null) {
            PostoSaude postoSaude = this.postoSaudeRepo.findById(informacoesDTO.getPostoSaude().getId()).get();
            posto_str = postoSaude.getNomePosto();
        }
        System.out.println("=================================================");
        System.out.println("============== SISTEMA VACINA JÁ ================");
        System.out.println("=================================================");
        System.out.println("Opcao Inicial Escolhida: " + escolha_str);
        System.out.println("Opção de receber Email: " + email_str);
        System.out.println("Info: " + covidInformationType);
        System.out.println("Local: " + covidinformationLocal);
        //System.out.println("Posto:" + posto_str);
        System.out.println("=================================================");
        System.out.println("=================================================");
        System.out.println("=================================================");

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
        System.out.println("=================================================");
        System.out.println("=================================================");
    }
}
