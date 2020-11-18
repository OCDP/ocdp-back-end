package br.ufg.api.ocd.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FatorRiscoAcompanhamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne
    private FatorRisco fatorRisco;
    @ManyToOne
    private Atendimento atendimento;

    public FatorRiscoAcompanhamento(FatorRisco fatorRisco, Atendimento atendimento) {
        this.fatorRisco = fatorRisco;
        this.atendimento = atendimento;
    }


}
