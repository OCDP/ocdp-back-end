package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Atendimento;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, String> {

    public List<Atendimento> findAllByPacienteId(String nome);
    public List<Atendimento> findAllByLocalAtendimentoId(String id);
}