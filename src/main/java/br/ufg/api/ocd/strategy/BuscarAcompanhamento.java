package br.ufg.api.ocd.strategy;

import br.ufg.api.ocd.dto.*;
import br.ufg.api.ocd.model.Atendimento;
import br.ufg.api.ocd.model.FatorRiscoAcompanhamento;
import br.ufg.api.ocd.model.RegioesLesoes;
import br.ufg.api.ocd.repository.FatorRiscoAtendimentoRepository;
import br.ufg.api.ocd.repository.RegioesLesoesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuscarAcompanhamento implements EstrategiaBusca {

    @Autowired
    private RegioesLesoesRepository regioesLesoesRepository;

    @Autowired
    private FatorRiscoAtendimentoRepository fatorRiscoAtendimentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AtendimentoBuscarDTO acao(final Atendimento atendimento) {
        criaDTO(atendimento);
        return dto;
    }

    private void criaDTO(final Atendimento atendimento) {
        addAtendimento(atendimento);
        addRegioesLesoes(buscaRegioesLesoes(atendimento));
        addFatorRisco(buscaFatoresRisco(atendimento));
    }

    private void addAtendimento(final Atendimento atendimento) {
        if (atendimento != null) dto.setAtendimento(modelMapper.map(atendimento, AtendimentoDTO.class));
    }

    private List<RegioesLesoes> buscaRegioesLesoes(final Atendimento atendimento){
        return regioesLesoesRepository.findByAtendimento_Id(atendimento.getId());

    }

    private List<FatorRiscoAcompanhamento> buscaFatoresRisco(final Atendimento atendimento){
        return fatorRiscoAtendimentoRepository.findAllByAtendimento_Id(atendimento.getId());

    }

    private void addRegioesLesoes(final List<RegioesLesoes> regioesLesoes) {
        if (regioesLesoes != null && !regioesLesoes.isEmpty()) {
            List<RegioesLesoesDTO> regioesLesoesLista = new ArrayList<>();
            regioesLesoes.forEach(reLe -> {
                RegioesLesoesDTO regioesLesoesDTO = new RegioesLesoesDTO();
                regioesLesoesDTO.setLesao(modelMapper.map(reLe.getLesao(), LesaoDTO.class));
                regioesLesoesDTO.setRegiaoBoca(modelMapper.map(reLe.getRegiaoBoca(), RegiaoBocaDTO.class));
                regioesLesoesLista.add(regioesLesoesDTO);
            });
            dto.setRegioesLesoes(regioesLesoesLista);
        }
    }

    private void addFatorRisco(final List<FatorRiscoAcompanhamento> fatorRiscoAcompanhamentos) {
        if (fatorRiscoAcompanhamentos != null && !fatorRiscoAcompanhamentos.isEmpty()) {
            List<FatorRiscoDTO> fatoresDto = new ArrayList<>();
            fatorRiscoAcompanhamentos.forEach(fator -> {
                fatoresDto.add(modelMapper.map(fator.getFatorRisco(), FatorRiscoDTO.class));
            });
            dto.setFatoresDeRisco(fatoresDto);
        }
    }

}
