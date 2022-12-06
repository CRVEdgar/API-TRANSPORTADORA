package com.example.apitrasnportadora.api.controller;

import com.example.apitrasnportadora.api.model.convert.ClienteConvertDISAssembler;
import com.example.apitrasnportadora.api.model.convert.FreteConvertAssembler;
import com.example.apitrasnportadora.api.model.convert.FreteConvertDISAssembler;
import com.example.apitrasnportadora.api.model.dto.input.FreteInput;
import com.example.apitrasnportadora.api.model.dto.response.FreteResponse;
import com.example.apitrasnportadora.domain.model.Cliente;
import com.example.apitrasnportadora.domain.repository.ClienteRepository;
import com.example.apitrasnportadora.domain.service.FreteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@ControllerAdvice
@RequestMapping("/fretes")
public class FreteController {

    private final FreteService service;

    private final ClienteRepository clienteRepository;
    private final ClienteConvertDISAssembler clienteConvertDISAssembler;
    private final FreteConvertDISAssembler freteConvertDISAssembler;
    private final FreteConvertAssembler freteConvertAssembler;


    public FreteController(FreteService service, ClienteRepository clienteRepository, ClienteConvertDISAssembler clienteConvertDISAssembler, FreteConvertDISAssembler freteConvertDISAssembler, FreteConvertAssembler freteConvertAssembler) {
        this.service = service;
        this.clienteRepository = clienteRepository;
        this.clienteConvertDISAssembler = clienteConvertDISAssembler;
        this.freteConvertDISAssembler = freteConvertDISAssembler;
        this.freteConvertAssembler = freteConvertAssembler;
    }

    @GetMapping("/fretes-Cliente")
    @ResponseStatus(HttpStatus.OK)
    public List<FreteResponse> fretesDoCliente(@Valid @RequestBody FreteInput freteInput){

        Cliente cliente = clienteRepository.findById(freteInput.getCliente().getId()).get();
        return freteConvertAssembler.convert_Lista_para_DTO( service.fretesDoCliente( cliente ) );
    }

    @PostMapping("/novo-frete")
    @ResponseStatus(HttpStatus.CREATED)
    public FreteResponse cadastrarFrete(@Valid @RequestBody FreteInput freteInput){
        return freteConvertAssembler.convert_para_DTO(
                service.novoFrete( freteConvertDISAssembler.convert_paraFreteDomain(freteInput) )
        );
    }

    @GetMapping("/valor-frete")
    @ResponseStatus(HttpStatus.OK)
    public Double calcularFrete(@Valid @RequestBody FreteInput freteInput){
        return service.valorDoFrete(
                freteConvertDISAssembler.convert_paraFreteDomain(freteInput)
        );
    }

    @GetMapping("/frete-maior-valor")
    @ResponseStatus(HttpStatus.OK)
    public FreteResponse freteMaiorValor(){
        return freteConvertAssembler.convert_para_DTO(
                service.freteMaiorValor()
        );
    }

    @DeleteMapping("/removerFrete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerFrete(Long id){

    }
}
