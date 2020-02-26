package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class ResultadosDTO {

    @NotNull(message = "Forneça do dados do atendimento do acompanhamento")
    private AtendimentoDTO atendimento;

    @NotNull(message = "Forneça a confirmacao do rastreamento")
    private Boolean confirmaRastreamento;

    @NotNull(message = "Forneça o diagnostico final do antedimento")
    private String diagnosticoFinal;

    @NotNull(message = "Forneça os procedimentos da intervenção")
    @Size(min=1, message = "Forneça ao menos 1 procedimento")
    private List<ProcedimentosResultadosDTO> procedimentos;

}
