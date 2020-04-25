package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Lesao;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface LesaoRepository extends CrudRepository<Lesao, String> {

    public List<Lesao> findByTipoLesao_Nome(String nome);

}
