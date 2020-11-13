package br.ufg.api.ocd.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Builder

public class UploadFile {
    @Id
    private String id;
    private String name;
    private byte[] bytes;
    private String type;
    private long size;
    private long length;
    private String cpf;
}
