package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.ParametrosSchedulerDTO;
import br.ufg.api.ocd.dto.VersaoBancoDTO;
import br.ufg.api.ocd.model.ParametrosScheduler;
import br.ufg.api.ocd.model.VersaoBanco;
import br.ufg.api.ocd.service.ParametrosSchedulerService;
import br.ufg.api.ocd.service.VersaoBancoService;
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
@RequestMapping(value = "/api/admin")
public class AdminController {

    @Autowired
    private ParametrosSchedulerService parametrosService;

    @Autowired
    private VersaoBancoService versaoBancoService;

    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("versaoBanco")
    public ResponseEntity<?> atualizarVersaoBanco(@Valid @RequestBody VersaoBancoDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        try {
            versaoBancoService.atualizar(modelMapper.map(dto, VersaoBanco.class));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "versaoBanco/byId/{id}")
    public VersaoBancoDTO getByIdVersaoBanco(@RequestParam String id) {
        return modelMapper.map(versaoBancoService.findById(id), VersaoBancoDTO.class);
    }

    @GetMapping("versaoBanco")
    public List<VersaoBancoDTO> getVersaoBanco() {
        return versaoBancoService.getAll().stream()
                .map(post -> modelMapper.map(post, VersaoBancoDTO.class))
                .collect(Collectors.toList());
    }

    @PutMapping
    public ResponseEntity<?> atualizarParametrosScheduler(@Valid @RequestBody ParametrosSchedulerDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        try {
            parametrosService.atualizar(modelMapper.map(dto, ParametrosScheduler.class));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/byId/{id}")
    public ParametrosSchedulerDTO getByIdParametrosScheduler(@RequestParam String id) {
        return modelMapper.map(parametrosService.findById(id), ParametrosSchedulerDTO.class);
    }

    @GetMapping
    public List<ParametrosSchedulerDTO> getCParametrosScheduler() {
        return parametrosService.getAll().stream()
                .map(post -> modelMapper.map(post, ParametrosSchedulerDTO.class))
                .collect(Collectors.toList());
    }

}
