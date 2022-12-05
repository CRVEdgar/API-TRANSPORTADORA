package com.example.apitrasnportadora.builderObjects;

import com.example.apitrasnportadora.domain.model.Cliente;

import static com.example.apitrasnportadora.builderObjects.FreteBuilder.*;

public class ClienteBuilder {

    public static Cliente clientMock() {
        Cliente cliente = new Cliente();
        cliente.setNome("Romulo");
        cliente.setEndereco("Rua das Margaridas");
        cliente.setTelefone("98 99999-8888");
        cliente.setFretes(setFretes());

        return cliente;
    }
}
