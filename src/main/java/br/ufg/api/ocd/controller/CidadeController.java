package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.CidadeDTO;
import br.ufg.api.ocd.model.Cidade;
import br.ufg.api.ocd.service.CidadeService;
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
@RequestMapping(value = "/api/cidade")
public class CidadeController {

    @Autowired
    private CidadeService service;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(consumes = {"application/json"} ,produces = {"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<?> salvar(@Valid @RequestBody CidadeDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        service.salvar(modelMapper.map(dto, Cidade.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(consumes = {"application/json"} ,produces = {"application/json"}, method = RequestMethod.PUT)
    public ResponseEntity<?> atualizar(@Valid @RequestBody CidadeDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        try {
            service.atualizar(modelMapper.map(dto, Cidade.class));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/byId/{id}")
    public CidadeDTO getById(@PathVariable String id) {
        return modelMapper.map(service.findById(id), CidadeDTO.class);
    }

    @GetMapping
    public List<CidadeDTO> getCidades() {
        return service.getAll().stream()
                .map(post -> modelMapper.map(post, CidadeDTO.class))
                .collect(Collectors.toList());
    }

}
