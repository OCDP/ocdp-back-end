package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.VersaoBanco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersaoBancoRepository extends JpaRepository<VersaoBanco, String> {
}
