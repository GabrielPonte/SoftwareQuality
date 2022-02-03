package com.mycompany.myapp.process.informacoesProcess;

import com.mycompany.myapp.domain.Informacoes;
import com.mycompany.myapp.domain.InformacoesProcess;
import com.mycompany.myapp.service.dto.InformacoesDTO;
import com.mycompany.myapp.service.dto.InformacoesProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskPreencherDadosMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    InformacoesProcessDTO toInformacoesProcessDTO(InformacoesProcess informacoesProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nomeCompleto", source = "nomeCompleto")
    @Mapping(target = "email", source = "email")
    @Mapping(target = "idade", source = "idade")
    @Mapping(target = "cpf", source = "cpf")
    @Mapping(target = "cep", source = "cep")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "cidade", source = "cidade")
    @Mapping(target = "endereco", source = "endereco")
    @Mapping(target = "complemento", source = "complemento")
    @Mapping(target = "qtdVacinas", source = "qtdVacinas")
    @Mapping(target = "sintomas", source = "sintomas")
    InformacoesDTO toInformacoesDTO(Informacoes informacoes);
}
