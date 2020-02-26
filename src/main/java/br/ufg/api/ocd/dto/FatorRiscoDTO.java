package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class FatorRiscoDTO {
    private String id;
    @NotEmpty(message = "Forneça o nome do fator de risco")
    private String nome;
}
