package br.ufg.api.ocd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class AcompanhamentoDTO {

    @NotNull(message = "Forneça do dados do atendimento do acompanhamento")
    private AtendimentoDTO atendimento;

    @NotNull(message = "Forneça os dados dos fatores de risco ")
    @Size(min=1, message = "Forneça ao menos 1 fator de risco")
    private List<FatorRiscoDTO> fatoresDeRisco;
    private List<RegioesLesoesDTO> regioesLesoes;

    @JsonFormat(pattern="dd-MM-yyyyy HH:mm:ss")
    private Date dataSugeridaAcompanhamento;

    @JsonFormat(pattern="dd-MM-yyyyy HH:mm:ss")
    private Date dataSugeridaTratamento;
}
