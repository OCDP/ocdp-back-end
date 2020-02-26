package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class DistritoDTO {
    private String id;
    @NotEmpty(message = "Forneça o nome do distrito")
    private String nome;
}
