package br.ufg.api.ocd.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.File;

@Data
public class ProcedimentosResultadosDTO {

    @NotNull(message = "Forneça os procedimentos da intervenção")
    private File anexo;
    @NotEmpty(message = "Forneça o nome do procedimento")
    private String nome;

    private String observacao;
}
