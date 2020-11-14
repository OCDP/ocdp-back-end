package br.ufg.api.ocd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

import javax.persistence.Entity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalAtendimento {
    @Id
    private String id;
    private String nome;
    private Distrito distrito;
    private TipoLocalAtendimento tipoLocalAtendimento;
    private String emailResponsavel;
    private String nomeResponsavel;
}
