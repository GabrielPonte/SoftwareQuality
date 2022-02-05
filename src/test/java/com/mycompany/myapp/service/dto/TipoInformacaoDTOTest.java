package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TipoInformacaoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoInformacaoDTO.class);
        TipoInformacaoDTO tipoInformacaoDTO1 = new TipoInformacaoDTO();
        tipoInformacaoDTO1.setId(1L);
        TipoInformacaoDTO tipoInformacaoDTO2 = new TipoInformacaoDTO();
        assertThat(tipoInformacaoDTO1).isNotEqualTo(tipoInformacaoDTO2);
        tipoInformacaoDTO2.setId(tipoInformacaoDTO1.getId());
        assertThat(tipoInformacaoDTO1).isEqualTo(tipoInformacaoDTO2);
        tipoInformacaoDTO2.setId(2L);
        assertThat(tipoInformacaoDTO1).isNotEqualTo(tipoInformacaoDTO2);
        tipoInformacaoDTO1.setId(null);
        assertThat(tipoInformacaoDTO1).isNotEqualTo(tipoInformacaoDTO2);
    }
}
