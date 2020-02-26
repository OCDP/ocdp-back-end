package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class CidadeDTO {

    private String id;
    @NotEmpty(message = "Forneça o nome do procedimento")
    private String nome;
}

