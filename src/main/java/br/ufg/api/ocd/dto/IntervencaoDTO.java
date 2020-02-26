package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class IntervencaoDTO {

    @NotNull(message = "Forneça do dados do atendimento da intervenção")
    private AtendimentoDTO atendimento;

    @NotEmpty(message = "Forneça a hipótese do diagnóstico")
    private String hipoteseDiagnostico;

    private String observacao;

    @NotNull(message = "Forneça os procedimentos da intervenção")
    @Size(min=1, message = "Forneça ao menos 1 procedimento")
    private List<ProcedimentosIntervencaoDTO> procedimentos;
}
