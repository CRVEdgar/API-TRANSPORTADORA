package com.example.apitrasnportadora.api.model.convert;

import com.example.apitrasnportadora.api.model.dto.response.FreteResponse;
import com.example.apitrasnportadora.domain.model.Frete;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
* CLASSE UTILIZADA PARA CONVERTER Frete em FreteResponse para depois devolver ao controller
* */

@Component
public class FreteConvertAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public FreteResponse convert_para_DTO(Frete locacao){
        return modelMapper.map(locacao, FreteResponse.class);
    }

    public List<FreteResponse> convert_Lista_para_DTO( List<Frete> locacoes) {
        return locacoes.stream()
                .map(locacao -> convert_para_DTO(locacao))
                .collect(Collectors.toList());
    }

}
