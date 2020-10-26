package br.ufg.api.ocd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "procedimentosResultados")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcedimentosResultados {
    @Id
    private String id;
    private String nomeArquivo;
    private String nome;
    private String observacao;
    private Atendimento atendimento;

}
