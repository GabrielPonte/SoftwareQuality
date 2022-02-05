package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RecebaEmailTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RecebaEmail.class);
        RecebaEmail recebaEmail1 = new RecebaEmail();
        recebaEmail1.setId(1L);
        RecebaEmail recebaEmail2 = new RecebaEmail();
        recebaEmail2.setId(recebaEmail1.getId());
        assertThat(recebaEmail1).isEqualTo(recebaEmail2);
        recebaEmail2.setId(2L);
        assertThat(recebaEmail1).isNotEqualTo(recebaEmail2);
        recebaEmail1.setId(null);
        assertThat(recebaEmail1).isNotEqualTo(recebaEmail2);
    }
}
