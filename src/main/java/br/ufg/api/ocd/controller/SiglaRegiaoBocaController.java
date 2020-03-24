package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.SiglaRegiaoBocaDTO;
import br.ufg.api.ocd.model.SiglaRegiaoBoca;
import br.ufg.api.ocd.service.SiglaRegiaoBocaService;
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
@RequestMapping(value = "/api/siglaRegiaoBoca")
public class SiglaRegiaoBocaController {
    @Autowired
    private SiglaRegiaoBocaService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody SiglaRegiaoBocaDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        service.salvar(modelMapper.map(dto, SiglaRegiaoBoca.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@Valid @RequestBody SiglaRegiaoBocaDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        try {
            service.atualizar(modelMapper.map(dto, SiglaRegiaoBoca.class));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/byId/{id}")
    public SiglaRegiaoBocaDTO getById(@PathVariable String id) {
        return modelMapper.map(service.findById(id), SiglaRegiaoBocaDTO.class);
    }

    @GetMapping
    public List<SiglaRegiaoBocaDTO> getSiglaRegiaoBocas() {
        return service.getAll().stream()
                .map(post -> modelMapper.map(post, SiglaRegiaoBocaDTO.class))
                .collect(Collectors.toList());
    }
}
