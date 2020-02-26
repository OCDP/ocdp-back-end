package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.config.security.AuthenticationBean;
import br.ufg.api.ocd.dto.UsuarioDTO;
import br.ufg.api.ocd.enums.NivelAtencao;
import br.ufg.api.ocd.enums.StatusUsuario;
import br.ufg.api.ocd.enums.TipoUsuario;
import br.ufg.api.ocd.model.Usuario;
import br.ufg.api.ocd.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

import static br.ufg.api.ocd.util.ValidacoesDtoUtil.createErrorString;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService service;
    
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<?> salvar(@Valid @RequestBody UsuarioDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        service.salvar(modelMapper.map(dto, Usuario.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> atualizar(@Valid @RequestBody UsuarioDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        try {
            service.atualizar(modelMapper.map(dto, Usuario.class));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/byId/{id}")
    public UsuarioDTO getById(@RequestParam String id) {
        return modelMapper.map(service.findById(id), UsuarioDTO.class);
    }

    @GetMapping(value = "/byCpf/{cpf}")
    public UsuarioDTO getByCpf(@RequestParam String cpf) {
        return modelMapper.map(service.findByCpf(cpf), UsuarioDTO.class);
    }

    @GetMapping(path = "/basicauth")
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("You are authenticated");
    }

    @GetMapping(path = "/status")
    public List<StatusUsuario> getStatus() {
        return Arrays.asList(StatusUsuario.values());
    }

    @GetMapping(path = "/nivelAtencao")
    public List<NivelAtencao> getNivelAtencao() {
        return Arrays.asList(NivelAtencao.values());
    }

    @GetMapping(path = "/tipoUsuario")
    public List<TipoUsuario> getTipoUsuario() {
        return Arrays.asList(TipoUsuario.values());
    }
}

