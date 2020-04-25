package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SiglaRegiaoBocaDTO {
    private String id;
    private String nome;
    private String imagemBase64;
}
