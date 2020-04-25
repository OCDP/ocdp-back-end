package br.ufg.api.ocd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcompanhamentoDTO {

    @NotNull(message = "Forneça do dados do atendimento do acompanhamento")
    private AtendimentoDTO atendimento;

    @NotNull(message = "Forneça os dados dos fatores de risco ")
    @Size(min=1, message = "Forneça ao menos 1 fator de risco")
    private List<FatorRiscoDTO> fatoresDeRisco;
    private List<RegioesLesoesDTO> regioesLesoes;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataSugeridaAcompanhamento;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataSugeridaTratamento;
}
