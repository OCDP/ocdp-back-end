package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.LogAtendimentos;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LogAtendimentosRepository extends MongoRepository<LogAtendimentos, String> {
    public List<LogAtendimentos> findAllByIdLocalEncaminhado(String idLocal);
    public LogAtendimentos findByIdPaciente(String IdPaciente);
}
