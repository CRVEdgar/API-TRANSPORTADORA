package com.example.apitrasnportadora.api.model.convert;

/*
 * CLASSE UTILIZADA PARA CONVERTER FreteInput em Frete para depois enviar ao Service
 * */

import com.example.apitrasnportadora.api.model.dto.input.FreteInput;
import com.example.apitrasnportadora.domain.model.Cidade;
import com.example.apitrasnportadora.domain.model.Cliente;
import com.example.apitrasnportadora.domain.model.Frete;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FreteConvertDISAssembler {

    @Autowired
    private ModelMapper modelMapper;

    //recebe um objeto input e convert para um objeto do modelo de domínio
    public Frete convert_paraClienteDomain(FreteInput locacaoInput){
        return modelMapper.map(locacaoInput, Frete.class);
    }

    public void copy_paraLocacaoDomain(FreteInput locacaoInput, Frete locacao){
        locacao.setCliente(new Cliente());
        locacao.setCidade(new Cidade());

        modelMapper.map(locacaoInput, locacao);
    }
}
