package br.ufg.api.ocd.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegioesLesoesDTO {
    private List<LesaoDTO> lesoes;
    private List<RegiaoBocaDTO> regioes;
}
