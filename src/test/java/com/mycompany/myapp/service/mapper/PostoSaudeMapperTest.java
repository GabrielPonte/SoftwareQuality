package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PostoSaudeMapperTest {

    private PostoSaudeMapper postoSaudeMapper;

    @BeforeEach
    public void setUp() {
        postoSaudeMapper = new PostoSaudeMapperImpl();
    }
}
