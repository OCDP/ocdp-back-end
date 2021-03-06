package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.RegiaoBoca;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegiaoBocaRepository extends MongoRepository<RegiaoBoca, String> {

    public List<RegiaoBoca> findRegiaoBocaBySiglaRegiaoBoca_Nome(String sigla);

}
