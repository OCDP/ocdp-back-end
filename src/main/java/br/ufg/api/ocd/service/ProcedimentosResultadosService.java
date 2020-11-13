package br.ufg.api.ocd.service;

import br.ufg.api.ocd.dto.ProcedimentosIntervencaoDTO;
import br.ufg.api.ocd.dto.ProcedimentosResultadosDTO;
import br.ufg.api.ocd.model.Atendimento;
import br.ufg.api.ocd.model.ProcedimentosResultados;
import br.ufg.api.ocd.repository.ProcedimentosResultadosRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProcedimentosResultadosService {

    @Autowired
    private ProcedimentosResultadosRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired


    public void salvaIntervencaoProcedimentos(Atendimento atendimento, List<ProcedimentosIntervencaoDTO> procedimentos) {
        procedimentos.forEach(procedimento -> {
            ProcedimentosResultados procedimentoR = modelMapper.map(procedimento, ProcedimentosResultados.class);
            procedimentoR.setAtendimento(atendimento);

            repository.save(procedimentoR);
        });
    }

    public void salvaResultadosProcedimentos(Atendimento atendimento, List<ProcedimentosResultadosDTO> procedimentos) {
        procedimentos.forEach(procedimento -> {
            ProcedimentosResultados procedimentoR = new ProcedimentosResultados();
            procedimentoR.setNome(procedimento.getNome());
            procedimentoR.setAtendimento(atendimento);
            procedimentoR.setNomeArquivo(procedimento.getNomeArquivo());

            repository.save(procedimentoR);
        });
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
