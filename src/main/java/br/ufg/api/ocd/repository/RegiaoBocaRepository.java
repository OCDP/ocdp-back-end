package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.RegiaoBoca;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface RegiaoBocaRepository extends CrudRepository<RegiaoBoca, String> {

    public List<RegiaoBoca> findRegiaoBocaBySiglaRegiaoBoca_Nome(String sigla);

}
