package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class RegiaoBocaDTO {
    private Long id;
    @NotEmpty(message = "Forneça o nome da região da boca")
    private String nome;
    private SiglaRegiaoBocaDTO siglaRegiaoBoca;
}
