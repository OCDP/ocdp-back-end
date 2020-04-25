package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class RegiaoBocaDTO {
    private String id;
    @NotEmpty(message = "Forneça o nome da região da boca")
    private String nome;
    private SiglaRegiaoBocaDTO siglaRegiaoBoca;
}
