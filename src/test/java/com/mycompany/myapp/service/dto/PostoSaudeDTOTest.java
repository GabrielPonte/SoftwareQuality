package com.mycompany.myapp.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PostoSaudeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PostoSaudeDTO.class);
        PostoSaudeDTO postoSaudeDTO1 = new PostoSaudeDTO();
        postoSaudeDTO1.setId(1L);
        PostoSaudeDTO postoSaudeDTO2 = new PostoSaudeDTO();
        assertThat(postoSaudeDTO1).isNotEqualTo(postoSaudeDTO2);
        postoSaudeDTO2.setId(postoSaudeDTO1.getId());
        assertThat(postoSaudeDTO1).isEqualTo(postoSaudeDTO2);
        postoSaudeDTO2.setId(2L);
        assertThat(postoSaudeDTO1).isNotEqualTo(postoSaudeDTO2);
        postoSaudeDTO1.setId(null);
        assertThat(postoSaudeDTO1).isNotEqualTo(postoSaudeDTO2);
    }
}
