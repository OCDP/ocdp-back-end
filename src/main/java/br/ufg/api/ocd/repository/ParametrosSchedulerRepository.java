package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.ParametrosScheduler;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface ParametrosSchedulerRepository  extends CrudRepository<ParametrosScheduler, String> {
    public ParametrosScheduler findByChave(String chave);
}
