package com.example.apitrasnportadora.core.excetion;

public class CidadeNaoEncontradoException extends EntidadeNaoEncontradaException {
    private static final long serialVersionUID = 1L;

    public CidadeNaoEncontradoException(String mensagem){
        super(mensagem);
    }

    public CidadeNaoEncontradoException(Long cidadeId){
        this(String.format("NÃO EXISTE UM CADASTRO DE CIDADE COM CÓDIGO %d", cidadeId));
    }
}
