package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class LocalAtendimentoDTO {
    private Long id;
    @NotEmpty(message = "Forneça o nome do local de atendimento")
    private String nome;
    @NotNull(message = "Forneça do dados do distrito")
    private DistritoDTO distrito;
    @NotNull(message = "Forneça do dados do tipo de local de atendimento")
    private TipoLocalAtendimentoDTO tipoLocalAtendimento;
    @NotEmpty(message = "Forneça o email do responsavel")
    private String emailResponsavel;
    @NotEmpty(message = "Forneça o nome do responsavel")
    private String nomeResponsavel;
}
