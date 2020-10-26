package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.PacienteDTO;
import br.ufg.api.ocd.model.Paciente;
import br.ufg.api.ocd.service.PacienteService;
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
@RequestMapping(value = "/api/paciente")
public class PacienteController {

    @Autowired
    PacienteService service;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody PacienteDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        Paciente paciente = service.salvar(modelMapper.map(dto, Paciente.class));
        return new ResponseEntity<>(paciente.getId(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@Valid @RequestBody PacienteDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        try {
            service.atualizar(modelMapper.map(dto, Paciente.class));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/byId/{id}")
    public PacienteDTO getById(@PathVariable String id) {
        return modelMapper.map(service.findById(id), PacienteDTO.class);
    }

    @GetMapping(value = "/byCpf/{cpf}")
    public PacienteDTO getByCpf(@PathVariable String cpf) {
        return modelMapper.map(service.findByCpf(cpf), PacienteDTO.class);
    }

    @GetMapping(value = "/byName/{nome}")
    public List<PacienteDTO> getPacientesByNome(
            @PathVariable("nome") String nome) {
        return service.geByNome(nome).stream()
                .map(post -> modelMapper.map(post, PacienteDTO.class))
                .collect(Collectors.toList());
    }

}

