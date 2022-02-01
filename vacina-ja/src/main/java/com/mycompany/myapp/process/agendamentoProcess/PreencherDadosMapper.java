package com.mycompany.myapp.process.agendamentoProcess;

import com.mycompany.myapp.domain.Agendamento;
import com.mycompany.myapp.domain.AgendamentoProcess;
import com.mycompany.myapp.service.dto.AgendamentoDTO;
import com.mycompany.myapp.service.dto.AgendamentoProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface PreencherDadosMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    AgendamentoProcessDTO toAgendamentoProcessDTO(AgendamentoProcess agendamentoProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nomeCompleto", source = "nomeCompleto")
    @Mapping(target = "idade", source = "idade")
    @Mapping(target = "CPF", source = "CPF")
    @Mapping(target = "CEP", source = "CEP")
    @Mapping(target = "endereco", source = "endereco")
    @Mapping(target = "complemento", source = "complemento")
    @Mapping(target = "qtdVacinas", source = "qtdVacinas")
    @Mapping(target = "sintomas", source = "sintomas")
    AgendamentoDTO toAgendamentoDTO(Agendamento agendamento);
}
