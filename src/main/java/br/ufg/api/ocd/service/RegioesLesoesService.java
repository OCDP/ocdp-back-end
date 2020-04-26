package br.ufg.api.ocd.service;

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

@Service
@Transactional
public class RegioesLesoesService {

    @Autowired
    private RegioesLesoesRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GenericService genericService;

    public List<RegioesLesoes> getAll() {
        return (List<RegioesLesoes>) repository.findAll();
    }

    public void salvarRegioesLesoes(Atendimento atendimento, List<RegioesLesoesDTO> regioesLesoes) {
        genericService.init(RegioesLesoes.class);
        regioesLesoes.forEach(local -> {
            RegioesLesoes rl = RegioesLesoes.builder()
                    .atendimento(atendimento)
                    .lesao(modelMapper.map(local.getLesao(), Lesao.class))
                    .regiaoBoca(modelMapper.map(local.getRegiaoBoca(), RegiaoBoca.class)).build();
            repository.save(rl);
        });
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
