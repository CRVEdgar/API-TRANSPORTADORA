package com.example.apitrasnportadora.domain.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String nome;
    private String UF;
    private Double taxa;

    @OneToMany(mappedBy = "cidade", cascade = CascadeType.ALL)
    private Set<Frete> fretes;

    public Cidade() {
    }
    public Cidade(String nome, String UF, Double taxa, Set<Frete> fretes) {
        this.nome = nome;
        this.UF = UF;
        this.taxa = taxa;
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

    public Set<Frete> getFretes() {
        return fretes;
    }

    public void setFretes(Set<Frete> fretes) {
        this.fretes = fretes;
    }
}
