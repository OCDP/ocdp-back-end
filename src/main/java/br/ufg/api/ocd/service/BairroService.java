package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.Bairro;
import br.ufg.api.ocd.repository.BairroRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BairroService {

    @Autowired
    private BairroRepository repository;




    public Bairro salvar(Bairro bairro) {

        return repository.save(bairro);
    }

    public Bairro atualizar(Bairro bairro) throws Exception {
        Bairro bairroDB = repository.findById(bairro.getId()).get();
        if (bairroDB == null) {
            throw new Exception("Bairro não existe com esse id: " + bairro.getId());
        }
        return repository.save(bairro);
    }

    public Bairro findById(@NonNull String id) {
        return repository.findById(id).get();
    }

    public List<Bairro> geByNomeCidade(String nomeCidade) {
        return repository.findByCidade_NomeOrderByNomeDesc(nomeCidade);
    }

    public List<Bairro> getAll() {
        return repository.findAll();
    }

    public void deleteAll(){
        repository.deleteAll();
    }

}
