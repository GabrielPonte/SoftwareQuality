package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AgendamentoMapperTest {

    private AgendamentoMapper agendamentoMapper;

    @BeforeEach
    public void setUp() {
        agendamentoMapper = new AgendamentoMapperImpl();
    }
}
