package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.AtendimentoBuscarDTO;
import br.ufg.api.ocd.dto.HistoricoAtendimentoDTO;
import br.ufg.api.ocd.enums.TipoAtendimento;
import br.ufg.api.ocd.service.AtendimentoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "/api/historico")
public class HistoricoPacienteController {

    @Autowired
    private AtendimentoService atendimentoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping(value = "/atendimentos/cpf/{cpf}")
    public List<HistoricoAtendimentoDTO> getAtendimentosByCpfPaciente(@PathVariable("cpf") String cpf) {
        return atendimentoService.getHistoricoPacienteCpf(cpf);
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
