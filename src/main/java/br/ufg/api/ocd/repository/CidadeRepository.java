package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    public Cidade findByNome(String nome);

    public Cidade findByNomeOrderByBairrosDesc(String nome);
}
