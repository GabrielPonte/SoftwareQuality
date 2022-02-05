package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.PostoSaudeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PostoSaude} and its DTO {@link PostoSaudeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PostoSaudeMapper extends EntityMapper<PostoSaudeDTO, PostoSaude> {
    @Named("nomePosto")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nomePosto", source = "nomePosto")
    PostoSaudeDTO toDtoNomePosto(PostoSaude postoSaude);
}
