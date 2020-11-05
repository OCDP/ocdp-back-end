package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.BairroDTO;
import br.ufg.api.ocd.dto.LogradouroDTO;
import br.ufg.api.ocd.model.Bairro;
import br.ufg.api.ocd.model.Logradouro;
import br.ufg.api.ocd.service.BairroService;
import br.ufg.api.ocd.service.LogradouroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.ufg.api.ocd.util.ValidacoesDtoUtil.createErrorString;

@RestController
@RequestMapping(value = "/api/logradouros")
public class LogradouroController {

    @Autowired
    private LogradouroService service;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping()
    public LogradouroDTO getById(@RequestParam("cep") String cep) throws Exception {
        return modelMapper.map(service.findByCep(cep), LogradouroDTO.class);
    }

    @PostMapping()
    public void salvar(LogradouroDTO dto) throws Exception {
        Optional.of(dto)
                .map(o -> modelMapper.map(dto, Logradouro.class))
                .map(o -> service.salvar(o))
                .orElseThrow(() -> new Exception("Erro ao salvar Logradouro"));
    }

}
