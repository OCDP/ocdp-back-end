package br.ufg.api.ocd.config.initialization;

import br.ufg.api.ocd.dto.*;
import br.ufg.api.ocd.enums.Sexo;
import br.ufg.api.ocd.enums.TipoAtendimento;
import br.ufg.api.ocd.model.*;
import br.ufg.api.ocd.repository.*;
import br.ufg.api.ocd.service.AtendimentoService;
import br.ufg.api.ocd.service.PacienteService;
import org.modelmapper.ModelMapper;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MassaDeTeste {
    private static AtendimentoService service;
    private static ModelMapper modelMapper;
    private static UsuarioRepository usuarioRepository;
    private static BairroRepository bairroRepository;
    private static LocalAtendimentoRepository localAtendimentoRepository;
    private static FatorRiscoRepository fatorRiscoRepository;
    private static RegiaoBocaRepository regiaoBocaRepository;
    private static LesaoRepository lesaoRepository;
    private static PacienteService pacienteService;

    private static LocalAtendimentoDTO localAtendimento1;
    private static LocalAtendimentoDTO localAtendimento2;
    private static LocalAtendimentoDTO localAtendimento3;

    private static Usuario usuario1;
    private static Usuario usuario2;
    private static Usuario usuario3;

    private static PacienteDTO joao;
    private static PacienteDTO joao2;
    private static PacienteDTO batista;
    private static PacienteDTO mane;
    private static PacienteDTO jose;

    public static void criaAtendimentos(AtendimentoService service,
                                        ModelMapper modelMapper,
                                        UsuarioRepository usuarioRepository,
                                        BairroRepository bairroRepository,
                                        LocalAtendimentoRepository localAtendimentoRepository,
                                        FatorRiscoRepository fatorRiscoRepository,
                                        RegiaoBocaRepository regiaoBocaRepository,
                                        LesaoRepository lesaoRepository,
                                        PacienteService pacienteService) {

        init(service,modelMapper,usuarioRepository, bairroRepository,localAtendimentoRepository, fatorRiscoRepository,regiaoBocaRepository, lesaoRepository, pacienteService);
        criaAtendiemtnoJoao();
        criaAtendiemtnoJoao2();
        criaAtendiemtnoBatista();
        criaAtendiemtnoMane();
        criaAtendiemtnoJose();
    }

    private static void init(AtendimentoService service2,
                             ModelMapper modelMapper2,
                             UsuarioRepository usuarioRepository2,
                             BairroRepository bairroRepository2,
                             LocalAtendimentoRepository localAtendimentoRepository2,
                             FatorRiscoRepository fatorRiscoRepository2,
                             RegiaoBocaRepository regiaoBocaRepository2,
                             LesaoRepository lesaoRepository2,
                             PacienteService pacienteService2) {

        service = service2;
        modelMapper = modelMapper2;
        usuarioRepository = usuarioRepository2;
        bairroRepository = bairroRepository2;
        localAtendimentoRepository = localAtendimentoRepository2;
        fatorRiscoRepository = fatorRiscoRepository2;
        regiaoBocaRepository = regiaoBocaRepository2;
        lesaoRepository = lesaoRepository2;
        pacienteService = pacienteService2;
        criaLocais();
        criaUsuarios();
        criaPacientes();
    }

    private static void criaLocais(){
        localAtendimento1 = modelMapper.map(localAtendimentoRepository.findById("1").get(), LocalAtendimentoDTO.class);
        localAtendimento2 = modelMapper.map(localAtendimentoRepository.findById("2").get(), LocalAtendimentoDTO.class);
        localAtendimento3 = modelMapper.map(localAtendimentoRepository.findById("3").get(), LocalAtendimentoDTO.class);
    }

    private static void criaUsuarios(){
        usuario1 = usuarioRepository.findById("1").get();
        usuario2 = usuarioRepository.findById("2").get();
        usuario3 = usuarioRepository.findById("3").get();
    }

    private static void criaPacientes(){
        joao = criaPaciente("João", "999.999.999-99");
        joao2 = criaPaciente("João", "888.888.888-88");
        batista = criaPaciente("Batista", "777.777.777-77");
        mane = criaPaciente("Mane", "666.666.666-66");
        jose = criaPaciente("Jose", "555.555.555-55");
    }

    private static void criaAtendiemtnoJoao(){
        AtendimentoDTO atendimentoDTOAC = criaAtentedimentoDTO(joao, localAtendimento1, localAtendimento2, TipoAtendimento.ACOMPANHAMENTO, montaData(2019, Calendar.MARCH, 7), usuario2);
        AtendimentoDTO atendimentoDTOIT = criaAtentedimentoDTO(joao, localAtendimento2, localAtendimento3, TipoAtendimento.INTERVENCAO, montaData(2019, Calendar.AUGUST, 7), usuario3);
        AtendimentoDTO atendimentoDTORT = criaAtentedimentoDTO(joao, localAtendimento2, localAtendimento3, TipoAtendimento.RESULTADOS, montaData(2020, Calendar.JANUARY, 7), usuario3);

        criaAtendimentoAcompanhamento(atendimentoDTOAC);
        criaAtendimentoIntervencao(atendimentoDTOIT);
        criaAtendimentoResultados(atendimentoDTORT);
    }

    private static void criaAtendiemtnoJoao2(){
        AtendimentoDTO atendimentoDTOAC = criaAtentedimentoDTO(joao2, localAtendimento3, localAtendimento2, TipoAtendimento.ACOMPANHAMENTO, montaData(2019, Calendar.MARCH, 7), usuario2);
        AtendimentoDTO atendimentoDTOIT = criaAtentedimentoDTO(joao2, localAtendimento2, localAtendimento3, TipoAtendimento.INTERVENCAO, montaData(2019, Calendar.AUGUST, 7), usuario3);
        AtendimentoDTO atendimentoDTORT = criaAtentedimentoDTO(joao2, localAtendimento2, localAtendimento3, TipoAtendimento.RESULTADOS, montaData(2020, Calendar.JANUARY, 7), usuario3);

        criaAtendimentoAcompanhamento(atendimentoDTOAC);
        criaAtendimentoIntervencao(atendimentoDTOIT);
        criaAtendimentoResultados(atendimentoDTORT);
    }

    private static void criaAtendiemtnoBatista(){
        AtendimentoDTO atendimentoDTOAC = criaAtentedimentoDTO(batista, localAtendimento2, localAtendimento2, TipoAtendimento.ACOMPANHAMENTO, montaData(2019, Calendar.MARCH, 7), usuario2);
        AtendimentoDTO atendimentoDTOIT = criaAtentedimentoDTO(batista, localAtendimento3, localAtendimento3, TipoAtendimento.INTERVENCAO, montaData(2019, Calendar.AUGUST, 7), usuario3);
        AtendimentoDTO atendimentoDTORT = criaAtentedimentoDTO(batista, localAtendimento3, localAtendimento3, TipoAtendimento.RESULTADOS, montaData(2020, Calendar.JANUARY, 7), usuario3);

        criaAtendimentoAcompanhamento(atendimentoDTOAC);
        criaAtendimentoIntervencao(atendimentoDTOIT);
        criaAtendimentoResultados(atendimentoDTORT);
    }

    private static void criaAtendiemtnoMane(){
        AtendimentoDTO atendimentoDTOAC = criaAtentedimentoDTO(mane, localAtendimento1, localAtendimento2, TipoAtendimento.ACOMPANHAMENTO, montaData(2019, Calendar.MARCH, 7), usuario2);
        AtendimentoDTO atendimentoDTOIT = criaAtentedimentoDTO(mane, localAtendimento3, localAtendimento3, TipoAtendimento.INTERVENCAO, montaData(2019, Calendar.AUGUST, 7), usuario3);
        AtendimentoDTO atendimentoDTORT = criaAtentedimentoDTO(mane, localAtendimento3, localAtendimento3, TipoAtendimento.RESULTADOS, montaData(2020, Calendar.JANUARY, 7), usuario3);

        criaAtendimentoAcompanhamento(atendimentoDTOAC);
        criaAtendimentoIntervencao(atendimentoDTOIT);
        criaAtendimentoResultados(atendimentoDTORT);
    }

    private static void criaAtendiemtnoJose(){
        AtendimentoDTO atendimentoDTOAC = criaAtentedimentoDTO(jose,localAtendimento1, localAtendimento2, TipoAtendimento.ACOMPANHAMENTO, montaData(2015, Calendar.JANUARY, 7), usuario2);
        AtendimentoDTO atendimentoDTOIT = criaAtentedimentoDTO(jose,localAtendimento2, localAtendimento3, TipoAtendimento.INTERVENCAO, montaData(2015, Calendar.APRIL, 7), usuario3);
        AtendimentoDTO atendimentoDTORT = criaAtentedimentoDTO(jose,localAtendimento2, localAtendimento3, TipoAtendimento.RESULTADOS, montaData(2016, Calendar.JANUARY, 7), usuario3);
        AtendimentoDTO atendimentoDTOIT2 = criaAtentedimentoDTO(jose,localAtendimento2, localAtendimento3, TipoAtendimento.INTERVENCAO, montaData(2016, Calendar.OCTOBER, 7), usuario3);
        AtendimentoDTO atendimentoDTORT2 = criaAtentedimentoDTO(jose,localAtendimento2, localAtendimento3, TipoAtendimento.RESULTADOS, montaData(2017, Calendar.JANUARY, 7), usuario3);
        AtendimentoDTO atendimentoDTOIT3 = criaAtentedimentoDTO(jose, localAtendimento2, localAtendimento3, TipoAtendimento.INTERVENCAO, montaData(2017, Calendar.NOVEMBER, 7), usuario3);
        AtendimentoDTO atendimentoDTORT3 = criaAtentedimentoDTO(jose,localAtendimento2, localAtendimento3, TipoAtendimento.RESULTADOS, montaData(2018, Calendar.FEBRUARY, 7), usuario3);
        AtendimentoDTO atendimentoDTOAC2 = criaAtentedimentoDTO(jose,localAtendimento1, localAtendimento2, TipoAtendimento.ACOMPANHAMENTO, montaData(2019, Calendar.AUGUST, 7), usuario2);
        AtendimentoDTO atendimentoDTOAC3 = criaAtentedimentoDTO(jose,localAtendimento1, localAtendimento2, TipoAtendimento.ACOMPANHAMENTO, montaData(2020, Calendar.JANUARY, 7), usuario2);

        criaAtendimentoAcompanhamento(atendimentoDTOAC);
        criaAtendimentoIntervencao(atendimentoDTOIT);
        criaAtendimentoResultados(atendimentoDTORT);
        criaAtendimentoIntervencao(atendimentoDTOIT2);
        criaAtendimentoResultados(atendimentoDTORT2);
        criaAtendimentoIntervencao(atendimentoDTOIT3);
        criaAtendimentoResultados(atendimentoDTORT3);
        criaAtendimentoAcompanhamento(atendimentoDTOAC2);
        criaAtendimentoAcompanhamento(atendimentoDTOAC3);
    }

    private static void criaAtendimentoAcompanhamento(AtendimentoDTO atendimentoDTO) {
        AcompanhamentoDTO dto = AcompanhamentoDTO.builder().
        atendimento(atendimentoDTO).
        fatoresDeRisco(criaListFatorRiscoDTO()).
        regioesLesoes(criaListRegioesLesoesDTO()).
        dataSugeridaAcompanhamento(new Date()).
        dataSugeridaTratamento(new Date()).build();

        service.salvarAcompanhamento(dto);
    }

    private static void criaAtendimentoIntervencao(AtendimentoDTO atendimentoDTO) {
        IntervencaoDTO dto = IntervencaoDTO.builder().
        atendimento(atendimentoDTO).
        hipoteseDiagnostico("Hipotese teste").
        observacao("Observação Teste").
        procedimentos(criaListProcedimentosIntervencao()).
        confirmaRastreamento(true).build();

        service.salvarIntervencao(dto);
    }

    private static void criaAtendimentoResultados(AtendimentoDTO atendimentoDTO) {
        ResultadosDTO dto = ResultadosDTO.builder().
        atendimento(atendimentoDTO).
        confirmaRastreamento(true).
        diagnosticoFinal("TESTESS FINAL").
        procedimentos(criaListProcedimentosResultados()).build();

        service.salvarResultados(dto);
    }

    private static AtendimentoDTO criaAtentedimentoDTO(
            PacienteDTO pacienteDTO,
            LocalAtendimentoDTO localAtendimento,
            LocalAtendimentoDTO localEncaminhamento,
            TipoAtendimento tipoAtendimento,
            Date dataAtendimento,
            Usuario usuario) {

        AtendimentoDTO dto = AtendimentoDTO.builder().
        dataAtendimento(dataAtendimento).
        localAtendimento(localAtendimento).
        localEncaminhado(localEncaminhamento).
        paciente(pacienteDTO).
        usuario(modelMapper.map(usuario, UsuarioDTO.class)).
        tipoAtendimento(tipoAtendimento).build();

        return dto;
    }

    private static List<FatorRiscoDTO> criaListFatorRiscoDTO() {
        List<FatorRiscoDTO> fatores = new ArrayList<>();

        final FatorRisco fatorRisco = fatorRiscoRepository.findById("1").get();
        final FatorRisco fatorRisco1 = fatorRiscoRepository.findById("2").get();

        fatores.add(modelMapper.map(fatorRisco, FatorRiscoDTO.class));
        fatores.add(modelMapper.map(fatorRisco1, FatorRiscoDTO.class));

        return fatores;
    }

    private static List<RegioesLesoesDTO> criaListRegioesLesoesDTO() {
        List<RegioesLesoesDTO> lista = new ArrayList<>();
        lista.add(RegioesLesoesDTO.builder().lesao(criaListLesaoDTO("1")).regiaoBoca(criaListRegiaoBocaDTO("1")).build());
        lista.add(RegioesLesoesDTO.builder().lesao(criaListLesaoDTO("2")).regiaoBoca(criaListRegiaoBocaDTO("2")).build());
        return lista;
    }

    private static RegiaoBocaDTO criaListRegiaoBocaDTO(String id) {
        final RegiaoBoca regiaoBoca = regiaoBocaRepository.findById(id).get();
        return modelMapper.map(regiaoBoca, RegiaoBocaDTO.class);
    }

    private static LesaoDTO criaListLesaoDTO(String id) {
        final Lesao lesao = lesaoRepository.findById(id).get();
        return modelMapper.map(lesao, LesaoDTO.class);
    }

    private static PacienteDTO criaPaciente(String nome, String cpf) {
        Paciente p = new Paciente();

        p.setNome(nome);
        p.setDataNascimento(montaData(1975,3,2));
        p.setSexo(Sexo.MASCULINO);
        p.setEmail("teste@teste.com");
        p.setTelefoneCelular("62 9999-9999");
        p.setNomeDaMae("Maria");
        p.setTelefoneResponsavel("62 9999-9999");
        p.setCpf(cpf);
        p.setEnderecoCompleto("Teste teste testetes");
        p.setBairro(bairroRepository.findById("1").get());
        Paciente salvo = pacienteService.salvar(p);

        return modelMapper.map(salvo, PacienteDTO.class);
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

    private static Date montaData(int ano, int mes, int dia){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date d = null;
        try{
            d = (Date) dateFormat.parse(dia+"/"+mes+"/"+ano);
        }catch(ParseException e){
            e.printStackTrace();
        }

        return d;
    }

    private static Date addSeisMeses(Date data){
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.add(Calendar.MONTH, 6);
        return cal.getTime();
    }
}
