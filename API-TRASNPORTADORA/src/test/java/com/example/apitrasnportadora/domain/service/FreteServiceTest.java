package com.example.apitrasnportadora.domain.service;


import com.example.apitrasnportadora.core.excetion.CidadeNaoEncontradoException;
import com.example.apitrasnportadora.core.excetion.ClienteNaoEncontradoException;
import com.example.apitrasnportadora.core.excetion.NegocioException;
import com.example.apitrasnportadora.domain.model.Cliente;
import com.example.apitrasnportadora.domain.model.Frete;
import com.example.apitrasnportadora.domain.repository.CidadeRepository;
import com.example.apitrasnportadora.domain.repository.ClienteRepository;
import com.example.apitrasnportadora.domain.repository.FreteRepository;
import com.example.apitrasnportadora.domain.service.impl.FreteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.example.apitrasnportadora.builderObjects.CidadeBuilder.cidadeMock;
import static com.example.apitrasnportadora.builderObjects.ClienteBuilder.*;
import static com.example.apitrasnportadora.builderObjects.FreteBuilder.*;
import static com.example.apitrasnportadora.builderObjects.FreteBuilder.freteBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FreteServiceTest {

    @InjectMocks
    private FreteServiceImpl freteService;

    @Mock
    private FreteRepository repository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private CidadeRepository cidadeRepository;

    Frete frete;
    List<Frete> freteList;

    @BeforeEach
    public void init(){
        frete = freteBuilder();
        freteList = fretesSet().stream().toList();
    }

    @Test
    @DisplayName("Deve retornar a lista de todos os fretes de um cliente ordenado por valor")
    public void FretesDoCliente(){
        Cliente cliente = clientMock();

        when(repository.findByCliente_Id(cliente.getId()))
                .thenReturn(fretesSet());

        List<Frete> fretesResponse = freteService.fretesDoCliente(cliente);

        assertFalse(fretesResponse.isEmpty());
        assertThat( fretesResponse.stream().allMatch( f -> f.getCliente().equals(cliente) ) );
        /** Valida se a ordenação por valor está ocorrendo*/
        assertTrue( fretesResponse.get(0).getValor() >= fretesResponse.get( fretesResponse.size()-1 ).getValor() );
    }

    @Test
    @DisplayName("deve cadastrar um novo frete e retornar o mesmo registro")
    public void novoFrete(){
        Frete freteInput = freteBuilder();

        when(cidadeRepository.findById(anyLong()))
                .thenReturn(Optional.of(cidadeMock()));
        when(clienteRepository.findById(anyLong()))
                .thenReturn(Optional.of(clientMock()));
        when(repository.save(any(Frete.class)))
                .thenReturn(freteBuilder());

        Frete freteResponse = freteService.novoFrete(freteInput);

        assertNotNull(freteResponse);
        assertEquals(freteInput, freteResponse);
    }

    @Test
    @DisplayName("deve lançar uma exception quando nao localizar a cidade informada")
    public void trhowExceptionNovoFrete_CidadeInvalid(){
        Frete freteInput = freteBuilder();

        assertThatExceptionOfType(CidadeNaoEncontradoException.class).isThrownBy(() ->
                        freteService.novoFrete(freteInput))
                .withMessageContaining("NÃO EXISTE UM CADASTRO DE CIDADE COM CÓDIGO");
    }

    @Test
    @DisplayName("deve lançar uma exception quando nao localizar o cliente informado")
    public void trhowExceptionNovoFrete_ClientInvalid(){
        Frete freteInput = freteBuilder();

        when(cidadeRepository.findById(anyLong()))
                .thenReturn(Optional.of(cidadeMock()));

        assertThatExceptionOfType(ClienteNaoEncontradoException.class).isThrownBy(() ->
                        freteService.novoFrete(freteInput))
                .withMessageContaining("NÃO EXISTE UM CADASTRO DE CLIENTE COM CÓDIGO");
    }

    @Test
    @DisplayName("deve calcular o valor do frete")
    public void valoDoFrete(){

        Double valorAtualDaTaxa = 25.0;
        Double valorDoFreteCalc = (frete.getPeso() * valorAtualDaTaxa) + frete.getCidade().getTaxa();

        Double valorDoFrete = freteService.valorDoFrete(frete);

        assertNotNull(valorDoFrete);
        assertEquals(valorDoFreteCalc, valorDoFrete);
    }

    @Test
    @DisplayName("deve retornar o frete com maior valor")
    public void freteMaiorValor(){
        Set<Frete> fretes = fretesSet();

        when(repository.findAll())
                .thenReturn(freteList);

        Frete maiorValorDoFrete = freteService.freteMaiorValor();

        assertTrue( fretes.stream().allMatch( f -> maiorValorDoFrete.getValor() >= f.getValor() ) );
    }

}
