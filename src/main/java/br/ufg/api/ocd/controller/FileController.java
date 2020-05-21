package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.UploadFileDTO;
import br.ufg.api.ocd.model.UploadFile;
import br.ufg.api.ocd.service.FileStorageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public UploadFileDTO uploadFile(@RequestParam("file") MultipartFile arquivo, String idAtendimento) {
        return modelMapper.map(fileStorageService.armazenarArquivo(arquivo, idAtendimento), UploadFileDTO.class);
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileDTO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] arquivos, String idAtendimento) {
        return Arrays.asList(arquivos)
                .stream()
                .map(file -> uploadFile(file, idAtendimento))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{nameArquivo}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String nameArquivo) {
        UploadFile uploadFileDB = fileStorageService.carregarArquivoComoRecurso(nameArquivo);
        if(uploadFileDB == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        InputStreamResource resource = new InputStreamResource(uploadFileDB.getInputStream());

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(uploadFileDB.getSize())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
