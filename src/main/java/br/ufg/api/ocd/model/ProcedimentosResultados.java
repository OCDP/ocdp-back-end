package br.ufg.api.ocd.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.File;


@Document(collection = "procedimentosResultados")
@Data
public class ProcedimentosResultados {
    @Id
    private String id;
    private File anexo;
    private String nome;
    private String observacao;
    private Atendimento atendimento;

}
