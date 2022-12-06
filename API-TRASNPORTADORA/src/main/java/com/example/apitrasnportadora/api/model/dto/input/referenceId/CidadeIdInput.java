package com.example.apitrasnportadora.api.model.dto.input.referenceId;

import jakarta.validation.constraints.NotNull;

public class CidadeIdInput {

    @NotNull
    private Long id;

    public CidadeIdInput() {
    }

    public CidadeIdInput(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
