package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.AcompanhamentoDTO;
import br.ufg.api.ocd.enums.Sexo;
import br.ufg.api.ocd.service.AtendimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static br.ufg.api.ocd.util.ValidacoesDtoUtil.createErrorString;

@RestController
@RequestMapping(value = "/api/acompanhamento")
public class AcompanhamentoController {

    @Autowired
    private AtendimentoService atendimentoService;

    @GetMapping(path = "/sexos")
    public List<Sexo> getSexos() {
        return Arrays.asList(Sexo.values());
    }


    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public ResponseEntity<?> salvarAcompanhamento(@Valid @RequestBody AcompanhamentoDTO acompanhamentoDTO, BindingResult errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(createErrorString(errors), HttpStatus.BAD_REQUEST);
        }
        atendimentoService.salvarAcompanhamento(acompanhamentoDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/uploadFoto")
    public void uploadFile(@RequestParam("file") MultipartFile foto, Long atendimentoId) throws IOException {
        atendimentoService.uploadFoto(foto.getBytes(), atendimentoId);
    }
}
