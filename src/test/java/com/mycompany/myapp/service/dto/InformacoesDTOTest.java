package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InformacoesDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(InformacoesDTO.class);
        InformacoesDTO informacoesDTO1 = new InformacoesDTO();
        informacoesDTO1.setId(1L);
        InformacoesDTO informacoesDTO2 = new InformacoesDTO();
        assertThat(informacoesDTO1).isNotEqualTo(informacoesDTO2);
        informacoesDTO2.setId(informacoesDTO1.getId());
        assertThat(informacoesDTO1).isEqualTo(informacoesDTO2);
        informacoesDTO2.setId(2L);
        assertThat(informacoesDTO1).isNotEqualTo(informacoesDTO2);
        informacoesDTO1.setId(null);
        assertThat(informacoesDTO1).isNotEqualTo(informacoesDTO2);
    }
}
