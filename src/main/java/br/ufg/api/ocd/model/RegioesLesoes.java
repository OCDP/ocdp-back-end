package br.ufg.api.ocd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegioesLesoes {
    @Id
    private String id;
    private Lesao lesao;
    private RegiaoBoca regiaoBoca;
    private Atendimento atendimento;
}
