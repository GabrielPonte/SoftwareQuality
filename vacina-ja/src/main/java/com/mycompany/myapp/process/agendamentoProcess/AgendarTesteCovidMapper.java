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
public interface AgendarTesteCovidMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    AgendamentoProcessDTO toAgendamentoProcessDTO(AgendamentoProcess agendamentoProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "data", source = "data")
    @Mapping(target = "hora", source = "hora")
    AgendamentoDTO toAgendamentoDTO(Agendamento agendamento);
}
