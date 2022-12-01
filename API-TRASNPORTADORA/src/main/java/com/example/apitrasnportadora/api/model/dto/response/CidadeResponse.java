package com.example.apitrasnportadora.api.model.dto.response;

import com.example.apitrasnportadora.api.model.dto.input.FreteInput;

import java.util.Set;

public class CidadeResponse {
    private String nome;
    private String UF;

    private Double taxa;

    private Set<FreteInput> frete;

    public CidadeResponse() {
    }

    public CidadeResponse(String nome, String UF, Double taxa, Set<FreteInput> frete) {
        this.nome = nome;
        this.UF = UF;
        this.taxa = taxa;
        this.frete = frete;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    public Set<FreteInput> getFrete() {
        return frete;
    }

    public void addFrete(FreteInput frete) {
        this.frete.add(frete);
    }
}
