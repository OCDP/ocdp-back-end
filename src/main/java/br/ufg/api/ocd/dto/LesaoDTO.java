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
public class LesaoDTO {
    private Long id;
    @NotEmpty(message = "Forneça o nome da lesao")
    private String nome;
    private TipoLesaoDTO tipoLesao;
}
