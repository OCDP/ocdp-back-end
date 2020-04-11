package br.ufg.api.ocd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IntervencaoDTO {

    @NotNull(message = "Forneça do dados do atendimento da intervenção")
    private AtendimentoDTO atendimento;

    @NotEmpty(message = "Forneça a hipótese do diagnóstico")
    private String hipoteseDiagnostico;

    private String observacao;

    private Boolean confirmaRastreamento;

    @NotNull(message = "Forneça os procedimentos da intervenção")
    @Size(min=1, message = "Forneça ao menos 1 procedimento")
    private List<ProcedimentosIntervencaoDTO> procedimentos;
}
