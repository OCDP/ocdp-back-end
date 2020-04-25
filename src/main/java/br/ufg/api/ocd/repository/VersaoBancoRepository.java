package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.VersaoBanco;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface VersaoBancoRepository extends CrudRepository<VersaoBanco, String> {
}
