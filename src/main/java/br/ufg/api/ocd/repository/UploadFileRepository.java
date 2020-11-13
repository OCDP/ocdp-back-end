package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UploadFileRepository extends JpaRepository<UploadFile, String> {

    public UploadFile findByName(String nome);

    public List<UploadFile> findByCpf(String cpf);
}
