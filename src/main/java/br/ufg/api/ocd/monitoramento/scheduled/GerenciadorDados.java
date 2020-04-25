package br.ufg.api.ocd.monitoramento.scheduled;

import br.ufg.api.ocd.model.LocalAtendimento;
import br.ufg.api.ocd.model.LogAtendimentos;
import br.ufg.api.ocd.model.ParametrosScheduler;
import br.ufg.api.ocd.service.ParametrosSchedulerService;
import br.ufg.api.ocd.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class GerenciadorDados {

    @Autowired
    private ParametrosSchedulerService parametros;

    private List<LogAtendimentos> logAtendimento = new ArrayList<>();
    private List<LogAtendimentos> logNaoCompareceu = new ArrayList<>();
    private List<LogAtendimentos> logSemAgendamento = new ArrayList<>();


    public SumarizacaoDados vericaLogs(LocalAtendimento localAtendimento, List<LogAtendimentos> logs) {

        logs.forEach(logAtendimentos -> {
            if(logAtendimentos.getDataSugeridaAcompanhamento() == null && logAtendimentos.getDataSugeridaTratamento() == null){
                adicionaNaListaSemAgendamento(logAtendimentos, logSemAgendamento, parametros.getValor("diasNaoCompareceuInicial"), parametros.getValor("diasNaoCompareceuFinal"));
            }
            if (logAtendimentos.getDataSugeridaAcompanhamento() != null) {
                adicionaNaLista(logAtendimentos, logAtendimentos.getDataSugeridaAcompanhamento(), logAtendimento, parametros.getValor("diasAcompanhamento"));
                adicionaNaLista(logAtendimentos, logAtendimentos.getDataSugeridaAcompanhamento(), logNaoCompareceu, parametros.getValor("diasAtrasoAcompanhamento"));
            }
            if (logAtendimentos.getDataSugeridaTratamento() != null) {
                adicionaNaLista(logAtendimentos, logAtendimentos.getDataSugeridaTratamento(), logAtendimento, parametros.getValor("diasTratamento"));
                adicionaNaLista(logAtendimentos, logAtendimentos.getDataSugeridaTratamento(), logNaoCompareceu, parametros.getValor("diasAtrasoTratamento"));
            }
        });

        return SumarizacaoDados.builder()
                .localAtendimento(localAtendimento)
                .logSemAgendamento(logSemAgendamento)
                .logAtendimento(logAtendimento)
                .logNaoCompareceu(logNaoCompareceu).build();
    }

    private void adicionaNaLista(LogAtendimentos log, LocalDateTime data, List<LogAtendimentos> listaLogAcompanhamento, ParametrosScheduler parametro) {
        String diferencaAcompanhamento = String.valueOf(DataUtil.diferencaEmMeses(data, LocalDateTime.now()));
        List<String> diasParametro = Arrays.asList(parametro.getValor().split("\\,"));
        if (diasParametro.contains(diferencaAcompanhamento)) {
            listaLogAcompanhamento.add(log);
        }
    }

    private void adicionaNaListaSemAgendamento(LogAtendimentos log, List<LogAtendimentos> listaLog, ParametrosScheduler parametroInicial, ParametrosScheduler parametroFinal) {
        int diferenca = DataUtil.diferencaEmMeses(log.getDataAtendimento(), LocalDateTime.now());

        if (Integer.valueOf(parametroInicial.getValor()) > diferenca && Integer.valueOf(parametroFinal.getValor())< diferenca) {
            listaLog.add(log);
        }
    }

}
