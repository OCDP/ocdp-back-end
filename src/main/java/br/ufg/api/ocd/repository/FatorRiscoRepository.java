package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.FatorRisco;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FatorRiscoRepository extends MongoRepository<FatorRisco, String> {
    public FatorRisco findByNome(String nome);
}
