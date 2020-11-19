package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Lesao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LesaoRepository extends JpaRepository<Lesao, Long> {

    public List<Lesao> findByTipoLesao_Nome(String nome);

}
