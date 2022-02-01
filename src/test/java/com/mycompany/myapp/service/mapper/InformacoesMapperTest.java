package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InformacoesMapperTest {

    private InformacoesMapper informacoesMapper;

    @BeforeEach
    public void setUp() {
        informacoesMapper = new InformacoesMapperImpl();
    }
}
