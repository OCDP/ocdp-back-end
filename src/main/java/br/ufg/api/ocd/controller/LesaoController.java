package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.LesaoDTO;
import br.ufg.api.ocd.model.Lesao;
import br.ufg.api.ocd.service.LesaoService;
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
@RequestMapping(value = "/api/lesao")
public class LesaoController {
    @Autowired
    private LesaoService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody LesaoDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        service.salvar(modelMapper.map(dto, Lesao.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@Valid @RequestBody LesaoDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        try {
            service.atualizar(modelMapper.map(dto, Lesao.class));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/byId/{id}")
    public LesaoDTO getById(@PathVariable String id) {
        return modelMapper.map(service.findById(id), LesaoDTO.class);
    }

    @GetMapping(value = "/byTipo/{nomeTipo}")
    public List<LesaoDTO> getByTipoLesao(@PathVariable String nomeTipo) {
        return service.findByTipo(nomeTipo).stream()
                .map(post -> modelMapper.map(post, LesaoDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<LesaoDTO> getLesaos() {
        return service.getAll().stream()
                .map(post -> modelMapper.map(post, LesaoDTO.class))
                .collect(Collectors.toList());
    }
}
