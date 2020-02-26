package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Cidade;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends MongoRepository<Cidade, String> {

    public Cidade findByNome(String nome);

    public Cidade findByNomeOrderByBairrosDesc(String nome);
}
