package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.BairroDTO;
import br.ufg.api.ocd.dto.UsuarioDTO;
import br.ufg.api.ocd.model.Bairro;
import br.ufg.api.ocd.service.BairroService;
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
@RequestMapping(value = "/api/bairro")
public class BairroController {

    @Autowired
    private BairroService service;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(consumes = {"application/json"} ,produces = {"application/json"}, method = RequestMethod.POST)
    public ResponseEntity<?> salvar(@Valid @RequestBody BairroDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        service.salvar(modelMapper.map(dto, Bairro.class));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(consumes = {"application/json"} ,produces = {"application/json"}, method = RequestMethod.PUT)
    public ResponseEntity<?> atualizar(@Valid @RequestBody BairroDTO dto, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        try {
            service.atualizar(modelMapper.map(dto, Bairro.class));
        } catch (Exception e) {
            return new ResponseEntity<>(e.getCause(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/byId/{id}")
    public BairroDTO getById(@PathVariable String id) {
        return modelMapper.map(service.findById(id), BairroDTO.class);
    }

    @GetMapping(value = "/byNome/{nome}")
    public BairroDTO getByName(@PathVariable String nome) {
        return modelMapper.map(service.findByNome(nome), BairroDTO.class);
    }


    @GetMapping(value = "/byCidade/{nomeCidade}")
    public List<BairroDTO> getByNomeCidade(@PathVariable String nomeCidade) {
        return service.geByNomeCidade(nomeCidade).stream()
                .map(post -> modelMapper.map(post, BairroDTO.class))
                .collect(Collectors.toList());
    }

}
