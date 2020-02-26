package br.ufg.api.ocd.enums;

public enum StatusUsuario {
    ATIVO("ativo"), INATIVO("inativo");

    StatusUsuario(String tipo) {
        this.tipo = tipo;
    }

    private String tipo;
}
