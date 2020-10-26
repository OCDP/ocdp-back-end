package br.ufg.api.ocd.dto;

import lombok.Data;

import java.util.List;

@Data
public class AtendimentoBuscarDTO {
    private AtendimentoGetDTO atendimento;
    private List<FatorRiscoDTO> fatoresDeRisco;
    private List<RegioesLesoesDTO> regioesLesoes;
    private List<ProcedimentosResultadosDTO> procedimentos;
}
