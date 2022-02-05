package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OpcaoEscolhidaDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OpcaoEscolhidaDTO.class);
        OpcaoEscolhidaDTO opcaoEscolhidaDTO1 = new OpcaoEscolhidaDTO();
        opcaoEscolhidaDTO1.setId(1L);
        OpcaoEscolhidaDTO opcaoEscolhidaDTO2 = new OpcaoEscolhidaDTO();
        assertThat(opcaoEscolhidaDTO1).isNotEqualTo(opcaoEscolhidaDTO2);
        opcaoEscolhidaDTO2.setId(opcaoEscolhidaDTO1.getId());
        assertThat(opcaoEscolhidaDTO1).isEqualTo(opcaoEscolhidaDTO2);
        opcaoEscolhidaDTO2.setId(2L);
        assertThat(opcaoEscolhidaDTO1).isNotEqualTo(opcaoEscolhidaDTO2);
        opcaoEscolhidaDTO1.setId(null);
        assertThat(opcaoEscolhidaDTO1).isNotEqualTo(opcaoEscolhidaDTO2);
    }
}
