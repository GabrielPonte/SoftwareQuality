package com.mycompany.myapp.process.informacoesProcess;

import com.mycompany.myapp.domain.Informacoes;
import com.mycompany.myapp.domain.InformacoesProcess;
import com.mycompany.myapp.domain.OpcaoEscolhida;
import com.mycompany.myapp.service.dto.InformacoesDTO;
import com.mycompany.myapp.service.dto.InformacoesProcessDTO;
import com.mycompany.myapp.service.dto.OpcaoEscolhidaDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskInitialOptionMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    InformacoesProcessDTO toInformacoesProcessDTO(InformacoesProcess informacoesProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "opcaoEscolhida", source = "opcaoEscolhida")
    InformacoesDTO toInformacoesDTO(Informacoes informacoes);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "opcaoInicial", source = "opcaoInicial")
    OpcaoEscolhidaDTO toOpcaoEscolhidaDTO(OpcaoEscolhida opcaoInicial);
}
