package br.ufg.api.ocd.enums;

public enum  TipoAtendimento {

    ACOMPANHAMENTO("Acompanhamento"), INTERVENCAO("Intervenção"), RESULTADOS("Resultados");

    TipoAtendimento(String nome) {
        this.nome = nome;
    }
    private String nome;
}

