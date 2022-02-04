package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.LocalDesejadoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LocalDesejado} and its DTO {@link LocalDesejadoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LocalDesejadoMapper extends EntityMapper<LocalDesejadoDTO, LocalDesejado> {
    @Named("localCovid")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "localCovid", source = "localCovid")
    LocalDesejadoDTO toDtoLocalCovid(LocalDesejado localDesejado);
}
