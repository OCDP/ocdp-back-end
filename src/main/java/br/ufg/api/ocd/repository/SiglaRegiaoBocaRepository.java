package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.SiglaRegiaoBoca;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiglaRegiaoBocaRepository extends MongoRepository<SiglaRegiaoBoca, String> {

}
