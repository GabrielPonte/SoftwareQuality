package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RecebaEmailDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecebaEmailDTO.class);
        RecebaEmailDTO recebaEmailDTO1 = new RecebaEmailDTO();
        recebaEmailDTO1.setId(1L);
        RecebaEmailDTO recebaEmailDTO2 = new RecebaEmailDTO();
        assertThat(recebaEmailDTO1).isNotEqualTo(recebaEmailDTO2);
        recebaEmailDTO2.setId(recebaEmailDTO1.getId());
        assertThat(recebaEmailDTO1).isEqualTo(recebaEmailDTO2);
        recebaEmailDTO2.setId(2L);
        assertThat(recebaEmailDTO1).isNotEqualTo(recebaEmailDTO2);
        recebaEmailDTO1.setId(null);
        assertThat(recebaEmailDTO1).isNotEqualTo(recebaEmailDTO2);
    }
}
