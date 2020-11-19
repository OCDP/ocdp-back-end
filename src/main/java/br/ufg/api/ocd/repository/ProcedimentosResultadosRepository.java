package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.ProcedimentosResultados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProcedimentosResultadosRepository extends JpaRepository<ProcedimentosResultados, Long> {

    public List<ProcedimentosResultados> findByAtendimento_Id(Long id);

}
