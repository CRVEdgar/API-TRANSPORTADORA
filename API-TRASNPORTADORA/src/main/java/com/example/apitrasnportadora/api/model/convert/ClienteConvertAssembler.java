package com.example.apitrasnportadora.api.model.convert;


import com.example.apitrasnportadora.api.model.dto.response.ClienteResponse;
import com.example.apitrasnportadora.domain.model.Cliente;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/*
* CLASSE UTILIZADA PARA CONVERTER Cliente em ClienteResponse para depois devolver ao controller
* */

@Component
public class ClienteConvertAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public ClienteResponse convert_para_DTO(Cliente cliente){
        return modelMapper.map(cliente, ClienteResponse.class);
    }

    public List<ClienteResponse> convert_Lista_para_DTO( List<Cliente> clientes) {
        return clientes.stream()
                .map(cliente -> convert_para_DTO(cliente))
                .collect(Collectors.toList());
    }

}
