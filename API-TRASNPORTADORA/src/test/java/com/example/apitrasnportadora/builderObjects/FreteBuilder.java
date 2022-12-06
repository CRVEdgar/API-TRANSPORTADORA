package com.example.apitrasnportadora.builderObjects;

import com.example.apitrasnportadora.api.model.dto.input.FreteInput;
import com.example.apitrasnportadora.api.model.dto.input.referenceId.CidadeIdInput;
import com.example.apitrasnportadora.api.model.dto.input.referenceId.ClientIdInput;
import com.example.apitrasnportadora.api.model.dto.response.FreteResponse;
import com.example.apitrasnportadora.domain.model.Cliente;
import com.example.apitrasnportadora.domain.model.Frete;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.*;

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

    public static FreteInput getFreteInputBuilder(){
        FreteInput input = new FreteInput();
        input.setCidade(new CidadeIdInput(1L));
        input.setDescricao("Segundo frete");
        input.setPeso(80.5);
        input.setValor(600.0);
        input.setCliente(new ClientIdInput(1L));

        return input;
    }

    public static FreteResponse getFreteResponse(String descricao){
        FreteResponse freteResponse = new FreteResponse();
        freteResponse.setDescricao(descricao);
        freteResponse.setPeso(80.1);
        freteResponse.setValor(100.5);
        return freteResponse;
    }

    public static List<FreteResponse> getListFreteResponse(){
        List<FreteResponse> responseList = new ArrayList<>();
        FreteResponse frete1 = getFreteResponse("Segundo frete");

        FreteResponse frete2 = getFreteResponse("Terceiro frete");

        responseList.add(frete1);
        responseList.add(frete2);

        return responseList;
    }



}
