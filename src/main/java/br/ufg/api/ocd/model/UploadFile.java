package br.ufg.api.ocd.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;

import javax.persistence.Entity;

@Entity
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
