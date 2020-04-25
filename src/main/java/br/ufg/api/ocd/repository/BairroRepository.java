package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Bairro;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface BairroRepository extends CrudRepository<Bairro, String> {
    public List<Bairro> findByNome(String nome);

    public List<Bairro> findByCidade_NomeOrderByNomeDesc(String nome);

}
