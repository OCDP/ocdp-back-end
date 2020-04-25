package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.LocalAtendimento;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface LocalAtendimentoRepository extends CrudRepository<LocalAtendimento, String> {
    public List<LocalAtendimento> findByNome(String nome);

    public List<LocalAtendimento> findByDistrito_Nome(String nome);

    public List<LocalAtendimento> findByAndTipoLocalAtendimento_Nome(String nome);
}
