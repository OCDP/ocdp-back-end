package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Atendimento;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;


import java.util.List;

@EnableScan
public interface AtendimentoRepository extends CrudRepository<Atendimento, String> {

    public List<Atendimento> findAllByPaciente_Nome(String nome, Sort sort);
    public List<Atendimento> findAllByPaciente_Cpf(String cpf, Sort sort);
    public List<Atendimento> findAllByLocalAtendimento_Id(String id);
}