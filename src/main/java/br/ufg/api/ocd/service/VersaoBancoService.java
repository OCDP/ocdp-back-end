package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.VersaoBanco;
import br.ufg.api.ocd.repository.VersaoBancoRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersaoBancoService {

    @Autowired
    private VersaoBancoRepository repository;

    @Autowired
    private  NextSequenceService nextSequenceService;

    public VersaoBanco salvar(VersaoBanco versaoBanco) {
        versaoBanco.setId(nextSequenceService.getNextSequence("versaoBanco"));
        return repository.save(versaoBanco);
    }

    public VersaoBanco atualizar(VersaoBanco versaoBanco) throws Exception {
        VersaoBanco versaoBancoDB = repository.findById(versaoBanco.getId()).get();
        if(versaoBancoDB == null){
            throw new Exception("VersaoBanco não existe com esse id: "+versaoBanco.getId());
        }
        versaoBanco.setId(nextSequenceService.getNextSequence("versaoBanco"));
        return repository.save(versaoBanco);
    }

    public VersaoBanco findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public List<VersaoBanco> getAll() {
        return repository.findAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
