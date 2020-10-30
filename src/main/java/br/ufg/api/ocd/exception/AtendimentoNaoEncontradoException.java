package br.ufg.api.ocd.exception;

public class AtendimentoNaoEncontradoException extends RuntimeException {

    public AtendimentoNaoEncontradoException() {
        super("Atendimento não encontrado");
    }
}
