package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LesaoDTO {
    private String id;
    @NotEmpty(message = "Forneça o nome da lesao")
    private String nome;
}
