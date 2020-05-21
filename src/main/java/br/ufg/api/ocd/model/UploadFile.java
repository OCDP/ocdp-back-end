package br.ufg.api.ocd.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.InputStream;

@Data
@Builder
@Document(collection = "file")
public class UploadFile {
    @Id
    private String id;
    private String name;
    private byte[] bytes;
    private InputStream inputStream;
    private String type;
    private long size;
    private long length;
    private String idAtendimento;
}
