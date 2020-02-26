package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TipoLesaoDTO {

    private String id;
    @NotEmpty(message = "Forneça o nome do tipo lesão")
    private String nome;
}
