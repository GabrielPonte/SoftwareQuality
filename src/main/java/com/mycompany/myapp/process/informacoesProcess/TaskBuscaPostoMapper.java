package com.mycompany.myapp.process.informacoesProcess;

import com.mycompany.myapp.domain.Informacoes;
import com.mycompany.myapp.domain.InformacoesProcess;
import com.mycompany.myapp.service.dto.InformacoesDTO;
import com.mycompany.myapp.service.dto.InformacoesProcessDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskBuscaPostoMapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    InformacoesProcessDTO toInformacoesProcessDTO(InformacoesProcess informacoesProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "cidade", source = "cidade")
    @Mapping(target = "estado", source = "estado")
    @Mapping(target = "nomePosto", source = "nomePosto")
    InformacoesDTO toInformacoesDTO(Informacoes informacoes);
}
