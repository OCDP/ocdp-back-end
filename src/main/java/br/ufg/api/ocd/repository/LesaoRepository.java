package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Lesao;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LesaoRepository extends MongoRepository<Lesao, String> {

    public List<Lesao> findByTipoLesao_Nome(String nome);

}
