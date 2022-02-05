package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OpcaoEscolhidaTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcaoEscolhida.class);
        OpcaoEscolhida opcaoEscolhida1 = new OpcaoEscolhida();
        opcaoEscolhida1.setId(1L);
        OpcaoEscolhida opcaoEscolhida2 = new OpcaoEscolhida();
        opcaoEscolhida2.setId(opcaoEscolhida1.getId());
        assertThat(opcaoEscolhida1).isEqualTo(opcaoEscolhida2);
        opcaoEscolhida2.setId(2L);
        assertThat(opcaoEscolhida1).isNotEqualTo(opcaoEscolhida2);
        opcaoEscolhida1.setId(null);
        assertThat(opcaoEscolhida1).isNotEqualTo(opcaoEscolhida2);
    }
}
