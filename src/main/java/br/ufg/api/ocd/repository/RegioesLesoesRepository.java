package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.RegioesLesoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RegioesLesoesRepository extends JpaRepository<RegioesLesoes, Long> {

    public List<RegioesLesoes> findByAtendimento_Id(Long id);
}
