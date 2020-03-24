package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.TipoLesaoDTO;
import br.ufg.api.ocd.model.TipoLesao;
import br.ufg.api.ocd.service.TipoLesaoService;
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
@RequestMapping(value = "/api/tipoLesao")
public class TipoLesaoController {
    @Autowired
    private TipoLesaoService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody TipoLesaoDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        service.salvar(modelMapper.map(dto, TipoLesao.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@Valid @RequestBody TipoLesaoDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        try {
            service.atualizar(modelMapper.map(dto, TipoLesao.class));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/byId/{id}")
    public TipoLesaoDTO getById(@PathVariable String id) {
        return modelMapper.map(service.findById(id), TipoLesaoDTO.class);
    }

    @GetMapping
    public List<TipoLesaoDTO> getTipoLesaos() {
        return service.getAll().stream()
                .map(post -> modelMapper.map(post, TipoLesaoDTO.class))
                .collect(Collectors.toList());
    }
}
