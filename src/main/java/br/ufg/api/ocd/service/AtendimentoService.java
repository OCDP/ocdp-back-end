package br.ufg.api.ocd.service;

import br.ufg.api.ocd.dto.*;
import br.ufg.api.ocd.enums.TipoAtendimento;
import br.ufg.api.ocd.model.Atendimento;
import br.ufg.api.ocd.model.LocalAtendimento;
import br.ufg.api.ocd.model.Paciente;
import br.ufg.api.ocd.model.Usuario;
import br.ufg.api.ocd.repository.AtendimentoRepository;
import br.ufg.api.ocd.repository.PacienteRepository;
import br.ufg.api.ocd.strategy.BuscarAcompanhamento;
import br.ufg.api.ocd.strategy.BuscarIntervencao;
import br.ufg.api.ocd.strategy.BuscarResultados;
import br.ufg.api.ocd.strategy.EstrategiaBusca;
import br.ufg.api.ocd.util.DataUtil;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Transactional
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository repository;

    @Autowired
    private ProcedimentosResultadosService procedimentosResultadosService;

    @Autowired
    private FatorRiscoAtendimentoService fatorRiscoAtendimentoService;

    @Autowired
    private RegioesLesoesService regioesLesoesService;

    @Autowired
    private BuscarIntervencao buscarIntervencao;

    @Autowired
    private BuscarResultados buscarResultados;

    @Autowired
    private BuscarAcompanhamento buscarAcompanhamento;

    @Autowired
    private LocalAtendimentoService localAtendimentoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LogAtendimentosService logService;

    private Map<TipoAtendimento, EstrategiaBusca> acoes;

    @Autowired
    private  NextSequenceService nextSequenceService;

    @Autowired
    private PacienteRepository pacienteRepository;


    public AtendimentoBuscarDTO getAtendimentosById(@NonNull String id) {

        Atendimento atendimento = repository.findById(id).get();
        if (atendimento != null) {
            return getListaAcao().get(atendimento.getTipoAtendimento()).acao(atendimento);
        }
        return null;
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public List<HistoricoAtendimentoDTO> getHistoricoPacienteCpf(@NonNull String cpf) {
        Sort sort = new Sort(Sort.Direction.ASC, "dataAtendimento");

        Paciente paciente = pacienteRepository.findByCpf(cpf);

        if(paciente != null) {
            List<Atendimento> historicoPaciente = repository.findAllByPacienteId(paciente.getId(), sort);
            return preparaDadosHistorico(historicoPaciente);
        }
        return null;
    }

    private List<HistoricoAtendimentoDTO> preparaDadosHistorico(@NonNull List<Atendimento> historicoPaciente) {
        List<HistoricoAtendimentoDTO> listaRetorno = new ArrayList<>();
        if (historicoPaciente != null && !historicoPaciente.isEmpty()) {
            prenchelistaHistorico(listaRetorno, historicoPaciente);
            Collections.sort(listaRetorno);
        }
        return listaRetorno;
    }

    private void prenchelistaHistorico(List<HistoricoAtendimentoDTO> listaRetorno, List<Atendimento> historicoPaciente) {
        LocalDateTime dataAnterior = null;
        int cont = 0;
        for (Atendimento atendimento : historicoPaciente) {
            LocalAtendimento localAtendimento = null;
            if(atendimento.getLocalAtendimentoId() != null)
            localAtendimento = localAtendimentoService.findById(atendimento.getLocalAtendimentoId());

            Usuario usuario = usuarioService.findById(atendimento.getUsuarioId());
            if(cont == 0){
                dataAnterior = atendimento.getDataAtendimento();
            } else{
                dataAnterior = historicoPaciente.get(cont-1).getDataAtendimento();
            }
            cont = cont + 1;
            listaRetorno.add(HistoricoAtendimentoDTO.builder()
                    .dataAtendimento(DataUtil.dateToString(atendimento.getDataAtendimento()))
                    .idAtendimento(atendimento.getId())
                    .localAtendimento(localAtendimento != null? localAtendimento.getNome() : null)
                    .profissionalDeSaude(usuario != null ? usuario.getNome() : null)
                    .tipoAtendiemtento(atendimento.getTipoAtendimento().name())
                    .diferencaMeses(DataUtil.diferencaEmMeses(dataAnterior, atendimento.getDataAtendimento()) + " meses após").build());
        }
    }

    public Atendimento salvarIntervencao(IntervencaoDTO intervencaoDTO) {

        Atendimento atendimento = salvaIntervencaoDTO(intervencaoDTO);
        salvaIntervencaoProcedimentos(atendimento, intervencaoDTO.getProcedimentos());
        return atendimento;
    }

    public Atendimento salvarResultados(ResultadosDTO resultadosDTO){
        Atendimento atendimento = salvaResultadosDTO(resultadosDTO);
        salvaResultadosProcedimentos(atendimento, resultadosDTO.getProcedimentos());

        return atendimento;
    }

    public Atendimento salvarAcompanhamento(AcompanhamentoDTO acompanhamentoDTO) {

        Atendimento atendimento = salvaAcompanhamentoDTO(acompanhamentoDTO);
        fatorRiscoAtendimentoService.salvarFatorRiscoAcompanhamento(atendimento, acompanhamentoDTO.getFatoresDeRisco());
        regioesLesoesService.salvarRegioesLesoes(atendimento, acompanhamentoDTO.getRegioesLesoes());

        return atendimento;
    }

    private Atendimento salvaAcompanhamentoDTO(AcompanhamentoDTO acompanhamentoDTO) {

        return salvaAtendimento(Atendimento.builder()
                .dataAtendimento(acompanhamentoDTO.getAtendimento().getDataAtendimento())
                .pacienteId(acompanhamentoDTO.getAtendimento().getPacienteId())
                .localAtendimentoId(acompanhamentoDTO.getAtendimento().getLocalAtendimentoId())
                .localEncaminhadoId(acompanhamentoDTO.getAtendimento().getLocalEncaminhadoId())
                .tipoAtendimento(acompanhamentoDTO.getAtendimento().getTipoAtendimento())
                .usuarioId(acompanhamentoDTO.getAtendimento().getUsuarioId())
                .dataSugeridaAcompanhamento(acompanhamentoDTO.getDataSugeridaAcompanhamento())
                .dataSugeridaTratamento(acompanhamentoDTO.getDataSugeridaTratamento()).build());
    }

    private Atendimento salvaIntervencaoDTO(IntervencaoDTO intervencaoDTO) {
        return salvaAtendimento(Atendimento.builder()
                 .dataAtendimento(intervencaoDTO.getAtendimento().getDataAtendimento())
                 .pacienteId(intervencaoDTO.getAtendimento().getPacienteId())
                 .localAtendimentoId(intervencaoDTO.getAtendimento().getLocalAtendimentoId())
                 .localEncaminhadoId(intervencaoDTO.getAtendimento().getLocalEncaminhadoId())
                 .tipoAtendimento(intervencaoDTO.getAtendimento().getTipoAtendimento())
                 .usuarioId(intervencaoDTO.getAtendimento().getUsuarioId())
                 .hipoteseDiagnostico(intervencaoDTO.getHipoteseDiagnostico())
                 .confirmaRastreamento(intervencaoDTO.getConfirmaRastreamento())
                 .observacao(intervencaoDTO.getObservacao()).build());
    }

    private Atendimento salvaResultadosDTO(ResultadosDTO resultadosDTO) {
        return salvaAtendimento(Atendimento.builder()
                .dataAtendimento(resultadosDTO.getAtendimento().getDataAtendimento())
                .pacienteId(resultadosDTO.getAtendimento().getPacienteId())
                .localAtendimentoId(resultadosDTO.getAtendimento().getLocalAtendimentoId())
                .localEncaminhadoId(resultadosDTO.getAtendimento().getLocalEncaminhadoId())
                .tipoAtendimento(resultadosDTO.getAtendimento().getTipoAtendimento())
                .usuarioId(resultadosDTO.getAtendimento().getUsuarioId())
                .confirmaRastreamento(resultadosDTO.getConfirmaRastreamento())
                .diagnosticoFinal(resultadosDTO.getDiagnosticoFinal()).build());
    }

    private void salvaIntervencaoProcedimentos(Atendimento atendimento, List<ProcedimentosIntervencaoDTO> procedimentos) {
        procedimentosResultadosService.salvaIntervencaoProcedimentos(atendimento, procedimentos);
    }
    private void salvaResultadosProcedimentos(Atendimento atendimento, List<ProcedimentosResultadosDTO> procedimentos) {
        procedimentosResultadosService.salvaResultadosProcedimentos(atendimento, procedimentos);
    }

    private Map<TipoAtendimento, EstrategiaBusca> getListaAcao() {

        if (acoes == null) {
            acoes = new HashMap<>();
            acoes.put(TipoAtendimento.RESULTADOS, buscarResultados);
            acoes.put(TipoAtendimento.INTERVENCAO, buscarIntervencao);
            acoes.put(TipoAtendimento.ACOMPANHAMENTO, buscarAcompanhamento);
        }
        return acoes;
    }

    private Atendimento salvaAtendimento(Atendimento atendimento){
        atendimento.setId(nextSequenceService.getNextSequence("atendimento"));
        Atendimento save = repository.save(atendimento);
        logService.salvarLog(save);
        return save;
    }


}
