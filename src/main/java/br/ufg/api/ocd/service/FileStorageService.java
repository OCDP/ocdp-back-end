package br.ufg.api.ocd.service;

import br.ufg.api.ocd.exception.FileStorageException;
import br.ufg.api.ocd.model.UploadFile;
import br.ufg.api.ocd.repository.UploadFileRepository;
import br.ufg.api.ocd.util.DataUtil;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class FileStorageService {

    @Autowired
    private NextSequenceService nextSequenceService;

    @Autowired
    private UploadFileRepository uploadFileRepository;

    public UploadFile armazenarArquivo(MultipartFile multipartFile, String cpf) {
        try {
            String fileName = multipartFile.getName() + cpf + DataUtil.dateToString(LocalDateTime.now());
            UploadFile uploadFile = UploadFile.builder()
                    .id(nextSequenceService.getNextSequence("file"))
                    .name(fileName.replace(" ", "").replace("/", "-").trim())
                    .bytes(multipartFile.getBytes())
                    .type(multipartFile.getContentType())
                    .size(multipartFile.getSize()).build();

            UploadFile save = uploadFileRepository.save(uploadFile);

            return save;
        } catch (Exception ex) {
            throw new FileStorageException("Não foi possível armazenar o arquivo " + multipartFile.getName() + ". Por favor, tente novamente!", ex);
        }
    }

    public UploadFile carregarArquivoComoRecurso(@NonNull String fileName) {
        final UploadFile uploadFile = uploadFileRepository.findByName(fileName);
        if (uploadFile != null) {
            uploadFile.setLength(retorneLength(uploadFile, fileName));
        }
        return uploadFile;
    }

    private long retorneLength(UploadFile uploadFile, String fileName) {
        File file = null;
        try {
            file = new File("src/main/resources/targetFile.jpeg");
            FileOutputStream fos = new FileOutputStream(file);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos.write(uploadFile.getBytes());
            fos.flush();
            if (fos != null) {
                fos.close();
            }
        } catch (FileNotFoundException ex) {
            throw new FileStorageException("Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", ex);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return file.length();
    }
}
