package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.OpcaoEscolhidaDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OpcaoEscolhida} and its DTO {@link OpcaoEscolhidaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface OpcaoEscolhidaMapper extends EntityMapper<OpcaoEscolhidaDTO, OpcaoEscolhida> {
    @Named("opcaoInicial")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "opcaoInicial", source = "opcaoInicial")
    OpcaoEscolhidaDTO toDtoOpcaoInicial(OpcaoEscolhida opcaoEscolhida);
}
