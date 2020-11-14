package br.ufg.api.ocd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegioesLesoes {
    @Id
    private String id;
    @ManyToOne
    private Lesao lesao;
    @ManyToOne
    private RegiaoBoca regiaoBoca;
    @ManyToOne
    private Atendimento atendimento;
}
