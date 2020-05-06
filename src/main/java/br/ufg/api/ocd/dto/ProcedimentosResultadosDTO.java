package br.ufg.api.ocd.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcedimentosResultadosDTO {
    private String id;
    @NotNull(message = "Forneça os procedimentos da intervenção")
    private String anexo64;
    @NotEmpty(message = "Forneça o nome do procedimento")
    private String nome;
    private String observacao;
}
