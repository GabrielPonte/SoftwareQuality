package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.TipoInformacaoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoInformacao} and its DTO {@link TipoInformacaoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoInformacaoMapper extends EntityMapper<TipoInformacaoDTO, TipoInformacao> {
    @Named("tipoInfo")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "tipoInfo", source = "tipoInfo")
    TipoInformacaoDTO toDtoTipoInfo(TipoInformacao tipoInformacao);
}
