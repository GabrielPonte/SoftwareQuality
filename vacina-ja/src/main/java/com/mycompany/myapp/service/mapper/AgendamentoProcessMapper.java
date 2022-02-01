package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.AgendamentoProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AgendamentoProcess} and its DTO {@link AgendamentoProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, AgendamentoMapper.class })
public interface AgendamentoProcessMapper extends EntityMapper<AgendamentoProcessDTO, AgendamentoProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "agendamento", source = "agendamento")
    AgendamentoProcessDTO toDto(AgendamentoProcess s);
}
