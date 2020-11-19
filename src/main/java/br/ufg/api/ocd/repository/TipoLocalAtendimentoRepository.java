package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.TipoLocalAtendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TipoLocalAtendimentoRepository extends JpaRepository<TipoLocalAtendimento, Long> {

    public List<TipoLocalAtendimento> findByNome(String nome);

}
