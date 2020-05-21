package br.ufg.api.ocd.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Comunicado {
    @Id
    private String id;
    private Usuario usuario;
    private String titulo;
    private String descricao;
    private LocalDateTime dataEnviar;
    private String origem;
    private byte[] arquivo;
    private String texto;
}
