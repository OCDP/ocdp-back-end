package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.LogAtendimentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogAtendimentosRepository extends JpaRepository<LogAtendimentos, Long> {
    public List<LogAtendimentos> findAllByIdLocalEncaminhado(Long idLocal);
    public LogAtendimentos findByIdPaciente(Long IdPaciente);
}
