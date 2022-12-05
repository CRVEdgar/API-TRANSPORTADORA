package com.example.apitrasnportadora.builderObjects;

import com.example.apitrasnportadora.domain.model.Frete;

import java.util.HashSet;
import java.util.Set;

public class FreteBuilder {

    public static Frete freteBuilder(){
        Frete frete = new Frete();



        return frete;
    }

    public static Set<Frete> setFretes(){
        Set<Frete> fretes = new HashSet<>();
        fretes.add(freteBuilder());

        return fretes;
    }
}
