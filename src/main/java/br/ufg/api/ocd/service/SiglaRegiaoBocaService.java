package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.SiglaRegiaoBoca;
import br.ufg.api.ocd.repository.SiglaRegiaoBocaRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiglaRegiaoBocaService {
    @Autowired
    private SiglaRegiaoBocaRepository repository;

    @Autowired
    private NextSequenceService nextSequenceService;

    public SiglaRegiaoBoca salvar(SiglaRegiaoBoca siglaRegiaoBoca) {
        siglaRegiaoBoca.setId(nextSequenceService.getNextSequence("siglaRegiaoBoca"));
        return repository.save(siglaRegiaoBoca);
    }

    public SiglaRegiaoBoca atualizar(SiglaRegiaoBoca siglaRegiaoBoca) throws Exception {
        SiglaRegiaoBoca siglaRegiaoBocaDB = repository.findById(siglaRegiaoBoca.getId()).get();
        if(siglaRegiaoBocaDB == null){
            throw new Exception("SiglaRegiaoBoca não existe com esse id: "+siglaRegiaoBoca.getId());
        }
        return repository.save(siglaRegiaoBoca);
    }

    public SiglaRegiaoBoca findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public List<SiglaRegiaoBoca> getAll() {
        return repository.findAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
