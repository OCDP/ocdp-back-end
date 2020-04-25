package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.RegioesLesoes;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface RegioesLesoesRepository extends CrudRepository<RegioesLesoes, String> {

    public List<RegioesLesoes> findByAtendimento_Id(String id);
}
