package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.TipoLocalAtendimento;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface TipoLocalAtendimentoRepository extends CrudRepository<TipoLocalAtendimento, String> {

    public List<TipoLocalAtendimento> findByNome(String nome);

}
