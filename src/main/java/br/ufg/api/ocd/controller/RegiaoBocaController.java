package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.RegiaoBocaDTO;
import br.ufg.api.ocd.model.RegiaoBoca;
import br.ufg.api.ocd.service.RegiaoBocaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static br.ufg.api.ocd.util.ValidacoesDtoUtil.createErrorString;

@RestController
@RequestMapping(value = "/api/regiaoBoca")
public class RegiaoBocaController {
    @Autowired
    private RegiaoBocaService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody RegiaoBocaDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        service.salvar(modelMapper.map(dto, RegiaoBoca.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@Valid @RequestBody RegiaoBocaDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        try {
            service.atualizar(modelMapper.map(dto, RegiaoBoca.class));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/byId/{id}")
    public RegiaoBocaDTO getById(@PathVariable String id) {
        return modelMapper.map(service.findById(id), RegiaoBocaDTO.class);
    }

    @GetMapping(value = "/bySiglaRegiao/{sigla}")
    public List<RegiaoBocaDTO> getBySiglaRegiao(String sigla) {
        return service.getBySigla(sigla).stream()
                .map(post -> modelMapper.map(post, RegiaoBocaDTO.class))
                .collect(Collectors.toList());
    }
}
