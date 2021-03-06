package br.ufg.api.ocd.dto;

import lombok.Data;

@Data
public class UploadFileDTO {
    private String name;
    private byte[] bytes;
    private String type;
    private long size;
    private long length;
    private String cpf;
}
