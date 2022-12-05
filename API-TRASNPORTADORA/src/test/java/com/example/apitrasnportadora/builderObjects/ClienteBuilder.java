package com.example.apitrasnportadora.builderObjects;

import com.example.apitrasnportadora.domain.model.Cliente;
import com.example.apitrasnportadora.domain.model.Frete;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.example.apitrasnportadora.builderObjects.CidadeBuilder.cidadeMock;
import static com.example.apitrasnportadora.builderObjects.FreteBuilder.*;

public class ClienteBuilder {

    public static Cliente clientMock() {
        Cliente cliente = new Cliente();
        ReflectionTestUtils.setField(cliente,"id", 1L);
        cliente.setNome("Romulo");
        cliente.setEndereco("Rua das Margaridas");
        cliente.setTelefone("98 99999-8888");

        cliente.setFretes( fretesSetToClient() );

        return cliente;
    }


}
