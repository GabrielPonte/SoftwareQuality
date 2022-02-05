package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OpcaoEscolhidaMapperTest {

    private OpcaoEscolhidaMapper opcaoEscolhidaMapper;

    @BeforeEach
    public void setUp() {
        opcaoEscolhidaMapper = new OpcaoEscolhidaMapperImpl();
    }
}
