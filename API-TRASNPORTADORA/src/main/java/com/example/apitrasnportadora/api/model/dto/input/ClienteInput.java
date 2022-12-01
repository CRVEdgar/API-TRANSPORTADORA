package com.example.apitrasnportadora.api.model.dto.input;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public class ClienteInput {

    @NotBlank
    private String nome;
    @NotBlank
    private String endereco;
    @NotBlank
    private String telefone;

    private Set<FreteInput> frete;

    public ClienteInput() {
    }

    public ClienteInput(String nome, String endereco, String telefone, Set<FreteInput> frete) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.frete = frete;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<FreteInput> getFrete() {
        return frete;
    }

    public void addFrete(FreteInput frete) {
        this.frete.add(frete);
    }
}
