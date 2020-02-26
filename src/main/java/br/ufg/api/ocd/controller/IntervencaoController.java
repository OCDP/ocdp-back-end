package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.IntervencaoDTO;
import br.ufg.api.ocd.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static br.ufg.api.ocd.util.ValidacoesDtoUtil.createErrorString;

@RestController
@RequestMapping(value = "/api/intervencao")
public class IntervencaoController {
    @Autowired
    private AtendimentoService atendimentoService;

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public ResponseEntity<?> salvar(@Valid @RequestBody IntervencaoDTO intervencao, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        atendimentoService.salvarIntervencao(intervencao);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
