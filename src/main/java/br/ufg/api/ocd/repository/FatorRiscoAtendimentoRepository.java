package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.FatorRiscoAcompanhamento;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface FatorRiscoAtendimentoRepository extends MongoRepository<FatorRiscoAcompanhamento, String> {
    public List<FatorRiscoAcompanhamento> findByAtendimento_Id(String id);
}
