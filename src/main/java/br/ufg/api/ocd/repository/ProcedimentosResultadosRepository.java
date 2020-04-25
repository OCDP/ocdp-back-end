package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.ProcedimentosResultados;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface ProcedimentosResultadosRepository extends CrudRepository<ProcedimentosResultados, String> {

    public List<ProcedimentosResultados> findByAtendimento_Id(String id);

}
