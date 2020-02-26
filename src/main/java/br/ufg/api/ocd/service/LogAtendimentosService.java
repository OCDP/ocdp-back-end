package br.ufg.api.ocd.service;

import br.ufg.api.ocd.model.Atendimento;
import br.ufg.api.ocd.model.LocalAtendimento;
import br.ufg.api.ocd.model.LogAtendimentos;
import br.ufg.api.ocd.model.Paciente;
import br.ufg.api.ocd.repository.LogAtendimentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LogAtendimentosService {

    @Autowired
    private LogAtendimentosRepository repository;

    @Autowired
    private NextSequenceService nextSequenceService;

    public void salvarLog(Atendimento atendimento) {
        LogAtendimentos log = repository.findByIdPaciente(atendimento.getPaciente().getId());
        if (log == null) log = preencheLog(atendimento);
        else log.atualiza(atendimento);
        log.setId(nextSequenceService.getNextSequence("log"));
        repository.save(log);
    }

    public LogAtendimentos buscaPeloPaciente(Paciente paciente) {
       return repository.findByIdPaciente(paciente.getId());
    }

    public List<LogAtendimentos> buscaPeloLocalAtendimento(LocalAtendimento localAtendimento) {
        return repository.findAllByIdLocalAtendimentoEncaminhado(localAtendimento.getId());
    }

    private LogAtendimentos preencheLog(Atendimento atendimento) {
        return LogAtendimentos.builder()
                .dataAtendimento(atendimento.getDataAtendimento())
                .dataSugeridaAcompanhamento(atendimento.getDataSugeridaAcompanhamento())
                .dataSugeridaTratamento(atendimento.getDataSugeridaTratamento())
                .idLocalAtendimentoEncaminhado(atendimento.getLocalAtendimento().getId())
                .idPaciente(atendimento.getPaciente().getId())
                .idUltimoAtendimento(atendimento.getId())
                .tipoAtendimento(atendimento.getTipoAtendimento())
                .build();
    }

    public void deleteAll(){
        repository.deleteAll();
    }
}
