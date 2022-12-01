package com.example.apitrasnportadora.domain.service;

import com.example.apitrasnportadora.domain.model.Cidade;

public interface CidadeService {

    Cidade save(Cidade cidade);

    Cidade findById(Long id);

    Cidade findByName(String name);


}
