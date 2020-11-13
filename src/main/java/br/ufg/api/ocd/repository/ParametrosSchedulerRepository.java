package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.ParametrosScheduler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametrosSchedulerRepository  extends JpaRepository<ParametrosScheduler, String> {
    public ParametrosScheduler findByChave(String chave);
}
