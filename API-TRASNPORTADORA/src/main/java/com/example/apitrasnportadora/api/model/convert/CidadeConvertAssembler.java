package com.example.apitrasnportadora.api.model.convert;

import com.example.apitrasnportadora.api.model.dto.response.CidadeResponse;
import com.example.apitrasnportadora.domain.model.Cidade;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
* CLASSE UTILIZADA PARA CONVERTER Cidade em CidadeResponse para depois devolver ao controller
* */

@Component
public class CidadeConvertAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeResponse convert_para_DTO(Cidade imovel){
        return modelMapper.map(imovel, CidadeResponse.class);
    }

    public List<CidadeResponse> convert_Lista_para_DTO( List<Cidade> imoveis) {
        return imoveis.stream()
                .map(imovel -> convert_para_DTO(imovel))
                .collect(Collectors.toList());
    }

}
