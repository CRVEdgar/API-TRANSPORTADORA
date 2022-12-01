package com.example.apitrasnportadora.api.model.dto.input;

import com.example.apitrasnportadora.api.model.dto.input.referenceId.CidadeIdInput;
import com.example.apitrasnportadora.api.model.dto.input.referenceId.ClientIdInput;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public class FreteInput {

    private String descricao;
    @NotNull
    @PositiveOrZero
    private Double peso;
    @NotNull
    @PositiveOrZero
    private Double valor;

    @Valid
    @NotNull
    private ClientIdInput cliente;

    @Valid
    @NotNull
    private CidadeIdInput cidade;

    public FreteInput() {
    }

    public FreteInput(String descricao, Double peso, Double valor, ClientIdInput cliente, CidadeIdInput cidade) {
        this.descricao = descricao;
        this.peso = peso;
        this.valor = valor;
        this.cliente = cliente;
        this.cidade = cidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public ClientIdInput getCliente() {
        return cliente;
    }

    public void setCliente(ClientIdInput cliente) {
        this.cliente = cliente;
    }

    public CidadeIdInput getCidade() {
        return cidade;
    }

    public void setCidade(CidadeIdInput cidade) {
        this.cidade = cidade;
    }
}
