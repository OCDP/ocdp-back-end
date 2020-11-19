package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Atendimento;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    public List<Atendimento> findAllByPacienteId(Long pacienteId);
    public List<Atendimento> findAllByLocalAtendimentoId(Long id);
}