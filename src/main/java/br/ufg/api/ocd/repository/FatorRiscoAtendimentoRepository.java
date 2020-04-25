package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.FatorRiscoAcompanhamento;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface FatorRiscoAtendimentoRepository extends CrudRepository<FatorRiscoAcompanhamento, String> {
    public List<FatorRiscoAcompanhamento> findByAtendimento_Id(String id);
}
