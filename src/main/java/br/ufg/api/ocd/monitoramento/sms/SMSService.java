package br.ufg.api.ocd.monitoramento.sms;

import br.ufg.api.ocd.model.LocalAtendimento;
import br.ufg.api.ocd.model.LogAtendimentos;
import br.ufg.api.ocd.model.Paciente;
import br.ufg.api.ocd.model.Usuario;
import br.ufg.api.ocd.repository.LocalAtendimentoRepository;
import br.ufg.api.ocd.repository.PacienteRepository;
import br.ufg.api.ocd.repository.UsuarioRepository;
import br.ufg.api.ocd.util.DataUtil;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SMSService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LocalAtendimentoRepository locAtendimentoRepository;

    public static final String ACCOUNT_SID = "AC07c1ce95f903b447b25ae4f6e1747e74";
    public static final String AUTH_TOKEN = "your_auth_token";

    public void sendSMSAtendimentos(List<LogAtendimentos> logs) {

        logs.forEach(logAtendimentos -> {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            Paciente paciente = getPacienteByLog(logAtendimentos);
            LocalAtendimento localAtendimento = getLocalAtendimentoByLog(logAtendimentos);

            StringBuffer sb = new StringBuffer();
            sb.append("Olá ").append(paciente!= null ? paciente.getNome(): null)
                    .append("\\n")
                    .append("você tem um retorno agendado para o dia ")
                    .append(DataUtil.dateToString(logAtendimentos.getDataAtendimento()))
                    .append(" - ").append(localAtendimento !=null ? localAtendimento.getNome() : null)
                    .append(". Não deixe de comparecer!")
                    .append("\\n")
                    .append("Atenciosamente, ")
                    .append("\\n")
                    .append("Equipe de saúde bucal");

            Message message = Message.creator(
                    new PhoneNumber(paciente!= null ? paciente.getTelefoneCelular() : null),  // To number
                    new PhoneNumber("+15559994321"),  // From number
                    sb.toString()
            ).create();
        });
    }

    public void sendSMSAtendimentosNaoCompareceu(List<LogAtendimentos> logs) {

        logs.forEach(logAtendimentos -> {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            StringBuffer sb = new StringBuffer();

            Paciente paciente = getPacienteByLog(logAtendimentos);
            LocalAtendimento localAtendimento = getLocalAtendimentoByLog(logAtendimentos);

            sb.append("Olá ").append(paciente.getNome())
                    .append("\\n")
                    .append(" você não compareceu ao seu último retorno agendado em: ")
                    .append(DataUtil.dateToString(logAtendimentos.getDataAtendimento()))
                    .append(" - ").append(localAtendimento != null ? localAtendimento.getNome() : null)
                    .append(".  Procure  o local do atendimento e agende o seu próximo atendimento com a equipe de saúde bucal.")
                    .append("\\n")
                    .append("Atenciosamente, ")
                    .append("\\n")
                    .append("Equipe de saúde bucal");

            Message message = Message.creator(
                    new PhoneNumber(paciente.getTelefoneCelular()),  // To number
                    new PhoneNumber("+15559994321"),  // From number
                    sb.toString()
            ).create();
        });
    }

    public void sendSMSAtendimentosSemAgendamento(List<LogAtendimentos> logs) {

        logs.forEach(logAtendimentos -> {
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

            StringBuffer sb = new StringBuffer();

            Paciente paciente = getPacienteByLog(logAtendimentos);
            LocalAtendimento localAtendimento = getLocalAtendimentoByLog(logAtendimentos);
            Usuario usuario = getUsuarioByLog(logAtendimentos);

            sb.append("Olá ").append(paciente.getNome())
                    .append("\\n")
                    .append("você estava sendo atendido pelo(a) profissional: ")
                    .append(usuario != null ? usuario.getNome() : null)
                    .append(" no(a) ").append(localAtendimento != null ? localAtendimento.getNome() : null)
                    .append(".  e o seu último atendimento tem mais de 6 meses.")
                    .append("Procure o ").append(localAtendimento.getNome()).append(" e agende o seu próximo atendimento com a equipe de saúde bucal.")
                    .append("\\n")
                    .append("\\n")
                    .append("Atenciosamente, ")
                    .append("\\n")
                    .append("Equipe de saúde bucal");

            Message message = Message.creator(
                    new PhoneNumber(paciente.getTelefoneCelular()),  // To number
                    new PhoneNumber("+15559994321"),  // From number
                    sb.toString()
            ).create();
        });
    }

    private Paciente getPacienteByLog(LogAtendimentos log){
        Optional<Paciente> optional = pacienteRepository.findById(log.getIdPaciente());
        return optional != null? optional.get() : null;
    }

    private Usuario getUsuarioByLog(LogAtendimentos log){
        Optional<Usuario> optional = usuarioRepository.findById(log.getIdUsuario());
        return optional != null? optional.get() : null;
    }

    private LocalAtendimento getLocalAtendimentoByLog(LogAtendimentos log){
        Optional<LocalAtendimento> optional = locAtendimentoRepository.findById(log.getIdUsuario());
        return optional != null? optional.get() : null;
    }
}
