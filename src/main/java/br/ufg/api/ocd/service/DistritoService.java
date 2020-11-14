package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.Distrito;
import br.ufg.api.ocd.repository.DistritoRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistritoService {
    @Autowired
    private DistritoRepository repository;

    public List<Distrito> getAll() {
        return repository.findAll();
    }

    public Distrito salvar(Distrito distrito) {

        return repository.save(distrito);
    }

    public Distrito findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public Distrito atualizar(Distrito distrito) throws Exception {
        Distrito distritoDB = repository.findById(distrito.getId()).get();
        if (distritoDB == null) {
            throw new Exception("Distrito não existe com esse id: " + distrito.getId());
        }
        return repository.save(distrito);
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
