package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.RegioesLesoes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegioesLesoesRepository extends MongoRepository<RegioesLesoes, String> {

    public List<RegioesLesoes> findByAtendimento_Id(String id);
}
