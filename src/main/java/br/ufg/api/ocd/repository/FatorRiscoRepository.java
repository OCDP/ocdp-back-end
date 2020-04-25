package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.FatorRisco;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface FatorRiscoRepository extends CrudRepository<FatorRisco, String> {
    public FatorRisco findByNome(String nome);
}
