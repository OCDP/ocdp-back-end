package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, String> {

    public List<Bairro> findByNome(String nome);

    public List<Bairro> findByCidade_NomeOrderByNomeDesc(String nome);

}
