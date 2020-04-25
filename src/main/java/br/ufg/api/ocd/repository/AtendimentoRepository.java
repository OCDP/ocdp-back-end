package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Atendimento;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtendimentoRepository extends MongoRepository<Atendimento, String> {

    public List<Atendimento> findAllByPaciente_Nome(String nome, Sort sort);
    public List<Atendimento> findAllByPaciente_Cpf(String cpf, Sort sort);
    public List<Atendimento> findAllByLocalAtendimento_Id(String id);
}