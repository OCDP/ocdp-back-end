package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.Cidade;
import br.ufg.api.ocd.repository.CidadeRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    @Autowired
    private GenericService genericService;

    public Cidade salvar(Cidade cidade) {
        genericService.init(Cidade.class);
        return repository.save(cidade);
    }

    public Cidade atualizar(Cidade cidade) throws Exception {
        Cidade cidadeDB = repository.findById(cidade.getId()).get();
        if(cidadeDB == null){
            throw new Exception("Cidade não existe com esse id: "+cidade.getId());
        }
        return repository.save(cidade);
    }

    public Cidade findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public Cidade findByNome(@NonNull String nome) {
        return repository.findByNome(nome);
    }

    public List<Cidade> getAll() {
        return (List<Cidade>) repository.findAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void deleteById(String id){
        repository.deleteById(id);
    }
}
