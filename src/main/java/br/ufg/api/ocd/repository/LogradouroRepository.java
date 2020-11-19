package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Logradouro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogradouroRepository extends JpaRepository<Logradouro, Long> {

    Logradouro findFirstByCep(String cep);

}
