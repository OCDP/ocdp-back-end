package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.SiglaRegiaoBoca;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface SiglaRegiaoBocaRepository extends CrudRepository<SiglaRegiaoBoca, String> {

}
