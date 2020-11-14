package br.ufg.api.ocd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegioesLesoes {
    @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @ManyToOne
    private Lesao lesao;
    @ManyToOne
    private RegiaoBoca regiaoBoca;
    @ManyToOne
    private Atendimento atendimento;
}
