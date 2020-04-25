package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Cidade;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface CidadeRepository extends CrudRepository<Cidade, String> {

    public Cidade findByNome(String nome);

    public Cidade findByNomeOrderByBairrosDesc(String nome);
}
