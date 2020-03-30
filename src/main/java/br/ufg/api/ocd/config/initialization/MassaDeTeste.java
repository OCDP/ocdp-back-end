package br.ufg.api.ocd.config.initialization;

import br.ufg.api.ocd.dto.*;
import br.ufg.api.ocd.enums.Sexo;
import br.ufg.api.ocd.enums.TipoAtendimento;
import br.ufg.api.ocd.model.FatorRisco;
import br.ufg.api.ocd.model.Lesao;
import br.ufg.api.ocd.model.Paciente;
import br.ufg.api.ocd.model.RegiaoBoca;
import br.ufg.api.ocd.repository.*;
import br.ufg.api.ocd.service.AtendimentoService;
import org.modelmapper.ModelMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MassaDeTeste {

    public static void criaAtendimentos(AtendimentoService service,
                                        ModelMapper modelMapper,
                                        UsuarioRepository usuarioRepository,
                                        BairroRepository bairroRepository,
                                        LocalAtendimentoRepository localAtendimentoRepository,
                                        FatorRiscoRepository fatorRiscoRepository,
                                        RegiaoBocaRepository regiaoBocaRepository,
                                        LesaoRepository lesaoRepository) {

        final LocalAtendimentoDTO localAtendimento1 = modelMapper.map(localAtendimentoRepository.findById("1").get(), LocalAtendimentoDTO.class);
        final LocalAtendimentoDTO localAtendimento2 = modelMapper.map(localAtendimentoRepository.findById("2").get(), LocalAtendimentoDTO.class);
        final LocalAtendimentoDTO localAtendimento3 = modelMapper.map(localAtendimentoRepository.findById("3").get(), LocalAtendimentoDTO.class);

        AtendimentoDTO atendimentoDTOAC = criaAtentedimentoDTO(modelMapper, bairroRepository, usuarioRepository, localAtendimento1, localAtendimento2, TipoAtendimento.ACOMPANHAMENTO);
        AtendimentoDTO atendimentoDTOIT = criaAtentedimentoDTO(modelMapper, bairroRepository, usuarioRepository, localAtendimento2, localAtendimento3, TipoAtendimento.INTERVENCAO);
        AtendimentoDTO atendimentoDTORT = criaAtentedimentoDTO(modelMapper, bairroRepository, usuarioRepository, localAtendimento2, localAtendimento3, TipoAtendimento.RESULTADOS);

        criaAtendimentoAcompanhamento(service, atendimentoDTOAC, modelMapper, fatorRiscoRepository, regiaoBocaRepository, lesaoRepository);
        criaAtendimentoIntervencao(service, atendimentoDTOIT);
        criaAtendimentoResultados(service, atendimentoDTORT);
    }

    private static void criaAtendimentoAcompanhamento(AtendimentoService service,
                                                     AtendimentoDTO atendimentoDTO,
                                                     ModelMapper modelMapper,
                                                     FatorRiscoRepository fatorRiscoRepository,
                                                     RegiaoBocaRepository regiaoBocaRepository,
                                                     LesaoRepository lesaoRepository) {

        AcompanhamentoDTO dto = new AcompanhamentoDTO();
        dto.setAtendimento(atendimentoDTO);
        dto.setFatoresDeRisco(criaListFatorRiscoDTO(fatorRiscoRepository, modelMapper));
        dto.setRegioesLesoes(criaListRegioesLesoesDTO(regiaoBocaRepository, lesaoRepository, modelMapper));
        dto.setDataSugeridaAcompanhamento(new Date());
        dto.setDataSugeridaTratamento(new Date());
        service.salvarAcompanhamento(dto);
    }

    private static void criaAtendimentoIntervencao(AtendimentoService service, AtendimentoDTO atendimentoDTO) {
        IntervencaoDTO dto = new IntervencaoDTO();
        dto.setAtendimento(atendimentoDTO);
        dto.setHipoteseDiagnostico("Hipotese teste");
        dto.setObservacao("Observação Teste");
        dto.setProcedimentos(criaListProcedimentosIntervencao());
        dto.setConfirmaRastreamento(true);
        service.salvarIntervencao(dto);
    }

    private static void criaAtendimentoResultados(AtendimentoService service, AtendimentoDTO atendimentoDTO) {
        ResultadosDTO dto = new ResultadosDTO();
        dto.setAtendimento(atendimentoDTO);
        dto.setConfirmaRastreamento(true);
        dto.setDiagnosticoFinal("TESTESS FINAL");
        dto.setProcedimentos(criaListProcedimentosResultados());

        service.salvarResultados(dto);
    }

    private static AtendimentoDTO criaAtentedimentoDTO(
            ModelMapper modelMapper,
            BairroRepository bairroRepository,
            UsuarioRepository usuarioRepository,
            LocalAtendimentoDTO localAtendimento,
            LocalAtendimentoDTO localEncaminhamento,
            TipoAtendimento tipoAtendimento) {

        AtendimentoDTO dto = new AtendimentoDTO();
        dto.setDataAtendimento(new Date());
        dto.setLocalAtendimento(localAtendimento);
        dto.setLocalEncaminhado(localEncaminhamento);
        dto.setPaciente(criaPaciente(bairroRepository, modelMapper));
        dto.setUsuario(modelMapper.map(usuarioRepository.findById("1").get(), UsuarioDTO.class));
        dto.setTipoAtendimento(tipoAtendimento);

        return dto;
    }

    private static List<FatorRiscoDTO> criaListFatorRiscoDTO(FatorRiscoRepository fatorRiscoRepository, ModelMapper modelMapper) {
        List<FatorRiscoDTO> fatores = new ArrayList<>();

        final FatorRisco fatorRisco = fatorRiscoRepository.findById("1").get();
        final FatorRisco fatorRisco1 = fatorRiscoRepository.findById("2").get();

        fatores.add(modelMapper.map(fatorRisco, FatorRiscoDTO.class));
        fatores.add(modelMapper.map(fatorRisco1, FatorRiscoDTO.class));

        return fatores;
    }

    private static List<RegioesLesoesDTO> criaListRegioesLesoesDTO(RegiaoBocaRepository regiaoBocaRepository, LesaoRepository lesaoRepository, ModelMapper modelMapper) {
        List<RegioesLesoesDTO> regioesLesoes = new ArrayList<>();

        RegioesLesoesDTO rl1 = new RegioesLesoesDTO();
        rl1.setRegioes(criaListRegiaoBocaDTO(regiaoBocaRepository, modelMapper));
        rl1.setLesoes(criaListLesaoDTO(lesaoRepository, modelMapper));

        regioesLesoes.add(rl1);

        return regioesLesoes;
    }

    private static List<RegiaoBocaDTO> criaListRegiaoBocaDTO(RegiaoBocaRepository regiaoBocaRepository, ModelMapper modelMapper) {
        List<RegiaoBocaDTO> regioesBoca = new ArrayList<>();

        final RegiaoBoca regiaoBoca = regiaoBocaRepository.findById("1").get();
        final RegiaoBoca regiaoBoca2 = regiaoBocaRepository.findById("1").get();

        regioesBoca.add(modelMapper.map(regiaoBoca, RegiaoBocaDTO.class));
        regioesBoca.add(modelMapper.map(regiaoBoca2, RegiaoBocaDTO.class));

        return regioesBoca;
    }

    private static List<LesaoDTO> criaListLesaoDTO(LesaoRepository lesaoRepository, ModelMapper modelMapper) {
        List<LesaoDTO> lesoes = new ArrayList<>();

        final Lesao lesao = lesaoRepository.findById("1").get();
        final Lesao lesao2 = lesaoRepository.findById("2").get();

        lesoes.add(modelMapper.map(lesao, LesaoDTO.class));
        lesoes.add(modelMapper.map(lesao2, LesaoDTO.class));

        return lesoes;
    }

    private static PacienteDTO criaPaciente(BairroRepository bairroRepository, ModelMapper modelMapper) {
        Paciente p = new Paciente();

        p.setNome("João");
        p.setDataNascimento(new Date());
        p.setSexo(Sexo.MASCULINO);
        p.setEmail("teste@teste.com");
        p.setTelefoneCelular("62 9999-9999");
        p.setNomeDaMae("Maria");
        p.setTelefoneResponsavel("62 9999-9999");
        p.setCpf("999.999.999-99");
        p.setEnderecoCompleto("Teste teste testetes");
        p.setBairro(bairroRepository.findById("1").get());

        return modelMapper.map(p, PacienteDTO.class);
    }

    private static List<ProcedimentosIntervencaoDTO> criaListProcedimentosIntervencao(){
        List<ProcedimentosIntervencaoDTO> procedimentos = new ArrayList<>();

        ProcedimentosIntervencaoDTO p = new ProcedimentosIntervencaoDTO();
        p.setNome("Biopsia Incisional");
        p.setObservacao("XXXXX");

        ProcedimentosIntervencaoDTO p1 = new ProcedimentosIntervencaoDTO();
        p1.setNome("Biopsia Exisional");
        p1.setObservacao("XXXXX");

        ProcedimentosIntervencaoDTO p2 = new ProcedimentosIntervencaoDTO();
        p2.setNome("Citologia");
        p2.setObservacao("XXXXX");

        procedimentos.add(p);
        procedimentos.add(p2);
        procedimentos.add(p2);

        return procedimentos;

    }

    private static List<ProcedimentosResultadosDTO> criaListProcedimentosResultados(){
        List<ProcedimentosResultadosDTO> procedimentos = new ArrayList<>();

        ProcedimentosResultadosDTO p = new ProcedimentosResultadosDTO();
        p.setNome("Biopsia Incisional");
        p.setObservacao("XXXXX");
        p.setAnexo(new File("/resources/file/Incisional.jpg"));

        ProcedimentosResultadosDTO p1 = new ProcedimentosResultadosDTO();
        p1.setNome("Biopsia Exisional");
        p1.setObservacao("XXXXX");
        p.setAnexo(new File("/resources/file/Incisional.jpg"));

        ProcedimentosResultadosDTO p2 = new ProcedimentosResultadosDTO();
        p2.setNome("Citologia");
        p2.setObservacao("XXXXX");
        p.setAnexo(new File("/resources/file/Incisional.jpg"));

        procedimentos.add(p);
        procedimentos.add(p2);
        procedimentos.add(p2);

        return procedimentos;

    }
}
