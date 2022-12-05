package com.example.apitrasnportadora.domain.service;

import com.example.apitrasnportadora.core.excetion.ClienteNaoEncontradoException;
import com.example.apitrasnportadora.domain.model.Cliente;
import com.example.apitrasnportadora.domain.repository.ClienteRepository;
import com.example.apitrasnportadora.domain.service.impl.ClienteServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.example.apitrasnportadora.builderObjects.ClienteBuilder.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @InjectMocks
    private ClienteServiceImpl service;

    @Mock
    private ClienteRepository repository;

    Cliente cliente;

    @BeforeEach
    public void init(){
        cliente = clientMock();
    }



    @Test
    @DisplayName("deve retornar o cliente informado ao buscar por telefone")
    public void buscarPortelefone(){
        String telefoneDesejado = "98 99999-8888";

        when(repository.findByTelefone(anyString()))
                .thenReturn(Optional.ofNullable(cliente));

        Cliente clienteResponse = service.buscarPorTelefone(telefoneDesejado);

        assertNotNull(clienteResponse, "Nao foi possível localizar o cliente com o telefone informado");
        assertEquals(telefoneDesejado, clienteResponse.getTelefone(), "O nº do telefone informado é diferente " +
                "do telefone do cliente localizado");

    }

    @Test
    @DisplayName("deve retornar lançar uma exception quando nao encontrar o cliente informado ao buscar por telefone")
    public void ThrowExceptionInBuscarPortelefone(){
        assertThatExceptionOfType(ClienteNaoEncontradoException.class).isThrownBy(() ->
                service.buscarPorTelefone("98 0000-0000"))
                .withMessage("telefone informado nao encontrado");
    }

    @Test
    @DisplayName("deve savar e retornar o registro do Cliente Salvo")
    public void salvarCliente(){

        when(service.salvar(any(Cliente.class)))
                .thenReturn(cliente);

        Cliente clientResponse = service.salvar(cliente);

        assertNotNull(clientResponse);
    }

}
