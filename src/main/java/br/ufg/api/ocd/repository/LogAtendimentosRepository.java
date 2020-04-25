package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.LogAtendimentos;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface LogAtendimentosRepository  extends CrudRepository<LogAtendimentos, String> {
    public List<LogAtendimentos> findAllByIdLocalEncaminhado(String idLocal);
    public LogAtendimentos findByIdPaciente(String IdPaciente);
}
