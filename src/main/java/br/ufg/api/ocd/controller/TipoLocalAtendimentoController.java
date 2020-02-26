package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.CidadeDTO;
import br.ufg.api.ocd.dto.TipoLocalAtendimentoDTO;
import br.ufg.api.ocd.model.TipoLocalAtendimento;
import br.ufg.api.ocd.service.TipoLocalAtendimentoService;
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
@RequestMapping(value = "/api/tipoLocalAtendimento")
public class TipoLocalAtendimentoController {

    @Autowired
    private TipoLocalAtendimentoService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody TipoLocalAtendimentoDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        service.salvar(modelMapper.map(dto, TipoLocalAtendimento.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@Valid @RequestBody TipoLocalAtendimentoDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        try {
            service.atualizar(modelMapper.map(dto, TipoLocalAtendimento.class));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/byId/{id}")
    public TipoLocalAtendimentoDTO getById(@RequestParam String id) {
        return modelMapper.map(service.findById(id), TipoLocalAtendimentoDTO.class);
    }

    @GetMapping
    public List<CidadeDTO> getLocais() {
        return service.getAll().stream()
                .map(post -> modelMapper.map(post, CidadeDTO.class))
                .collect(Collectors.toList());
    }
    
}
