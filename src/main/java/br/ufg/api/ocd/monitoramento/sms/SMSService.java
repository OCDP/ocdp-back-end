package br.ufg.api.ocd.monitoramento.sms;

import br.ufg.api.ocd.model.Atendimento;
import br.ufg.api.ocd.model.LogAtendimentos;
import br.ufg.api.ocd.repository.AtendimentoRepository;
import br.ufg.api.ocd.util.DataUtil;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SMSService {


    @Autowired
    private AtendimentoRepository atendimentoRepository;

    public static final String ACCOUNT_SID = "AC07c1ce95f903b447b25ae4f6e1747e74";
    public static final String AUTH_TOKEN = "your_auth_token";

    public void sendSMSAtendimentos(List<LogAtendimentos> logs) {

        logs.forEach(logAtendimentos -> {
            Atendimento atendimento = atendimentoRepository.findById(logAtendimentos.getIdUltimoAtendimento()).get();
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            StringBuffer sb = new StringBuffer();
            sb.append("Olá ").append(atendimento.getPaciente().getNome())
                    .append("\\n")
                    .append("você tem um retorno agendado para o dia ")
                    .append(DataUtil.dateToString(logAtendimentos.getDataAtendimento()))
                    .append(" - ").append(atendimento.getLocalAtendimento().getNome())
                    .append(". Não deixe de comparecer!")
                    .append("\\n")
                    .append("Atenciosamente, ")
                    .append("\\n")
                    .append("Equipe de saúde bucal");

            Message message = Message.creator(
                    new PhoneNumber(atendimento.getPaciente().getTelefoneCelular()),  // To number
                    new PhoneNumber("+15559994321"),  // From number
                    sb.toString()
            ).create();
        });
    }

    public void sendSMSAtendimentosNaoCompareceu(List<LogAtendimentos> logs) {

        logs.forEach(logAtendimentos -> {
            Atendimento atendimento = atendimentoRepository.findById(logAtendimentos.getIdUltimoAtendimento()).get();
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            StringBuffer sb = new StringBuffer();

            sb.append("Olá ").append(atendimento.getPaciente().getNome())
                    .append("\\n")
                    .append(" você não compareceu ao seu último retorno agendado em: ")
                    .append(DataUtil.dateToString(logAtendimentos.getDataAtendimento()))
                    .append(" - ").append(atendimento.getLocalAtendimento().getNome())
                    .append(".  Procure  o local do atendimento e agende o seu próximo atendimento com a equipe de saúde bucal.")
                    .append("\\n")
                    .append("Atenciosamente, ")
                    .append("\\n")
                    .append("Equipe de saúde bucal");

            Message message = Message.creator(
                    new PhoneNumber(atendimento.getPaciente().getTelefoneCelular()),  // To number
                    new PhoneNumber("+15559994321"),  // From number
                    sb.toString()
            ).create();
        });
    }

    public void sendSMSAtendimentosSemAgendamento(List<LogAtendimentos> logs) {

        logs.forEach(logAtendimentos -> {
            Atendimento atendimento = atendimentoRepository.findById(logAtendimentos.getIdUltimoAtendimento()).get();
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            StringBuffer sb = new StringBuffer();

            sb.append("Olá ").append(atendimento.getPaciente().getNome())
                    .append("\\n")
                    .append("você estava sendo atendido pelo(a) profissional: ")
                    .append(atendimento.getUsuario().getNome())
                    .append(" no(a) ").append(atendimento.getLocalAtendimento().getNome())
                    .append(".  e o seu último atendimento tem mais de 6 meses.")
                    .append("Procure o ").append(atendimento.getLocalAtendimento().getNome()).append(" e agende o seu próximo atendimento com a equipe de saúde bucal.")
                    .append("\\n")
                    .append("\\n")
                    .append("Atenciosamente, ")
                    .append("\\n")
                    .append("Equipe de saúde bucal");

            Message message = Message.creator(
                    new PhoneNumber(atendimento.getPaciente().getTelefoneCelular()),  // To number
                    new PhoneNumber("+15559994321"),  // From number
                    sb.toString()
            ).create();
        });
    }

}
