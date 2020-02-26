package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.ParametrosSchedulerDTO;
import br.ufg.api.ocd.model.ParametrosScheduler;
import br.ufg.api.ocd.service.ParametrosSchedulerService;
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
@RequestMapping(value = "/api/parametrosScheduler")
public class ParametrosSchedulerController {

    @Autowired
    private ParametrosSchedulerService service;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping
    public ResponseEntity<?> atualizar(@Valid @RequestBody ParametrosSchedulerDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        try {
            service.atualizar(modelMapper.map(dto, ParametrosScheduler.class));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/byId/{id}")
    public ParametrosSchedulerDTO getById(@RequestParam String id) {
        return modelMapper.map(service.findById(id), ParametrosSchedulerDTO.class);
    }

    @GetMapping
    public List<ParametrosSchedulerDTO> getParametrosSchedulers() {
        return service.getAll().stream()
                .map(post -> modelMapper.map(post, ParametrosSchedulerDTO.class))
                .collect(Collectors.toList());
    }

}
