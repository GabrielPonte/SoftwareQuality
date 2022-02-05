package com.mycompany.myapp.process.informacoesProcess;

import com.mycompany.myapp.domain.Informacoes;
import com.mycompany.myapp.domain.InformacoesProcess;
import com.mycompany.myapp.domain.RecebaEmail;
import com.mycompany.myapp.service.dto.InformacoesDTO;
import com.mycompany.myapp.service.dto.InformacoesProcessDTO;
import com.mycompany.myapp.service.dto.RecebaEmailDTO;
import org.akip.service.mapper.ProcessInstanceMapper;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ProcessInstanceMapper.class })
public interface TaskRecebaEmailC19Mapper {
    @Mapping(target = "processInstance", source = "processInstance", qualifiedByName = "loadTaskContext")
    InformacoesProcessDTO toInformacoesProcessDTO(InformacoesProcess informacoesProcess);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "receberEmail", source = "receberEmail")
    @Mapping(target = "recebaEmail", source = "recebaEmail")
    InformacoesDTO toInformacoesDTO(Informacoes informacoes);

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "opcaoEmail", source = "opcaoEmail")
    RecebaEmailDTO toRecebaEmailDTO(RecebaEmail opcaoEmail);
}
