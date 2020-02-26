package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ParametrosSchedulerDTO {

    private String id;
    @NotEmpty(message = "Forneça a chave do parametro")
    private String chave;
    @NotEmpty(message = "Forneça o valor do parametro")
    private String valor;
}
