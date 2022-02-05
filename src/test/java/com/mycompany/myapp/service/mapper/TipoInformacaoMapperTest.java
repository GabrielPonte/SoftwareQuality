package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TipoInformacaoMapperTest {

    private TipoInformacaoMapper tipoInformacaoMapper;

    @BeforeEach
    public void setUp() {
        tipoInformacaoMapper = new TipoInformacaoMapperImpl();
    }
}
