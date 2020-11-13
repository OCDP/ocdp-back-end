package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PacienteRepository extends JpaRepository<Paciente, String> {

    public List<Paciente> findByNome(String nome);

//    @Query("{'nome' : { $regex : ?0, $options: 'i' } }")
//    public List<Paciente> findByQuery(String nome);

    public Paciente findByEmail(String email);

    public Paciente findByCpf(String cpf);
}
