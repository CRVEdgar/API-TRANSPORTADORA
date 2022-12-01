package com.example.apitrasnportadora.core.excetion;

public class FreteNaoEncontradaException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public FreteNaoEncontradaException(String mensagem){
        super(mensagem);
    }

    public FreteNaoEncontradaException(Long freteId){
        this(String.format("NÃO EXISTE UM CADASTRO DE FRETE COM CÓDIGO %d", freteId));
    }
}
