package br.ufg.api.ocd.monitoramento.scheduled;

import br.ufg.api.ocd.model.LocalAtendimento;
import br.ufg.api.ocd.model.LogAtendimentos;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SumarizacaoDados {
    LocalAtendimento localAtendimento;
    List<LogAtendimentos> logAtendimento;
    List<LogAtendimentos> logNaoCompareceu;
    List<LogAtendimentos> logSemAgendamento;
}
