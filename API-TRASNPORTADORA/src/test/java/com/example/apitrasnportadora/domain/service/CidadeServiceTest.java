package com.example.apitrasnportadora.domain.service;

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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Optional;

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

    @BeforeEach
    public void init(){
         cidade = new Cidade();
    }

    @Test
    @DisplayName("deve salvar e devolver um registro de Cidade")
    public void save(){
        ReflectionTestUtils.setField(cidade, "id", 1L);
        cidade.setNome("Sao Luis");
        cidade.setTaxa(30.50);
        cidade.setUF("MA");

        when(repository.save(any(Cidade.class)))
                .thenReturn(cidade);

        Cidade cidadeResponse = service.save( cidade );

        assertNotNull(cidadeResponse);
        assertEquals(cidade.getNome(), cidadeResponse.getNome());

    }

    @Test
    @DisplayName("deve lancar uma exception")
    public void ThrowExceptionInSave(){
        Cidade cidadeNull = null;

        assertThatExceptionOfType(NegocioException.class).isThrownBy(() ->
                        service.save(cidadeNull))
                .withMessage("DADOS DA CIDADE NAO PODE SER NULO");

    }


}
