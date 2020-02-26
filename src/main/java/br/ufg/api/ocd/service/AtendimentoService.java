package br.ufg.api.ocd.service;

import br.ufg.api.ocd.dto.*;
import br.ufg.api.ocd.enums.TipoAtendimento;
import br.ufg.api.ocd.model.Atendimento;
import br.ufg.api.ocd.model.LocalAtendimento;
import br.ufg.api.ocd.model.Paciente;
import br.ufg.api.ocd.model.Usuario;
import br.ufg.api.ocd.repository.AtendimentoRepository;
import br.ufg.api.ocd.strategy.BuscarAcompanhamento;
import br.ufg.api.ocd.strategy.BuscarIntervencao;
import br.ufg.api.ocd.strategy.EstrategiaBusca;
import br.ufg.api.ocd.util.DataUtil;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private BuscarAcompanhamento buscarAcompanhamento;

    @Autowired
    private LogAtendimentosService logService;

    @Autowired
    private ModelMapper modelMapper;

    private Map<TipoAtendimento, EstrategiaBusca> acoes;

    @Autowired
    private  NextSequenceService nextSequenceService;


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

    public List<Atendimento> getByNomePaciente(@NonNull String nome) {
        return repository.findAllByPaciente_NomeOrderByDataAtendimento(nome);
    }

    public List<HistoricoAtendimentoDTO> getHistoricoPaciente(@NonNull String nome) {
        return preparaDadosHistorico(nome);
    }

    private List<HistoricoAtendimentoDTO> preparaDadosHistorico(@NonNull String nome) {
        List<HistoricoAtendimentoDTO> listaRetorno = new ArrayList<>();
        final List<Atendimento> historicoPaciente = repository.findAllByPaciente_NomeOrderByDataAtendimento(nome);
        if (historicoPaciente != null && !historicoPaciente.isEmpty())
            prenchelistaHistorico(listaRetorno, historicoPaciente);
        return listaRetorno;
    }

    private void prenchelistaHistorico(List<HistoricoAtendimentoDTO> listaRetorno, List<Atendimento> historicoPaciente) {
        Date dataAnterior;
        for (Atendimento atendimento : historicoPaciente) {
            dataAnterior = atendimento.getDataAtendimento();
            listaRetorno.add(HistoricoAtendimentoDTO.builder()
                    .dataAtendimento(DataUtil.dateToString(atendimento.getDataAtendimento()))
                    .idAtendimento(atendimento.getId())
                    .localAtendimento(atendimento.getLocalAtendimento().getNome())
                    .profissionalDeSaude(atendimento.getUsuario().getNome())
                    .tipoAtendiemtento(atendimento.getTipoAtendimento().name())
                    .diferencaDias(DataUtil.diferencaEmDias(atendimento.getDataAtendimento(), dataAnterior) + " dias após").build());
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
        return salvaAtendimento( Atendimento.builder()
                .dataAtendimento(new Date())
                .paciente(modelMapper.map(acompanhamentoDTO.getAtendimento().getPaciente(), Paciente.class))
                .localAtendimento(modelMapper.map(acompanhamentoDTO.getAtendimento().getLocalAtendimento(), LocalAtendimento.class))
                .tipoAtendimento(acompanhamentoDTO.getAtendimento().getTipoAtendimento())
                .usuario(modelMapper.map(acompanhamentoDTO.getAtendimento().getUsuario(), Usuario.class))
                .dataSugeridaAcompanhamento(acompanhamentoDTO.getDataSugeridaAcompanhamento())
                .dataSugeridaTratamento(acompanhamentoDTO.getDataSugeridaTratamento()).build());
    }

    private Atendimento salvaIntervencaoDTO(IntervencaoDTO intervencaoDTO) {
        return salvaAtendimento( Atendimento.builder()
                 .dataAtendimento(new Date())
                 .paciente(modelMapper.map(intervencaoDTO.getAtendimento().getPaciente(), Paciente.class))
                 .localAtendimento(modelMapper.map(intervencaoDTO.getAtendimento().getLocalAtendimento(), LocalAtendimento.class))
                 .tipoAtendimento(intervencaoDTO.getAtendimento().getTipoAtendimento())
                 .usuario(modelMapper.map(intervencaoDTO.getAtendimento().getUsuario(), Usuario.class))
                 .hipoteseDiagnostico(intervencaoDTO.getHipoteseDiagnostico())
                 .observacao(intervencaoDTO.getObservacao()).build());
    }

    private Atendimento salvaResultadosDTO(ResultadosDTO resultadosDTO) {
        return salvaAtendimento( Atendimento.builder()
                .dataAtendimento(new Date())
                .paciente(modelMapper.map(resultadosDTO.getAtendimento().getPaciente(), Paciente.class))
                .localAtendimento(modelMapper.map(resultadosDTO.getAtendimento().getLocalAtendimento(), LocalAtendimento.class))
                .tipoAtendimento(resultadosDTO.getAtendimento().getTipoAtendimento())
                .usuario(modelMapper.map(resultadosDTO.getAtendimento().getUsuario(), Usuario.class))
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
            acoes.put(TipoAtendimento.RESULTADOS, buscarIntervencao);
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
