package br.ufg.api.ocd.enums;

public enum TipoUsuario {

    ENFERMEIRO("enfermeiro"), MEDICO("MÉdico"), DENTISTA("Dentista"), ASSISTENTE("assistente");

    TipoUsuario(String nome) {
        this.nome = nome;
    }
    private String nome;
}

