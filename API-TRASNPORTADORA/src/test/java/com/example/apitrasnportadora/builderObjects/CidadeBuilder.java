package com.example.apitrasnportadora.builderObjects;

import com.example.apitrasnportadora.domain.model.Cidade;
import org.springframework.test.util.ReflectionTestUtils;

public class CidadeBuilder {

    public static Cidade cidadeMock(){

        Cidade saoLuis = new Cidade();
        ReflectionTestUtils.setField(saoLuis, "id", 1L);
        saoLuis.setNome("São Luis");
        saoLuis.setTaxa(30.50);
        saoLuis.setUF("MA");

        return saoLuis;
    }
}
