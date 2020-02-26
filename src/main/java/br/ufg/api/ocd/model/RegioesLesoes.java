package br.ufg.api.ocd.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "localDaLesao")
@Data
public class RegioesLesoes {
    @Id
    private String id;
    private List<Lesao> lesoes;
    private List<RegiaoBoca> regioes;
    private Atendimento atendimento;

    public RegioesLesoes(List<Lesao> lesoes, List<RegiaoBoca> regioes, Atendimento atendimento) {
        this.lesoes = lesoes;
        this.regioes = regioes;
        this.atendimento = atendimento;
    }
}
