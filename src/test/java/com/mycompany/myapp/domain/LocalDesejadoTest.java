package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class LocalDesejadoTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(LocalDesejado.class);
        LocalDesejado localDesejado1 = new LocalDesejado();
        localDesejado1.setId(1L);
        LocalDesejado localDesejado2 = new LocalDesejado();
        localDesejado2.setId(localDesejado1.getId());
        assertThat(localDesejado1).isEqualTo(localDesejado2);
        localDesejado2.setId(2L);
        assertThat(localDesejado1).isNotEqualTo(localDesejado2);
        localDesejado1.setId(null);
        assertThat(localDesejado1).isNotEqualTo(localDesejado2);
    }
}
