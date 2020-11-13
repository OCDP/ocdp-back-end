package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.SiglaRegiaoBoca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SiglaRegiaoBocaRepository extends JpaRepository<SiglaRegiaoBoca, String> {

}
