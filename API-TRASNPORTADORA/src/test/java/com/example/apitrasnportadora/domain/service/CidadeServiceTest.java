package com.example.apitrasnportadora.domain.service;

import com.example.apitrasnportadora.core.excetion.CidadeNaoEncontradoException;
import com.example.apitrasnportadora.core.excetion.NegocioException;
import com.example.apitrasnportadora.domain.model.Cidade;
import com.example.apitrasnportadora.domain.repository.CidadeRepository;
import com.example.apitrasnportadora.domain.service.impl.CidadeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.apitrasnportadora.builderObjects.CidadeBuilder.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CidadeServiceTest {


    @InjectMocks
    private CidadeServiceImpl service;

    @Mock
    private CidadeRepository repository;

    Cidade cidade;

    private static final String SAO_LUIS = "São Luis";

    @BeforeEach
    public void init(){
         cidade = cidadeMock();
    }

    @Test
    @DisplayName("deve salvar e devolver um registro de Cidade")
    public void save(){

        when(repository.save(any(Cidade.class)))
                .thenReturn(cidade);

        Cidade cidadeResponse = service.save( cidade );

        assertNotNull(cidadeResponse);
        assertEquals(cidade.getNome(), cidadeResponse.getNome());

    }

    @Test
    @DisplayName("deve lancar uma exception ao salvar uma cidade")
    public void ThrowExceptionInSave(){
        Cidade cidadeNull = null;

        assertThatExceptionOfType(NegocioException.class).isThrownBy(() ->
                        service.save(cidadeNull))
                .withMessage("DADOS DA CIDADE NAO PODE SER NULO");

    }

    @Test
    @DisplayName("deve encontrar uma cidade ao buscar por nome")
    public void buscarCidadePorNome(){
        Cidade saoLuis = cidadeMock();

        when(repository.findByNome(anyString()))
                .thenReturn(Optional.of(saoLuis));

        Cidade cidaderesponse = service.findByName(anyString());

        assertEquals(SAO_LUIS, cidaderesponse.getNome());
    }

    @Test
    @DisplayName("deve lancar uma exception ao buscar por name")
    public void ThrowExceptionInFindName(){

        assertThatExceptionOfType(CidadeNaoEncontradoException.class).isThrownBy(() ->
                        service.findByName("Sao Paulo"))
                .withMessageContaining("NÃO EXISTE UM CADASTRO DE CIDADE COM NOME");

    }

    @Test
    @DisplayName("deve encontrar uma cidade ao buscar por id")
    public void buscarCidadePorId(){


        when(repository.findById(anyLong()))
                .thenReturn(Optional.of(cidade));

        Cidade cidaderesponse = service.findById( anyLong() );

        assertEquals(SAO_LUIS, cidaderesponse.getNome());
        assertEquals(1L, cidaderesponse.getId());

    }

    @Test
    @DisplayName("deve lancar uma exception ao buscar por id")
    public void ThrowExceptionInFindId(){
        Long idINvalid = 300L;
        assertThatExceptionOfType(CidadeNaoEncontradoException.class).isThrownBy(() ->
                        service.findById(idINvalid))
                .withMessage("NÃO EXISTE UM CADASTRO DE CIDADE COM CÓDIGO " +idINvalid);
    }

//    private Cidade generateCidade(){
//
//        Cidade saoLuis = new Cidade();
//        ReflectionTestUtils.setField(saoLuis, "id", 1L);
//        saoLuis.setNome(SAO_LUIS);
//        saoLuis.setTaxa(30.50);
//        saoLuis.setUF("MA");
//
//        return saoLuis;
//    }

}
