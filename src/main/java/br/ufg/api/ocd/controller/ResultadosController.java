package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.ResultadosDTO;
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
@RequestMapping(value = "/api/resultados")
public class ResultadosController {

    @Autowired
    private AtendimentoService atendimentoService;


    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public ResponseEntity<?> salvar(@Valid @RequestBody ResultadosDTO resultados, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        atendimentoService.salvarResultados(resultados);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
