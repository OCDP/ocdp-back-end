package br.ufg.api.ocd.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoricoAtendimentoDTO {
    private String idAtendimento;
    private String tipoAtendiemtento;
    private String diferencaMeses;
    private String localAtendimento;
    private String profissionalDeSaude;
    private String dataAtendimento;
}
