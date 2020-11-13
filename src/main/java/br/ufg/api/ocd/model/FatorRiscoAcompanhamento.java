package br.ufg.api.ocd.model;

import lombok.Data;
import org.springframework.data.annotation.Id;


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
