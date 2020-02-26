package br.ufg.api.ocd.enums;

public enum NivelAtencao {

    PRIMARIA("Primária"), SECUNDARIA("Secundária"), TERCIARIA("Terciária");

    NivelAtencao(String nome) {
        this.nome = nome;
    }

    private String nome;
}
