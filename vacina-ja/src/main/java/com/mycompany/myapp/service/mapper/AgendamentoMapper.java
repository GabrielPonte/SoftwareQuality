package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.AgendamentoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Agendamento} and its DTO {@link AgendamentoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AgendamentoMapper extends EntityMapper<AgendamentoDTO, Agendamento> {}
