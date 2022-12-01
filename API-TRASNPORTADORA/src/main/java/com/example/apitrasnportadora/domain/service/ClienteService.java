package com.example.apitrasnportadora.domain.service;

import com.example.apitrasnportadora.domain.model.Cliente;

public interface ClienteService {

    Cliente buscarPorTelefone(String telefone);
}
