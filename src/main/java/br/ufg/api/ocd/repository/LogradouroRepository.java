package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Bairro;
import br.ufg.api.ocd.model.Logradouro;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogradouroRepository extends MongoRepository<Logradouro, String> {

    Logradouro findFirstByCep(String cep);

}
