package br.ufg.api.ocd.model;

import org.springframework.data.annotation.Id;

public class ItemChat {
    @Id
    private String id;
    private Chat chat;
    private Usuario usuario;
    private Paciente paciente;
    private LocalAtendimento dataEnvio;
    private String origem;
    private String destino;
    private String mensagem;
    private boolean lida;
}
