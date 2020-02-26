package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.ParametrosScheduler;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParametrosSchedulerRepository  extends MongoRepository<ParametrosScheduler, String> {
    public ParametrosScheduler findByChave(String chave);
}
