package com.example.apitrasnportadora.api.model.convert;

/*
 * CLASSE UTILIZADA PARA CONVERTER ImovelInput em Imoveis para depois enviar ao Service
 * */

import com.example.apitrasnportadora.api.model.dto.input.CidadeInput;
import com.example.apitrasnportadora.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeConvertDISAssembler {

    @Autowired
    private ModelMapper modelMapper;

    //recebe um objeto input e convert para um objeto do modelo de domínio
    public Cidade convert_paraClienteDomain(CidadeInput imovelInput){
        return modelMapper.map(imovelInput, Cidade.class);
    }

    public void copy_paraClienteDomain(CidadeInput imovelInput, Cidade imovel){
        modelMapper.map(imovelInput, imovel);
    }
}
