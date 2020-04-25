package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.Lesao;
import br.ufg.api.ocd.repository.LesaoRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LesaoService {
    @Autowired
    private LesaoRepository repository;
    @Autowired
    private NextSequenceService nextSequenceService;

    public List<Lesao> getAll() {
        return repository.findAll();
    }

    public Lesao salvar(Lesao lesao) {
        lesao.setId(nextSequenceService.getNextSequence("lesao"));
        return repository.save(lesao);
    }

    public Lesao atualizar(Lesao lesao) throws Exception {
        Lesao lesaoDB = repository.findById(lesao.getId()).get();
        if(lesaoDB == null){
            throw new Exception("Lesao não existe com esse id: "+lesao.getId());
        }
        return repository.save(lesao);
    }

    public Lesao findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public List<Lesao> findByTipo(@NonNull String nome) {
        return repository.findByTipoLesao_Nome(nome);
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
