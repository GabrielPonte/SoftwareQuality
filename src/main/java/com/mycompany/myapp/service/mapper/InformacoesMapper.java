package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.InformacoesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Informacoes} and its DTO {@link InformacoesDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface InformacoesMapper extends EntityMapper<InformacoesDTO, Informacoes> {}
