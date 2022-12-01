package com.example.apitrasnportadora.api.model.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.Set;

public class CidadeInput {

    @NotBlank
    private String nome;
    @NotBlank
    private String UF;
    @NotNull
    @PositiveOrZero
    private Double taxa;

    private Set<FreteInput> frete;

    public CidadeInput() {
    }

    public CidadeInput(String nome, String UF, Double taxa, Set<FreteInput> frete) {
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
