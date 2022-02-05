package com.mycompany.myapp.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mycompany.myapp.IntegrationTest;
import com.mycompany.myapp.domain.Informacoes;
import com.mycompany.myapp.repository.InformacoesRepository;
import com.mycompany.myapp.service.dto.InformacoesDTO;
import com.mycompany.myapp.service.mapper.InformacoesMapper;
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
 * Integration tests for the {@link InformacoesResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InformacoesResourceIT {

    private static final String DEFAULT_NOME_COMPLETO = "AAAAAAAAAA";
    private static final String UPDATED_NOME_COMPLETO = "BBBBBBBBBB";

    private static final String DEFAULT_CPF = "AAAAAAAAAA";
    private static final String UPDATED_CPF = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

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

    private static final LocalDate DEFAULT_DATA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_HORA = "AAAAAAAAAA";
    private static final String UPDATED_HORA = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/informacoes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InformacoesRepository informacoesRepository;

    @Autowired
    private InformacoesMapper informacoesMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInformacoesMockMvc;

    private Informacoes informacoes;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Informacoes createEntity(EntityManager em) {
        Informacoes informacoes = new Informacoes()
            .nomeCompleto(DEFAULT_NOME_COMPLETO)
            .cpf(DEFAULT_CPF)
            .email(DEFAULT_EMAIL)
            .idade(DEFAULT_IDADE)
            .sintomas(DEFAULT_SINTOMAS)
            .qtdVacinas(DEFAULT_QTD_VACINAS)
            .cidade(DEFAULT_CIDADE)
            .estado(DEFAULT_ESTADO)
            .endereco(DEFAULT_ENDERECO)
            .complemento(DEFAULT_COMPLEMENTO)
            .cep(DEFAULT_CEP)
            .data(DEFAULT_DATA)
            .hora(DEFAULT_HORA);
        return informacoes;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Informacoes createUpdatedEntity(EntityManager em) {
        Informacoes informacoes = new Informacoes()
            .nomeCompleto(UPDATED_NOME_COMPLETO)
            .cpf(UPDATED_CPF)
            .email(UPDATED_EMAIL)
            .idade(UPDATED_IDADE)
            .sintomas(UPDATED_SINTOMAS)
            .qtdVacinas(UPDATED_QTD_VACINAS)
            .cidade(UPDATED_CIDADE)
            .estado(UPDATED_ESTADO)
            .endereco(UPDATED_ENDERECO)
            .complemento(UPDATED_COMPLEMENTO)
            .cep(UPDATED_CEP)
            .data(UPDATED_DATA)
            .hora(UPDATED_HORA);
        return informacoes;
    }

    @BeforeEach
    public void initTest() {
        informacoes = createEntity(em);
    }

    @Test
    @Transactional
    void getAllInformacoes() throws Exception {
        // Initialize the database
        informacoesRepository.saveAndFlush(informacoes);

        // Get all the informacoesList
        restInformacoesMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(informacoes.getId().intValue())))
            .andExpect(jsonPath("$.[*].nomeCompleto").value(hasItem(DEFAULT_NOME_COMPLETO)))
            .andExpect(jsonPath("$.[*].cpf").value(hasItem(DEFAULT_CPF)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].idade").value(hasItem(DEFAULT_IDADE)))
            .andExpect(jsonPath("$.[*].sintomas").value(hasItem(DEFAULT_SINTOMAS)))
            .andExpect(jsonPath("$.[*].qtdVacinas").value(hasItem(DEFAULT_QTD_VACINAS)))
            .andExpect(jsonPath("$.[*].cidade").value(hasItem(DEFAULT_CIDADE)))
            .andExpect(jsonPath("$.[*].estado").value(hasItem(DEFAULT_ESTADO)))
            .andExpect(jsonPath("$.[*].endereco").value(hasItem(DEFAULT_ENDERECO)))
            .andExpect(jsonPath("$.[*].complemento").value(hasItem(DEFAULT_COMPLEMENTO)))
            .andExpect(jsonPath("$.[*].cep").value(hasItem(DEFAULT_CEP)))
            .andExpect(jsonPath("$.[*].data").value(hasItem(DEFAULT_DATA.toString())))
            .andExpect(jsonPath("$.[*].hora").value(hasItem(DEFAULT_HORA)));
    }

    @Test
    @Transactional
    void getInformacoes() throws Exception {
        // Initialize the database
        informacoesRepository.saveAndFlush(informacoes);

        // Get the informacoes
        restInformacoesMockMvc
            .perform(get(ENTITY_API_URL_ID, informacoes.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(informacoes.getId().intValue()))
            .andExpect(jsonPath("$.nomeCompleto").value(DEFAULT_NOME_COMPLETO))
            .andExpect(jsonPath("$.cpf").value(DEFAULT_CPF))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.idade").value(DEFAULT_IDADE))
            .andExpect(jsonPath("$.sintomas").value(DEFAULT_SINTOMAS))
            .andExpect(jsonPath("$.qtdVacinas").value(DEFAULT_QTD_VACINAS))
            .andExpect(jsonPath("$.cidade").value(DEFAULT_CIDADE))
            .andExpect(jsonPath("$.estado").value(DEFAULT_ESTADO))
            .andExpect(jsonPath("$.endereco").value(DEFAULT_ENDERECO))
            .andExpect(jsonPath("$.complemento").value(DEFAULT_COMPLEMENTO))
            .andExpect(jsonPath("$.cep").value(DEFAULT_CEP))
            .andExpect(jsonPath("$.data").value(DEFAULT_DATA.toString()))
            .andExpect(jsonPath("$.hora").value(DEFAULT_HORA));
    }

    @Test
    @Transactional
    void getNonExistingInformacoes() throws Exception {
        // Get the informacoes
        restInformacoesMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
