package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class SiglaRegiaoBocaDTO {
    private String id;
    @NotEmpty(message = "Forneça o nome do tipo região")
    private String nome;
    @NotEmpty(message = "Forneça a base64 da imagem do tipo região")
    private String imagemBase64;
}
