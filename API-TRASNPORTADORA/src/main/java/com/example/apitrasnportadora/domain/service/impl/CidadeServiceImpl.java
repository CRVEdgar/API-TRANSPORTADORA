package com.example.apitrasnportadora.domain.service.impl;

import com.example.apitrasnportadora.domain.model.Cidade;
import com.example.apitrasnportadora.domain.repository.CidadeRepository;
import com.example.apitrasnportadora.domain.service.CidadeService;
import org.springframework.stereotype.Service;

@Service
public class CidadeServiceImpl implements CidadeService {

    private final CidadeRepository repository;

    public CidadeServiceImpl(CidadeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Cidade save(Cidade cidade) {
        if(cidade!=null){
            return repository.save(cidade);
        }
        //TODO: EXCEPTION
        return null;
    }

    @Override
    public Cidade findById(Long id) {
        return null;
    }

    @Override
    public Cidade findByName(String name) {
        return null;
    }
}
