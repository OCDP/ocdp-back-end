package br.ufg.api.ocd.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fatorRiscoAvaliacaoClinica")
@Data
public class FatorRiscoAcompanhamento {
    @Id
    private String id;
    private FatorRisco fatorRisco;
    private Atendimento atendimento;

    public FatorRiscoAcompanhamento(FatorRisco fatorRisco, Atendimento atendimento) {
        this.fatorRisco = fatorRisco;
        this.atendimento = atendimento;
    }




}
