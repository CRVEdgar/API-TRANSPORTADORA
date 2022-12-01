package com.example.apitrasnportadora.domain.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;
    private String endereco;
    private String telefone;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Set<Frete> fretes;

    public Cliente() {
    }

    public Cliente(String nome, String endereco, String telefone, Set<Frete> fretes) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.fretes = fretes;
    }

    public Long getId() {
        return id;
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

    public Set<Frete> getFretes() {
        return fretes;
    }

    public void setFretes(Set<Frete> fretes) {
        this.fretes = fretes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente)) return false;
        Cliente cliente = (Cliente) o;
        return getId().equals(cliente.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
