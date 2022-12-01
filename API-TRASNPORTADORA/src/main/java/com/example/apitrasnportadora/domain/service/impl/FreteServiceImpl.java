package com.example.apitrasnportadora.domain.service.impl;

import com.example.apitrasnportadora.core.excetion.CidadeNaoEncontradoException;
import com.example.apitrasnportadora.core.excetion.ClienteNaoEncontradoException;
import com.example.apitrasnportadora.domain.model.Cidade;
import com.example.apitrasnportadora.domain.model.Cliente;
import com.example.apitrasnportadora.domain.model.Frete;
import com.example.apitrasnportadora.domain.repository.CidadeRepository;
import com.example.apitrasnportadora.domain.repository.ClienteRepository;
import com.example.apitrasnportadora.domain.repository.FreteRepository;
import com.example.apitrasnportadora.domain.service.FreteService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FreteServiceImpl implements FreteService {

    private final FreteRepository repository;

    private final ClienteRepository clienteRepository;
    private final CidadeRepository cidadeRepository;

    private static final Double TAXA_FIXA = 25.0;

    public FreteServiceImpl(FreteRepository repository, ClienteRepository clienteRepository, CidadeRepository cidadeRepository) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.cidadeRepository = cidadeRepository;
    }

    @Override
    public List<Frete> fretesDoCliente(Cliente cliente) {
        List<Frete> frestes = repository.findByCliente_Id(cliente.getId())
                .stream()
                .sorted(Comparator.comparing(Frete :: getValor) )
                .collect(Collectors.toList());

        return frestes;
    }

    @Override
    public Frete novoFrete(Frete freteInput) {

        Cidade cidadeLocalizada = cidadeRepository.findById(freteInput.getCidade().getId())
                .orElseThrow(() -> new CidadeNaoEncontradoException(freteInput.getCidade().getId()));

        Cliente clienteLocalizado = clienteRepository.findById( freteInput.getCliente().getId() )
                .orElseThrow(() -> new ClienteNaoEncontradoException( freteInput.getCliente().getId() ));

        Frete freteNew = new Frete();

        freteNew.setCidade(cidadeLocalizada);
        freteNew.setCliente(clienteLocalizado);
        freteNew.setDescricao(freteInput.getDescricao());
        freteNew.setPeso(freteInput.getPeso());
        freteNew.setValor(freteInput.getValor());

        return repository.save(freteNew);
    }

    @Override
    public Double valorDoFrete(Frete fretInput) {

         return  (fretInput.getPeso() * TAXA_FIXA) + fretInput.getCidade().getTaxa();
    }

    @Override
    public Frete freteMaiorValor() {
        return repository.findAll().stream()
                .max(Comparator.comparingDouble(Frete :: getValor))
                .get();
    }

    @Override
    public Cidade moda() {


        return null;
    }


}
