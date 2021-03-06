package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.VersaoBanco;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersaoBancoRepository extends MongoRepository<VersaoBanco, String> {
}
