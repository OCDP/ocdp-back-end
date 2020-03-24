package br.ufg.api.ocd.monitoramento.scheduled;

import br.ufg.api.ocd.model.LocalAtendimento;
import br.ufg.api.ocd.model.LogAtendimentos;
import br.ufg.api.ocd.monitoramento.email.EmailComponent;
import br.ufg.api.ocd.monitoramento.sms.SmsComponent;
import br.ufg.api.ocd.service.LocalAtendimentoService;
import br.ufg.api.ocd.service.LogAtendimentosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.List;

@Component
//@EnableScheduling
public class SchedulerAlertaRetorno {

    @Autowired
    private LocalAtendimentoService localAtendimentoService;

    @Autowired
    private LogAtendimentosService logService;

    @Autowired
    private GerenciadorDados gerenciadorDados;

    @Autowired
    private EmailComponent emailComponent;

    @Autowired
    private SmsComponent smsComponent;

    //@Scheduled(cron = "0 0 12")
    public void enviarNotificacoes() {
        buscaLocalAtendimento().forEach(localAtendimento -> {
            try {
                List<LogAtendimentos> logAtendimentos = buscaLogs(localAtendimento);
                SumarizacaoDados sumarizacaoDados = gerenciadorDados.vericaLogs(localAtendimento, logAtendimentos);
                emailComponent.sendEmailHtml(sumarizacaoDados);
                smsComponent.sendSMS(sumarizacaoDados);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }
    private List<LocalAtendimento> buscaLocalAtendimento() {
        return localAtendimentoService.getAll();
    }

    private List<LogAtendimentos> buscaLogs(LocalAtendimento local) {
        return logService.buscaPeloLocalAtendimento(local);
    }
}
