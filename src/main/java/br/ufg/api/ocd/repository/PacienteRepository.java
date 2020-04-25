package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Paciente;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.socialsignin.spring.data.dynamodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface PacienteRepository extends CrudRepository<Paciente, String> {

    public List<Paciente> findByNome(String nome);

    @Query(fields = "nome")
    public List<Paciente> findByQuery(String nome);

    public Paciente findByEmail(String email);

    public Paciente findByCpf(String cpf);
}
