package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocalDesejadoMapperTest {

    private LocalDesejadoMapper localDesejadoMapper;

    @BeforeEach
    public void setUp() {
        localDesejadoMapper = new LocalDesejadoMapperImpl();
    }
}
