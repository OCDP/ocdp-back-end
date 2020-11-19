package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.FatorRiscoAcompanhamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FatorRiscoAtendimentoRepository extends JpaRepository<FatorRiscoAcompanhamento, Long> {
    public List<FatorRiscoAcompanhamento> findAllByAtendimento_Id(Long id);
}
