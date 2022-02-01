package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InformacoesTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Informacoes.class);
        Informacoes informacoes1 = new Informacoes();
        informacoes1.setId(1L);
        Informacoes informacoes2 = new Informacoes();
        informacoes2.setId(informacoes1.getId());
        assertThat(informacoes1).isEqualTo(informacoes2);
        informacoes2.setId(2L);
        assertThat(informacoes1).isNotEqualTo(informacoes2);
        informacoes1.setId(null);
        assertThat(informacoes1).isNotEqualTo(informacoes2);
    }
}
