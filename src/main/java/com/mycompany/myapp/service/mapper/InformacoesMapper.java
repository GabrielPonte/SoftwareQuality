package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.InformacoesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Informacoes} and its DTO {@link InformacoesDTO}.
 */
@Mapper(
    componentModel = "spring",
    uses = {
        LocalDesejadoMapper.class, OpcaoEscolhidaMapper.class, PostoSaudeMapper.class, RecebaEmailMapper.class, TipoInformacaoMapper.class,
    }
)
public interface InformacoesMapper extends EntityMapper<InformacoesDTO, Informacoes> {
    @Mapping(target = "localDesejado", source = "localDesejado", qualifiedByName = "localCovid")
    @Mapping(target = "opcaoEscolhida", source = "opcaoEscolhida", qualifiedByName = "opcaoInicial")
    @Mapping(target = "postoSaude", source = "postoSaude", qualifiedByName = "nomePosto")
    @Mapping(target = "recebaEmail", source = "recebaEmail", qualifiedByName = "opcaoEmail")
    @Mapping(target = "tipoInformacao", source = "tipoInformacao", qualifiedByName = "tipoInfo")
    InformacoesDTO toDto(Informacoes s);
}
