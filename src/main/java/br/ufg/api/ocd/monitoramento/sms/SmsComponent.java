package br.ufg.api.ocd.monitoramento.sms;

import br.ufg.api.ocd.monitoramento.scheduled.SumarizacaoDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Component
public class SmsComponent {

    @Autowired
    private SMSService smsService;

    public void sendSMS(SumarizacaoDados sumarizacaoDados) throws MessagingException {
        smsService.sendSMSAtendimentos(sumarizacaoDados.getLogAtendimento());
        smsService.sendSMSAtendimentosNaoCompareceu(sumarizacaoDados.getLogNaoCompareceu());
        smsService.sendSMSAtendimentosSemAgendamento(sumarizacaoDados.getLogSemAgendamento());
    }
}
