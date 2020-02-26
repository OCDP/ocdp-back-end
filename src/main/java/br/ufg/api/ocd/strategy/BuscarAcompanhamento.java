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
        return fatorRiscoAtendimentoRepository.findByAtendimento_Id(atendimento.getId());

    }

    private void addRegioesLesoes(final List<RegioesLesoes> regioesLesoes) {
        if (regioesLesoes != null && !regioesLesoes.isEmpty()) {
            List<RegioesLesoesDTO> regioesLesoesLista = new ArrayList<>();
            regioesLesoes.forEach(reLe -> {
                RegioesLesoesDTO regioesLesoesDTO = new RegioesLesoesDTO();
                regioesLesoesDTO.setLesoes(retornaListaLesao(reLe));
                regioesLesoesDTO.setRegioes(retornaListaRegiao(reLe));
                regioesLesoesLista.add(regioesLesoesDTO);
            });
            dto.setRegioesLesoes(regioesLesoesLista);
        }
    }

    private List<LesaoDTO> retornaListaLesao(RegioesLesoes regioesLesoes){
       return regioesLesoes.getLesoes().stream().map(lesao -> modelMapper.map(lesao, LesaoDTO.class)).collect(Collectors.toList());
    }

    private List<RegiaoBocaDTO> retornaListaRegiao(RegioesLesoes regioesLesoes){
        return regioesLesoes.getRegioes().stream().map(regiao -> modelMapper.map(regiao, RegiaoBocaDTO.class)).collect(Collectors.toList());
    }

    private void addFatorRisco(final List<FatorRiscoAcompanhamento> fatorRiscoAcompanhamentos) {
        if (fatorRiscoAcompanhamentos != null && !fatorRiscoAcompanhamentos.isEmpty()) {
            List<FatorRiscoDTO> fatoresDto = new ArrayList<>();
            fatorRiscoAcompanhamentos.forEach(fator -> {
                fatoresDto.add(modelMapper.map(fator, FatorRiscoDTO.class));
            });
            dto.setFatoresDeRisco(fatoresDto);
        }
    }

}
