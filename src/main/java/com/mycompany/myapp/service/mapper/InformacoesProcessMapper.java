package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.InformacoesProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InformacoesProcess} and its DTO {@link InformacoesProcessDTO}.
 */
@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class, InformacoesMapper.class })
public interface InformacoesProcessMapper extends EntityMapper<InformacoesProcessDTO, InformacoesProcess> {
    @Mapping(target = "processInstance", source = "processInstance")
    @Mapping(target = "informacoes", source = "informacoes")
    InformacoesProcessDTO toDto(InformacoesProcess s);
}
