package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class TipoInformacaoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoInformacao.class);
        TipoInformacao tipoInformacao1 = new TipoInformacao();
        tipoInformacao1.setId(1L);
        TipoInformacao tipoInformacao2 = new TipoInformacao();
        tipoInformacao2.setId(tipoInformacao1.getId());
        assertThat(tipoInformacao1).isEqualTo(tipoInformacao2);
        tipoInformacao2.setId(2L);
        assertThat(tipoInformacao1).isNotEqualTo(tipoInformacao2);
        tipoInformacao1.setId(null);
        assertThat(tipoInformacao1).isNotEqualTo(tipoInformacao2);
    }
}
