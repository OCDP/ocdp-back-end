package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Distrito;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface DistritoRepository extends CrudRepository<Distrito, String> {
    public List<Distrito> findByNome(String nome);
}
