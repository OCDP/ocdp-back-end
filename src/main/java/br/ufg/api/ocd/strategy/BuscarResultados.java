package br.ufg.api.ocd.strategy;

import br.ufg.api.ocd.dto.AtendimentoBuscarDTO;
import br.ufg.api.ocd.dto.AtendimentoDTO;
import br.ufg.api.ocd.dto.ProcedimentosResultadosDTO;
import br.ufg.api.ocd.model.Atendimento;
import br.ufg.api.ocd.model.ProcedimentosResultados;
import br.ufg.api.ocd.repository.ProcedimentosResultadosRepository;
import br.ufg.api.ocd.util.GzipUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuscarResultados implements EstrategiaBusca {

    @Autowired
    private ProcedimentosResultadosRepository procedimentosResultadosRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AtendimentoBuscarDTO acao(final Atendimento atendimento) {
        criaDTO(atendimento);
        return dto;
    }


    private void criaDTO(final Atendimento atendimento) {
        addAtendimento(atendimento);
        addProcedimentos(procedimentosResultadosRepository.findByAtendimento_Id(atendimento.getId()));
    }

    private void addAtendimento(final Atendimento atendimento) {
        if (atendimento != null) dto.setAtendimento(modelMapper.map(atendimento, AtendimentoDTO.class));
    }


    private void addProcedimentos(final List<ProcedimentosResultados> procedimentos) {
        if (procedimentos != null && !procedimentos.isEmpty()) {
            List<ProcedimentosResultadosDTO> procedimentoDto = new ArrayList<>();
            procedimentos.forEach(procedimento -> {
                procedimentoDto.add(ProcedimentosResultadosDTO.builder()
                        .id(procedimento.getId())
                        .nome(procedimento.getNome())
                        .anexo64(GzipUtil.unzip(procedimento.getAnexo()))
                        .observacao(procedimento.getObservacao()).build());
            });
            dto.setProcedimentos(procedimentoDto);
        }
    }
}
