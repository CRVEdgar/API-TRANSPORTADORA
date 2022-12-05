package com.example.apitrasnportadora.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Frete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigo_frete", nullable = false)
    private Long id;

    private String descricao;
    private Double peso;
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    public Frete() {
    }

    public Frete(String descricao, Double peso, Double valor, Cliente cliente, Cidade cidade) {
        this.descricao = descricao;
        this.peso = peso;
        this.valor = valor;
        this.cliente = cliente;
        this.cidade = cidade;
    }

    public Long getId() {
        return id;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Frete)) return false;
        Frete frete = (Frete) o;
        return getId().equals(frete.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
