package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.LocalAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LocalAtendimentoRepository extends JpaRepository<LocalAtendimento, String> {
    public List<LocalAtendimento> findByNome(String nome);

    public List<LocalAtendimento> findByDistrito_Nome(String nome);

    public List<LocalAtendimento> findByAndTipoLocalAtendimento_Nome(String nome);
}
