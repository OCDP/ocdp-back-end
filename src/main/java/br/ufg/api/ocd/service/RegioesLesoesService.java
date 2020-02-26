package br.ufg.api.ocd.service;

import br.ufg.api.ocd.dto.LesaoDTO;
import br.ufg.api.ocd.dto.RegiaoBocaDTO;
import br.ufg.api.ocd.dto.RegioesLesoesDTO;
import br.ufg.api.ocd.model.Atendimento;
import br.ufg.api.ocd.model.Lesao;
import br.ufg.api.ocd.model.RegiaoBoca;
import br.ufg.api.ocd.model.RegioesLesoes;
import br.ufg.api.ocd.repository.RegioesLesoesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RegioesLesoesService {

    @Autowired
    private RegioesLesoesRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private  NextSequenceService nextSequenceService;

    public List<RegioesLesoes> getAll() {
        return repository.findAll();
    }

    public void salvarRegioesLesoes(Atendimento atendimento, List<RegioesLesoesDTO> regioesLesoes) {
        regioesLesoes.forEach(local -> {
            RegioesLesoes rl = new RegioesLesoes(retornaListaLesoes(local.getLesoes()), retornaListaRegioes(local.getRegioes()), atendimento);
            rl.setId(nextSequenceService.getNextSequence("regioesLesoes"));
            repository.save(rl);
        });
    }

    private List<Lesao> retornaListaLesoes(List<LesaoDTO> lesoes) {
        if(lesoes == null || lesoes.isEmpty()) return null;
        return lesoes.stream()
                .map(lesao -> modelMapper.map(lesao, Lesao.class))
                .collect(Collectors.toList());
    }

    private List<RegiaoBoca> retornaListaRegioes(List<RegiaoBocaDTO> regioes) {
        if(regioes == null || regioes.isEmpty()) return null;
        return regioes.stream()
                .map(regiao -> modelMapper.map(regiao, RegiaoBoca.class))
                .collect(Collectors.toList());
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
