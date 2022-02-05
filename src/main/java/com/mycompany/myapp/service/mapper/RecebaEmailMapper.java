package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.RecebaEmailDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RecebaEmail} and its DTO {@link RecebaEmailDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RecebaEmailMapper extends EntityMapper<RecebaEmailDTO, RecebaEmail> {
    @Named("opcaoEmail")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "opcaoEmail", source = "opcaoEmail")
    RecebaEmailDTO toDtoOpcaoEmail(RecebaEmail recebaEmail);
}
