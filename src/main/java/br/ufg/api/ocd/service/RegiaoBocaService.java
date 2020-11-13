package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.RegiaoBoca;
import br.ufg.api.ocd.repository.RegiaoBocaRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegiaoBocaService {
    @Autowired
    private RegiaoBocaRepository repository;



    public List<RegiaoBoca> getAll() {
        return repository.findAll();
    }

    public List<RegiaoBoca> getBySigla(@NonNull String sigla) {
        return repository.findRegiaoBocaBySiglaRegiaoBoca_Nome(sigla);
    }

    public RegiaoBoca salvar(RegiaoBoca regiaoBoca) {

        return repository.save(regiaoBoca);
    }

    public RegiaoBoca findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public RegiaoBoca atualizar(RegiaoBoca regiaoBoca) throws Exception {
        RegiaoBoca regiaoBocaDB = repository.findById(regiaoBoca.getId()).get();
        if(regiaoBocaDB == null){
            throw new Exception("RegiaoBoca não existe com esse id: "+regiaoBoca.getId());
        }
        return repository.save(regiaoBoca);
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
