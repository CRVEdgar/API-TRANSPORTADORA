package com.example.apitrasnportadora.api.model.dto.response;

import com.example.apitrasnportadora.api.model.dto.input.CidadeInput;
import com.example.apitrasnportadora.api.model.dto.input.ClienteInput;

public class FreteResponse {

    private String descricao;

    private Double peso;

    private Double valor;


    private ClienteInput cliente;


    private CidadeInput cidade;



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


}
