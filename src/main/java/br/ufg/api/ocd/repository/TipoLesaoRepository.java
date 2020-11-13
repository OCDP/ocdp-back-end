package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.TipoLesao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface TipoLesaoRepository extends JpaRepository<TipoLesao, String> {

}
