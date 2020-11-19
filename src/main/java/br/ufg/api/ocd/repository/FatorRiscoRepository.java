package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.FatorRisco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FatorRiscoRepository extends JpaRepository<FatorRisco, Long> {
    public FatorRisco findByNome(String nome);
}
