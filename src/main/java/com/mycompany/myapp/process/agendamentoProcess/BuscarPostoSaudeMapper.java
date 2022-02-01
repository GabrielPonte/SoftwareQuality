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
public interface BuscarPostoSaudeMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    AgendamentoProcessDTO toAgendamentoProcessDTO(AgendamentoProcess agendamentoProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "cidade", source = "cidade")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "nomePosto", source = "nomePosto")
    AgendamentoDTO toAgendamentoDTO(Agendamento agendamento);
}
