package br.ufg.api.ocd.repository;

import br.ufg.api.ocd.model.UploadFile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadFileRepository extends MongoRepository<UploadFile, String> {

    public UploadFile findByName(String nome);

    public List<UploadFile> findByCpf(String cpf);
}
