package com.example.apitrasnportadora.domain.service;

import com.example.apitrasnportadora.domain.model.Cidade;
import com.example.apitrasnportadora.domain.model.Cliente;
import com.example.apitrasnportadora.domain.model.Frete;

import java.util.List;

public interface FreteService {

    List<Frete> fretesDoCliente(Cliente cliente);

    Frete novoFrete(Frete freteInput);

    Double valorDoFrete(Frete fretInput);

    Frete freteMaiorValor();

    Cidade moda();
}
