package br.ufg.api.ocd.strategy;

import br.ufg.api.ocd.dto.*;
import br.ufg.api.ocd.model.*;
import br.ufg.api.ocd.repository.LocalAtendimentoRepository;
import br.ufg.api.ocd.repository.PacienteRepository;
import br.ufg.api.ocd.repository.ProcedimentosResultadosRepository;
import br.ufg.api.ocd.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BuscarResultados implements EstrategiaBusca {

    @Autowired
    private ProcedimentosResultadosRepository procedimentosResultadosRepository;

    @Autowired
    private br.ufg.api.ocd.repository.LocalAtendimentoRepository localAtendimentoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AtendimentoBuscarDTO acao(final Atendimento atendimento) {
        criaDTO(atendimento);
        return dto;
    }


    private void criaDTO(final Atendimento atendimento) {
        addAtendimento(atendimento);
        addProcedimentos(procedimentosResultadosRepository.findByAtendimento_Id(atendimento.getId()));
    }

    private void addAtendimento(final Atendimento atendimento) {
        if (atendimento != null) dto.setAtendimento(retornaAtendimentoGetDTO(atendimento));
    }


    private void addProcedimentos(final List<ProcedimentosResultados> procedimentos) {
        if (procedimentos != null && !procedimentos.isEmpty()) {
            List<ProcedimentosResultadosDTO> procedimentoDto = new ArrayList<>();
            procedimentos.forEach(procedimento -> {
                String arquivo = procedimento.getNomeArquivo().replace(" ", "").replace("/", "-").trim();
                procedimentoDto.add(ProcedimentosResultadosDTO.builder()
                        .id(procedimento.getId())
                        .nome(procedimento.getNome())
                        .nomeArquivo(arquivo)
                        .observacao(procedimento.getObservacao()).build());
            });
            dto.setProcedimentos(procedimentoDto);
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
                .localAtendimento(localAtendimento != null ? modelMapper.map(localAtendimento.get(), LocalAtendimentoDTO.class) : null)
                .localEncaminhado(localEncaminhado != null ? modelMapper.map(localEncaminhado.get(),LocalAtendimentoDTO.class) : null)
                .paciente(paciente != null ? modelMapper.map(paciente.get(),PacienteDTO.class) : null)
                .tipoAtendimento(atendimento.getTipoAtendimento())
                .usuario(usuario != null ? modelMapper.map(usuario.get(),UsuarioDTO.class) : null)
                .build();
    }
}
