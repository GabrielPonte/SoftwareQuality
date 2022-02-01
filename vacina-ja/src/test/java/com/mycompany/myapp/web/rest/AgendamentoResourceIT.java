package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Agendamento;
import com.mycompany.myapp.repository.AgendamentoRepository;
import com.mycompany.myapp.service.dto.AgendamentoDTO;
import com.mycompany.myapp.service.mapper.AgendamentoMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link AgendamentoResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AgendamentoResourceIT {

    private static final String DEFAULT_NOME_COMPLETO = "AAAAAAAAAA";
    private static final String UPDATED_NOME_COMPLETO = "BBBBBBBBBB";

    private static final String DEFAULT_CPF = "AAAAAAAAAA";
    private static final String UPDATED_CPF = "BBBBBBBBBB";

    private static final Integer DEFAULT_IDADE = 1;
    private static final Integer UPDATED_IDADE = 2;

    private static final String DEFAULT_SINTOMAS = "AAAAAAAAAA";
    private static final String UPDATED_SINTOMAS = "BBBBBBBBBB";

    private static final Integer DEFAULT_QTD_VACINAS = 1;
    private static final Integer UPDATED_QTD_VACINAS = 2;

    private static final String DEFAULT_CIDADE = "AAAAAAAAAA";
    private static final String UPDATED_CIDADE = "BBBBBBBBBB";

    private static final String DEFAULT_ESTADO = "AAAAAAAAAA";
    private static final String UPDATED_ESTADO = "BBBBBBBBBB";

    private static final String DEFAULT_ENDERECO = "AAAAAAAAAA";
    private static final String UPDATED_ENDERECO = "BBBBBBBBBB";

    private static final String DEFAULT_COMPLEMENTO = "AAAAAAAAAA";
    private static final String UPDATED_COMPLEMENTO = "BBBBBBBBBB";

    private static final String DEFAULT_CEP = "AAAAAAAAAA";
    private static final String UPDATED_CEP = "BBBBBBBBBB";

    private static final String DEFAULT_NOME_POSTO = "AAAAAAAAAA";
    private static final String UPDATED_NOME_POSTO = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_HORA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_HORA = LocalDate.now(ZoneId.systemDefault());

    private static final String ENTITY_API_URL = "/api/agendamentos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private AgendamentoMapper agendamentoMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAgendamentoMockMvc;

    private Agendamento agendamento;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agendamento createEntity(EntityManager em) {
        Agendamento agendamento = new Agendamento()
            .nomeCompleto(DEFAULT_NOME_COMPLETO)
            .CPF(DEFAULT_CPF)
            .idade(DEFAULT_IDADE)
            .sintomas(DEFAULT_SINTOMAS)
            .qtdVacinas(DEFAULT_QTD_VACINAS)
            .cidade(DEFAULT_CIDADE)
            .estado(DEFAULT_ESTADO)
            .endereco(DEFAULT_ENDERECO)
            .complemento(DEFAULT_COMPLEMENTO)
            .CEP(DEFAULT_CEP)
            .nomePosto(DEFAULT_NOME_POSTO)
            .data(DEFAULT_DATA)
            .hora(DEFAULT_HORA);
        return agendamento;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Agendamento createUpdatedEntity(EntityManager em) {
        Agendamento agendamento = new Agendamento()
            .nomeCompleto(UPDATED_NOME_COMPLETO)
            .CPF(UPDATED_CPF)
            .idade(UPDATED_IDADE)
            .sintomas(UPDATED_SINTOMAS)
            .qtdVacinas(UPDATED_QTD_VACINAS)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .endereco(UPDATED_ENDERECO)
            .complemento(UPDATED_COMPLEMENTO)
            .CEP(UPDATED_CEP)
            .nomePosto(UPDATED_NOME_POSTO)
            .data(UPDATED_DATA)
            .hora(UPDATED_HORA);
        return agendamento;
    }

    @BeforeEach
    public void initTest() {
        agendamento = createEntity(em);
    }

    @Test
    @Transactional
    void getAllAgendamentos() throws Exception {
        // Initialize the database
        agendamentoRepository.saveAndFlush(agendamento);

        // Get all the agendamentoList
        restAgendamentoMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(agendamento.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomeCompleto").value(hasItem(DEFAULT_NOME_COMPLETO)))
            .andExpect(jsonPath("$.[*].CPF").value(hasItem(DEFAULT_CPF)))
            .andExpect(jsonPath("$.[*].idade").value(hasItem(DEFAULT_IDADE)))
            .andExpect(jsonPath("$.[*].sintomas").value(hasItem(DEFAULT_SINTOMAS)))
            .andExpect(jsonPath("$.[*].qtdVacinas").value(hasItem(DEFAULT_QTD_VACINAS)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO)))
            .andExpect(jsonPath("$.[*].complemento").value(hasItem(DEFAULT_COMPLEMENTO)))
            .andExpect(jsonPath("$.[*].CEP").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].nomePosto").value(hasItem(DEFAULT_NOME_POSTO)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].hora").value(hasItem(DEFAULT_HORA.toString())));
    }

    @Test
    @Transactional
    void getAgendamento() throws Exception {
        // Initialize the database
        agendamentoRepository.saveAndFlush(agendamento);

        // Get the agendamento
        restAgendamentoMockMvc
            .perform(get(ENTITY_API_URL_ID, agendamento.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(agendamento.getId().intValue()))
            .andExpect(jsonPath("$.nomeCompleto").value(DEFAULT_NOME_COMPLETO))
            .andExpect(jsonPath("$.CPF").value(DEFAULT_CPF))
            .andExpect(jsonPath("$.idade").value(DEFAULT_IDADE))
            .andExpect(jsonPath("$.sintomas").value(DEFAULT_SINTOMAS))
            .andExpect(jsonPath("$.qtdVacinas").value(DEFAULT_QTD_VACINAS))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.endereco").value(DEFAULT_ENDERECO))
            .andExpect(jsonPath("$.complemento").value(DEFAULT_COMPLEMENTO))
            .andExpect(jsonPath("$.CEP").value(DEFAULT_CEP))
            .andExpect(jsonPath("$.nomePosto").value(DEFAULT_NOME_POSTO))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.hora").value(DEFAULT_HORA.toString()));
    }

    @Test
    @Transactional
    void getNonExistingAgendamento() throws Exception {
        // Get the agendamento
        restAgendamentoMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
