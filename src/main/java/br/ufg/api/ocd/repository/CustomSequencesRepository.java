package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.CustomSequences;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomSequencesRepository extends MongoRepository<CustomSequences, String> {
}
