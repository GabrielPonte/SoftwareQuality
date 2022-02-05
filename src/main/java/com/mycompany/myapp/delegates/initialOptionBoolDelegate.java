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
public class initialOptionBoolDelegate implements JavaDelegate {

    private final OpcaoEscolhidaRepository opcaoEscolhidaRepo;

    public initialOptionBoolDelegate(OpcaoEscolhidaRepository opcaoEscolhidaRepo) {
        this.opcaoEscolhidaRepo = opcaoEscolhidaRepo;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        InformacoesProcessDTO pi = (InformacoesProcessDTO) delegateExecution.getVariable("processInstance");
        InformacoesDTO informacoesDTO = pi.getInformacoes();

        String escolha_str = "error";

        Boolean initialOptionBool = false;

        if (informacoesDTO.getOpcaoEscolhida() != null) {
            OpcaoEscolhida opcaoEscolhida = this.opcaoEscolhidaRepo.findById(informacoesDTO.getOpcaoEscolhida().getId()).get();
            escolha_str = opcaoEscolhida.getOpcaoInicial();
        }

        if (escolha_str.contains("Agendamento")) {
            initialOptionBool = true;
        }
        delegateExecution.setVariable("initialOptionBool", initialOptionBool);
    }
}
