package br.ufg.api.ocd.service;

import br.ufg.api.ocd.dto.FatorRiscoDTO;
import br.ufg.api.ocd.model.Atendimento;
import br.ufg.api.ocd.model.FatorRisco;
import br.ufg.api.ocd.model.FatorRiscoAcompanhamento;
import br.ufg.api.ocd.repository.FatorRiscoAtendimentoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FatorRiscoAtendimentoService {

    @Autowired
    private FatorRiscoAtendimentoRepository repository;

    @Autowired
    private NextSequenceService nextSequenceService;

    @Autowired
    private ModelMapper modelMapper;

    public void salvarFatorRiscoAcompanhamento(Atendimento atendimento, List<FatorRiscoDTO> fatoresDeRisco) {
        fatoresDeRisco.forEach(fator -> {
            FatorRisco fatorRisco = modelMapper.map(fator, FatorRisco.class);
            FatorRiscoAcompanhamento fatorRiscoAcompanhamento = new FatorRiscoAcompanhamento(fatorRisco, atendimento);
            fatorRiscoAcompanhamento.setId(nextSequenceService.getNextSequence("fatorRiscoAcompanhamento"));
            repository.save(fatorRiscoAcompanhamento);
        });
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
