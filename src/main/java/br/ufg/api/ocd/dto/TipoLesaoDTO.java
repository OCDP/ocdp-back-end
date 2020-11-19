package br.ufg.api.ocd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoLesaoDTO {

    private Long id;
    @NotEmpty(message = "Forneça o nome do tipo lesão")
    private String nome;
}
