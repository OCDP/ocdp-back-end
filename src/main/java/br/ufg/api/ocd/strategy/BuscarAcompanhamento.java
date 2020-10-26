package br.ufg.api.ocd.strategy;

import br.ufg.api.ocd.dto.*;
import br.ufg.api.ocd.model.*;
import br.ufg.api.ocd.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BuscarAcompanhamento implements EstrategiaBusca {

    @Autowired
    private RegioesLesoesRepository regioesLesoesRepository;

    @Autowired
    private br.ufg.api.ocd.repository.LocalAtendimentoRepository localAtendimentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private FatorRiscoAtendimentoRepository fatorRiscoAtendimentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AtendimentoBuscarDTO acao(final Atendimento atendimento) {
        criaDTO(atendimento);
        return dto;
    }

    private void criaDTO(final Atendimento atendimento) {
        addAtendimento(atendimento);
        addRegioesLesoes(buscaRegioesLesoes(atendimento));
        addFatorRisco(buscaFatoresRisco(atendimento));
    }

    private void addAtendimento(final Atendimento atendimento) {
        if (atendimento != null) dto.setAtendimento(retornaAtendimentoGetDTO(atendimento));
    }

    private List<RegioesLesoes> buscaRegioesLesoes(final Atendimento atendimento){
        return regioesLesoesRepository.findByAtendimento_Id(atendimento.getId());

    }

    private List<FatorRiscoAcompanhamento> buscaFatoresRisco(final Atendimento atendimento){
        return fatorRiscoAtendimentoRepository.findAllByAtendimento_Id(atendimento.getId());

    }

    private void addRegioesLesoes(final List<RegioesLesoes> regioesLesoes) {
        if (regioesLesoes != null && !regioesLesoes.isEmpty()) {
            List<RegioesLesoesDTO> regioesLesoesLista = new ArrayList<>();
            regioesLesoes.forEach(reLe -> {
                RegioesLesoesDTO regioesLesoesDTO = new RegioesLesoesDTO();
                regioesLesoesDTO.setLesao(modelMapper.map(reLe.getLesao(), LesaoDTO.class));
                regioesLesoesDTO.setRegiaoBoca(modelMapper.map(reLe.getRegiaoBoca(), RegiaoBocaDTO.class));
                regioesLesoesLista.add(regioesLesoesDTO);
            });
            dto.setRegioesLesoes(regioesLesoesLista);
        }
    }

    private void addFatorRisco(final List<FatorRiscoAcompanhamento> fatorRiscoAcompanhamentos) {
        if (fatorRiscoAcompanhamentos != null && !fatorRiscoAcompanhamentos.isEmpty()) {
            List<FatorRiscoDTO> fatoresDto = new ArrayList<>();
            fatorRiscoAcompanhamentos.forEach(fator -> {
                fatoresDto.add(modelMapper.map(fator.getFatorRisco(), FatorRiscoDTO.class));
            });
            dto.setFatoresDeRisco(fatoresDto);
        }
    }

    private AtendimentoGetDTO retornaAtendimentoGetDTO(Atendimento atendimento){
        Optional<LocalAtendimento> localAtendimento = atendimento.getLocalAtendimentoId()!= null? localAtendimentoRepository.findById(atendimento.getLocalAtendimentoId()):null;
        Optional<LocalAtendimento> localEncaminhado = atendimento.getLocalEncaminhadoId() != null? localAtendimentoRepository.findById(atendimento.getLocalEncaminhadoId()):null;
        Optional<Paciente> paciente = pacienteRepository.findById(atendimento.getPacienteId());
        Optional<Usuario> usuario = usuarioRepository.findById(atendimento.getUsuarioId());

        return AtendimentoGetDTO.builder()
                .dataAtendimento(atendimento.getDataAtendimento())
                .id(atendimento.getId())
                .localAtendimento(localAtendimento != null ? modelMapper.map(localAtendimento.get(),LocalAtendimentoDTO.class) : null)
                .localEncaminhado(localEncaminhado != null ? modelMapper.map(localEncaminhado.get(),LocalAtendimentoDTO.class) : null)
                .paciente(paciente != null ? modelMapper.map(paciente.get(),PacienteDTO.class) : null)
                .tipoAtendimento(atendimento.getTipoAtendimento())
                .usuario(usuario != null ? modelMapper.map(usuario.get(),UsuarioDTO.class) : null)
                .build();
    }

}
