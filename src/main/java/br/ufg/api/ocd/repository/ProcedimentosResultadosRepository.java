package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.ProcedimentosResultados;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcedimentosResultadosRepository extends MongoRepository<ProcedimentosResultados, String> {

    public List<ProcedimentosResultados> findByAtendimento_Id(String id);

}
