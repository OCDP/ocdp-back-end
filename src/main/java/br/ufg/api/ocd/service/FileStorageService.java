package br.ufg.api.ocd.service;

import br.ufg.api.ocd.exception.FileStorageException;
import br.ufg.api.ocd.model.UploadFile;
import br.ufg.api.ocd.repository.UploadFileRepository;
import br.ufg.api.ocd.util.DataUtil;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class FileStorageService {

    @Autowired
    private NextSequenceService nextSequenceService;

    @Autowired
    private UploadFileRepository uploadFileRepository;

    public String armazenarArquivo(MultipartFile multipartFile, String cpf) {
        try {
            String fileName = multipartFile.getName() + cpf + DataUtil.dateToStringImg(LocalDateTime.now());
            UploadFile uploadFile = UploadFile.builder()
                    .id(nextSequenceService.getNextSequence("file"))
                    .name(fileName.replace(" ", "").replace("/", "-").trim())
                    .cpf(cpf)
                    .bytes(multipartFile.getBytes())
                    .type(multipartFile.getContentType())
                    .size(multipartFile.getSize()).build();

           uploadFileRepository.save(uploadFile);

            return fileName;
        } catch (Exception ex) {
            throw new FileStorageException("Não foi possível armazenar o arquivo " + multipartFile.getName() + ". Por favor, tente novamente!", ex);
        }
    }

    public UploadFile carregarArquivoComoRecurso(@NonNull String fileName) {
        return uploadFileRepository.findByName(fileName);
    }
}
