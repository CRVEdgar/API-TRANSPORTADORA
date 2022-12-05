package com.example.apitrasnportadora.builderObjects;

import com.example.apitrasnportadora.domain.model.Cliente;
import com.example.apitrasnportadora.domain.model.Frete;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.apitrasnportadora.builderObjects.CidadeBuilder.cidadeMock;
import static com.example.apitrasnportadora.builderObjects.ClienteBuilder.*;

public class FreteBuilder {

    public static Frete freteBuilder(){
        Frete frete = new Frete();
        ReflectionTestUtils.setField(frete, "id", 1L);
        frete.setCliente(clientMock());
        frete.setCidade(cidadeMock());
        frete.setValor(100.0);
        frete.setPeso(60.8);

        return frete;
    }

    public static Set<Frete> fretesSet(){
        Set<Frete> fretes = new HashSet<>();
        fretes.add(freteBuilder());

        Frete frete2 = new Frete("Segundo frete", 50.5, 80.6, clientMock(), cidadeMock());
        ReflectionTestUtils.setField(frete2, "id", 2L);

        Frete frete3 = new Frete("Terceiro frete", 90.5, 120.9, clientMock(), cidadeMock());
        ReflectionTestUtils.setField(frete3, "id", 3L);

        fretes.add(frete2);
        fretes.add(frete3);

        return fretes;
    }

    public static Set<Frete> fretesSetToClient(){
        Frete frete1 = new Frete("Primeiro frete", 90.5, 120.9, new Cliente(), cidadeMock());
        ReflectionTestUtils.setField(frete1, "id", 1L);

        Frete frete2 = new Frete("Segundo frete", 100.5, 130.9, new Cliente(), cidadeMock());
        ReflectionTestUtils.setField(frete2, "id", 2L);

        Set<Frete> fretes = new LinkedHashSet<>();
        fretes.add(frete1);
        fretes.add(frete2);

        return fretes;
    }

}
