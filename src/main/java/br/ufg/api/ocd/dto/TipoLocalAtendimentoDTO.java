package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class TipoLocalAtendimentoDTO {

    private Long id;
    @NotEmpty(message = "Forneça o nome do tipo local atendimento")
    private String nome;
}
