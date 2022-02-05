package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PostoSaudeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PostoSaude.class);
        PostoSaude postoSaude1 = new PostoSaude();
        postoSaude1.setId(1L);
        PostoSaude postoSaude2 = new PostoSaude();
        postoSaude2.setId(postoSaude1.getId());
        assertThat(postoSaude1).isEqualTo(postoSaude2);
        postoSaude2.setId(2L);
        assertThat(postoSaude1).isNotEqualTo(postoSaude2);
        postoSaude1.setId(null);
        assertThat(postoSaude1).isNotEqualTo(postoSaude2);
    }
}
