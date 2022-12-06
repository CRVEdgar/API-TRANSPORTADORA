package com.example.apitrasnportadora.api.controller;

import com.example.apitrasnportadora.api.model.convert.FreteConvertAssembler;
import com.example.apitrasnportadora.api.model.convert.FreteConvertDISAssembler;
import com.example.apitrasnportadora.api.model.dto.input.FreteInput;
import com.example.apitrasnportadora.api.model.dto.response.FreteResponse;
import com.example.apitrasnportadora.builderObjects.FreteBuilder;
import com.example.apitrasnportadora.domain.model.Cliente;
import com.example.apitrasnportadora.domain.model.Frete;
import com.example.apitrasnportadora.domain.repository.ClienteRepository;
import com.example.apitrasnportadora.domain.service.FreteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static com.example.apitrasnportadora.builderObjects.ClienteBuilder.clientMock;
import static com.example.apitrasnportadora.builderObjects.FreteBuilder.*;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class FreteControllerTest {

    @InjectMocks
    private FreteController controller;

    @Mock
    private FreteService service;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private FreteConvertAssembler freteConvertAssembler;

    @Mock
    private FreteConvertDISAssembler freteConvertDISAssembler;

    private final String BASE_URL = "/fretes";
    @MockBean
    private MockMvc mockMvc;

    private ObjectMapper mapper;

    @BeforeEach
    public void setUp() {
        mapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    @DisplayName("deve retornar a lista de fretes de um cliente e o status 200 ")
    public void fretesClinte() throws Exception {
        List<Frete> fretesList = fretesSet().stream().toList();

        when(clienteRepository.findById(anyLong()))
                .thenReturn(Optional.of(clientMock()));

        when(service.fretesDoCliente(any(Cliente.class)))
                .thenReturn(fretesList);

        when(freteConvertAssembler.convert_Lista_para_DTO(anyList()))
                .thenReturn(getListFreteResponse());

        List<FreteResponse> freteResponseList = controller.fretesDoCliente(getFreteInputBuilder());


        mockMvc.perform(get(BASE_URL+"/fretes-Cliente"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(fretesList.size())))
                .andExpect(jsonPath("$[0].descricao", is("Segundo frete")));

        verify(service, atLeastOnce()).fretesDoCliente(any(Cliente.class));
        assertFalse(freteResponseList.isEmpty());
    }


    @Test
    @DisplayName("deve retornar o Frete criado e o status 201 ")
    public void cadastrarFrete() throws Exception {
        when(freteConvertDISAssembler.convert_paraFreteDomain(getFreteInputBuilder()))
                .thenReturn(freteBuilder());

        when(freteConvertAssembler.convert_para_DTO( any(Frete.class) ))
                .thenReturn( getFreteResponse("any") );

        FreteResponse freteResponse = controller.cadastrarFrete(getFreteInputBuilder());


        mockMvc.perform(post(BASE_URL+"/novo-frete"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.descricao", is("Segundo frete")));

        verify(service, atLeastOnce()).novoFrete(any(Frete.class));
        assertNotNull(freteResponse);
    }

    @Test
    @DisplayName("deve calcular e retornar o valor do frete desejado")
    public void calcularFrete() throws Exception {
        when(freteConvertDISAssembler.convert_paraFreteDomain(getFreteInputBuilder()))
                .thenReturn(freteBuilder());

        when(service.valorDoFrete(any(Frete.class)))
                .thenReturn(50.8);

        Double valorDoFrete  = controller.calcularFrete(getFreteInputBuilder());


        mockMvc.perform(get(BASE_URL+"/valor-frete"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.valorDoFrete", is(50.8)));

        verify(service, atLeastOnce()).valorDoFrete(any(Frete.class));
        assertNotNull(valorDoFrete);
    }



}
