package br.ufg.api.ocd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.InputStream;

@Data
@Builder
@AllArgsConstructor
public class UploadFileDTO {
    private String name;
    private byte[] bytes;
    private InputStream inputStream;
    private String type;
    private long size;
    private long length;
    private String idAtendimento;
}
