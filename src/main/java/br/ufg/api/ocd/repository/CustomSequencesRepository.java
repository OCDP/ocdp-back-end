package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.CustomSequences;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomSequencesRepository extends JpaRepository<CustomSequences, String> {
}
