package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.Distrito;
import br.ufg.api.ocd.model.FatorRisco;
import br.ufg.api.ocd.repository.FatorRiscoRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FatorRiscoService {

    @Autowired
    private FatorRiscoRepository repository;

    @Autowired
    private NextSequenceService nextSequenceService;

    public FatorRisco salvar(FatorRisco fatorRisco) {
        fatorRisco.setId(nextSequenceService.getNextSequence("fatorRisco"));
        return repository.save(fatorRisco);
    }

    public FatorRisco atualizar(FatorRisco fatorRisco) throws Exception {
        FatorRisco fatorRiscoDB = repository.findById(fatorRisco.getId()).get();
        if(fatorRiscoDB == null){
            throw new Exception("FatorRisco não existe com esse id: "+fatorRisco.getId());
        }
        return repository.save(fatorRisco);
    }

    public FatorRisco findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public FatorRisco findByNome(@NonNull String nome) {
        return repository.findByNome(nome);
    }

    public List<FatorRisco> getAll() {
        return repository.findAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
