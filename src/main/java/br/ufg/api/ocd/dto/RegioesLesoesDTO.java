package br.ufg.api.ocd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegioesLesoesDTO {
    private LesaoDTO lesao;
    private RegiaoBocaDTO regiaoBoca;
}
