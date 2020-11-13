package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.ParametrosScheduler;
import br.ufg.api.ocd.repository.ParametrosSchedulerRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParametrosSchedulerService {

    @Autowired
    private ParametrosSchedulerRepository repository;

    public ParametrosScheduler salvar(ParametrosScheduler parametrosScheduler){
        return repository.save(parametrosScheduler);
    }

    public ParametrosScheduler atualizar(ParametrosScheduler parametrosScheduler) throws Exception {
        ParametrosScheduler parametrosSchedulerDB = repository.findById(parametrosScheduler.getId()).get();
        if(parametrosSchedulerDB == null){
            throw new Exception("ParametrosScheduler não existe com esse id: "+parametrosScheduler.getId());
        }
        return repository.save(parametrosScheduler);
    }

    public ParametrosScheduler findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public ParametrosScheduler getValor(@NonNull String chave) {
        return repository.findByChave(chave);
    }

    public List<ParametrosScheduler> getAll() {
        return repository.findAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
