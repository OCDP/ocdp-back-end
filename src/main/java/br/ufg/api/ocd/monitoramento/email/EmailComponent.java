package br.ufg.api.ocd.monitoramento.email;

import br.ufg.api.ocd.enums.TipoAtendimento;
import br.ufg.api.ocd.model.LogAtendimentos;
import br.ufg.api.ocd.model.Paciente;
import br.ufg.api.ocd.model.Usuario;
import br.ufg.api.ocd.monitoramento.scheduled.SumarizacaoDados;
import br.ufg.api.ocd.repository.PacienteRepository;
import br.ufg.api.ocd.repository.UsuarioRepository;
import br.ufg.api.ocd.service.EmailService;
import br.ufg.api.ocd.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.*;

@Component
public class EmailComponent {

    @Autowired
    private EmailService emailService;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void sendEmailHtml(SumarizacaoDados sumarizacaoDados) throws MessagingException {
        Mail mail = criaMail(sumarizacaoDados);
        emailService.sendSimpleMessage(mail);
    }

    private Mail criaMail(SumarizacaoDados sumarizacaoDados) {
        Mail mail = new Mail();

        mail.setPara(sumarizacaoDados.getLocalAtendimento().getEmailResponsavel());
        mail.setDe("ocdpsobrevida@gmail.com");
        mail.setAssunto("Informações sobre atendimentos - OCDP");
        mail.setModel(addModel(sumarizacaoDados));
        return mail;
    }

    private Map addModel(SumarizacaoDados sumarizacaoDados) {
        Map model = new HashMap();

        model.put("nomeResponsavel", sumarizacaoDados.getLocalAtendimento().getNomeResponsavel());
        model.put("localAtendimento", sumarizacaoDados.getLocalAtendimento().getNome());
        if (sumarizacaoDados.getLogAtendimento() != null && !sumarizacaoDados.getLogAtendimento().isEmpty()) {
            model.put("dadosPacientesAtendimento", converteDadosPacientes(sumarizacaoDados.getLogAtendimento()));
        }
        if (sumarizacaoDados.getLogNaoCompareceu() != null && !sumarizacaoDados.getLogNaoCompareceu().isEmpty()) {
            model.put("dadosPacientesAtendimentoNaoCompareceu", converteDadosPacientes(sumarizacaoDados.getLogNaoCompareceu()));
        }
        if (sumarizacaoDados.getLogNaoCompareceu() != null && !sumarizacaoDados.getLogNaoCompareceu().isEmpty()) {
            model.put("dadosPacientesSemAgendamento", converteDadosPacientes(sumarizacaoDados.getLogSemAgendamento()));
        }
        return model;
    }

    private DadosPacientes[] converteDadosPacientes(List<LogAtendimentos> logs) {
        Set<DadosPacientes> listDadosPacientes = new HashSet<>();
        logs.forEach(log -> {
            Paciente paciente = pacienteRepository.findById(log.getIdPaciente()).get();
            DadosPacientes dados = new DadosPacientes();
            dados.dataAtendimento= DataUtil.dateToString(log.getDataAtendimento());
            dados.nomePaciente = paciente.getNome();
            dados.endereco = paciente.getEnderecoCompleto();
            dados.profissionalAtendeu = getUsuarioByLog(log).getNome();
            dados.dataUltimoAtendimento = DataUtil.dateToString(log.getDataAtendimento());
            if (log.getDataSugeridaAcompanhamento() != null) {
                dados.dataAtendimento = DataUtil.retornaApenasDataString(log.getDataSugeridaAcompanhamento());
                dados.horaAtendimento = DataUtil.retornaApenasHoraString(log.getDataSugeridaAcompanhamento());
                dados.tipoDeAtendimento = TipoAtendimento.ACOMPANHAMENTO.name();
            }
            if (log.getDataSugeridaTratamento() != null) {
                dados.dataAtendimento = DataUtil.retornaApenasDataString(log.getDataSugeridaTratamento());
                dados.horaAtendimento = DataUtil.retornaApenasHoraString(log.getDataSugeridaTratamento());
                dados.tipoDeAtendimento = TipoAtendimento.INTERVENCAO.name();
            }
            listDadosPacientes.add(dados);
        });

        return listDadosPacientes.toArray(new DadosPacientes[listDadosPacientes.size()]);
    }

    private Usuario getUsuarioByLog(LogAtendimentos log){
        Optional<Usuario> optional = usuarioRepository.findById(log.getIdUsuario());
        return optional != null? optional.get() : null;
    }

}
