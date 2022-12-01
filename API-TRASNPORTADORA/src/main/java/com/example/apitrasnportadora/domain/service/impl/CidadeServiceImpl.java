package com.example.apitrasnportadora.domain.service.impl;

import com.example.apitrasnportadora.core.excetion.CidadeNaoEncontradoException;
import com.example.apitrasnportadora.core.excetion.NegocioException;
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
        }else{
            throw new NegocioException("DADOS DA CIDADE NAO PODE SER NULO");
        }

    }

    @Override
    public Cidade findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CidadeNaoEncontradoException( id ));
    }

    @Override
    public Cidade findByName(String name) {
        return repository.findByNome(name).orElseThrow(() -> new CidadeNaoEncontradoException("NÃO EXISTE UM CADASTRO DE CIDADE COM NOME: " + name ));
    }
}
