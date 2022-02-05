package com.mycompany.myapp.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecebaEmailMapperTest {

    private RecebaEmailMapper recebaEmailMapper;

    @BeforeEach
    public void setUp() {
        recebaEmailMapper = new RecebaEmailMapperImpl();
    }
}
