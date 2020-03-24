package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.AtendimentoBuscarDTO;
import br.ufg.api.ocd.dto.HistoricoAtendimentoDTO;
import br.ufg.api.ocd.dto.PacienteDTO;
import br.ufg.api.ocd.enums.TipoAtendimento;
import br.ufg.api.ocd.service.AtendimentoService;
import br.ufg.api.ocd.service.PacienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/historico")
public class HistoricoPacienteController {

    @Autowired
    private AtendimentoService atendimentoService;

    @Autowired
    private PacienteService pacienteService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/pacientes/{nome}")
    public List<PacienteDTO> getPacientesByNome(
            @PathVariable("nome") String nome) {
        return pacienteService.geByNome(nome).stream()
                .map(post -> modelMapper.map(post, PacienteDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/atendimentos/{nomePaciente}")
    public List<HistoricoAtendimentoDTO> getAtendimentosByNomePaciente(@PathVariable("nomePaciente") String nomePaciente) {
        return atendimentoService.getHistoricoPaciente(nomePaciente);
    }

    @GetMapping(path = "/tiposAtendimentos")
    public List<TipoAtendimento> getTipos() {
        return Arrays.asList(TipoAtendimento.values());
    }

    @GetMapping(value = "/atendimento/{id}")
    public AtendimentoBuscarDTO getAtendimentosById(@PathVariable("id") String id) {
        return atendimentoService.getAtendimentosById(id);
    }

}
