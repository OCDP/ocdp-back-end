package br.ufg.api.ocd.model;

import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

public class Chat {
    @Id
    private String id;
    private LocalAtendimento localAtendimento;
    private Usuario usuario;
    private LocalDateTime data;
}
