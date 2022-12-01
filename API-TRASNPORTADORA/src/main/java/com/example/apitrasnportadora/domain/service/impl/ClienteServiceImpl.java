package com.example.apitrasnportadora.domain.service.impl;

import com.example.apitrasnportadora.domain.model.Cliente;
import com.example.apitrasnportadora.domain.repository.ClienteRepository;
import com.example.apitrasnportadora.domain.service.ClienteService;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repository;

    public ClienteServiceImpl(ClienteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cliente buscarPorTelefone(String telefone) {
        return repository.findByTelefone(telefone).orElseThrow(() -> new RuntimeException("telefone informado nao encontrado"));
    }
}
