package br.ufg.api.ocd.enums;

public enum Sexo {
    MASCULINO("Masculino"), FEMININO("Feminino");

    Sexo(String tipo) {
        this.tipo = tipo;
    }

    private String tipo;
}
