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
import java.time.LocalDate;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class agendamentoDelegate implements JavaDelegate {

    private final LocalDesejadoRepository localDesejadoRepo;
    private final TipoInformacaoRepository tipoInformacaoRepo;
    private final RecebaEmailRepository recebaEmailRepo;
    private final OpcaoEscolhidaRepository opcaoEscolhidaRepo;
    private final PostoSaudeRepository postoSaudeRepo;

    public agendamentoDelegate(
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
        LocalDate localDate = pi.getInformacoes().getData();
        String hora = pi.getInformacoes().getHora();

        String escolha_str = "error";
        String nomePosto = "error";
        String estadoPosto = "error";
        String cidadePosto = "error";

        if (informacoesDTO.getOpcaoEscolhida() != null) {
            OpcaoEscolhida opcaoEscolhida = this.opcaoEscolhidaRepo.findById(informacoesDTO.getOpcaoEscolhida().getId()).get();
            escolha_str = opcaoEscolhida.getOpcaoInicial();
        }
        if (informacoesDTO.getPostoSaude() != null) {
            PostoSaude postoSaude = this.postoSaudeRepo.findById(informacoesDTO.getPostoSaude().getId()).get();
            nomePosto = postoSaude.getNomePosto();
            estadoPosto = postoSaude.getEstadoPosto();
            cidadePosto = postoSaude.getCidadePosto();
        }
        System.out.println("=================================================");
        System.out.println("============== SISTEMA VACINA JÁ ================");
        System.out.println("=================================================");
        System.out.println("Opcao Inicial Escolhida: " + escolha_str);
        System.out.println("Posto: " + nomePosto);
        System.out.println("Local: " + estadoPosto + ", " + cidadePosto);
        System.out.println("=================================================");
        System.out.println("=================================================");
        System.out.println("=================================================");
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
        System.out.println("Posto: " + nomePosto);
        System.out.println("Local: " + estadoPosto + ", " + cidadePosto);
        System.out.println("Data: " + localDate);
        System.out.println("Hora: " + hora);
        System.out.println("=================================================");
    }
}
