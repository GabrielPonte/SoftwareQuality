package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LocalDesejadoDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocalDesejadoDTO.class);
        LocalDesejadoDTO localDesejadoDTO1 = new LocalDesejadoDTO();
        localDesejadoDTO1.setId(1L);
        LocalDesejadoDTO localDesejadoDTO2 = new LocalDesejadoDTO();
        assertThat(localDesejadoDTO1).isNotEqualTo(localDesejadoDTO2);
        localDesejadoDTO2.setId(localDesejadoDTO1.getId());
        assertThat(localDesejadoDTO1).isEqualTo(localDesejadoDTO2);
        localDesejadoDTO2.setId(2L);
        assertThat(localDesejadoDTO1).isNotEqualTo(localDesejadoDTO2);
        localDesejadoDTO1.setId(null);
        assertThat(localDesejadoDTO1).isNotEqualTo(localDesejadoDTO2);
    }
}
