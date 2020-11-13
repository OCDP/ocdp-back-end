package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.RegiaoBoca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RegiaoBocaRepository extends JpaRepository<RegiaoBoca, String> {

    public List<RegiaoBoca> findRegiaoBocaBySiglaRegiaoBoca_Nome(String sigla);

}
