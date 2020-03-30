package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.LocalAtendimentoDTO;
import br.ufg.api.ocd.model.LocalAtendimento;
import br.ufg.api.ocd.service.LocalAtendimentoService;
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
@RequestMapping(value = "/api/localAtendimento")
public class LocalAtendimentoController {

    @Autowired
    private LocalAtendimentoService service;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody LocalAtendimentoDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        service.salvar(modelMapper.map(dto, LocalAtendimento.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@Valid @RequestBody LocalAtendimentoDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        try {
            service.atualizar(modelMapper.map(dto, LocalAtendimento.class));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/byId/{id}")
    public LocalAtendimentoDTO getById(@PathVariable String id) {
        return modelMapper.map(service.findById(id), LocalAtendimentoDTO.class);
    }

    @GetMapping(value = "/byTipo/{tipo}")
    public List<LocalAtendimentoDTO> getByTipoLocalDeAtendimento(
            @PathVariable("tipo") String tipo) {
        return service.getByTipoLocalAtendimento(tipo).stream()
                .map(post -> modelMapper.map(post, LocalAtendimentoDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/byNome/{nome}")
    public List<LocalAtendimentoDTO> getByNome(
            @PathVariable("nome") String nome) {
        return service.getByNome(nome).stream()
                .map(post -> modelMapper.map(post, LocalAtendimentoDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<LocalAtendimentoDTO> getAll() {
        return service.getAll().stream()
                .map(post -> modelMapper.map(post, LocalAtendimentoDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/byDitrito/{distrito}")
    public List<LocalAtendimentoDTO> getByDistrito(
            @PathVariable("distrito") String distrito) {
        return service.getByDistrito(distrito).stream()
                .map(post -> modelMapper.map(post, LocalAtendimentoDTO.class))
                .collect(Collectors.toList());
    }
}
