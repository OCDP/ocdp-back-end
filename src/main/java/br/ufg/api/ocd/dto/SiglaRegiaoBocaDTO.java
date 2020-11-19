package br.ufg.api.ocd.dto;

import lombok.Data;

@Data
public class SiglaRegiaoBocaDTO {
    private Long id;
    private String nome;
    private String imagemBase64;
}
