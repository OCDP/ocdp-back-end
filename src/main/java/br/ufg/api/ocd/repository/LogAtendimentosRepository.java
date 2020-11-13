package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.LogAtendimentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogAtendimentosRepository extends JpaRepository<LogAtendimentos, String> {
    public List<LogAtendimentos> findAllByIdLocalEncaminhado(String idLocal);
    public LogAtendimentos findByIdPaciente(String IdPaciente);
}
