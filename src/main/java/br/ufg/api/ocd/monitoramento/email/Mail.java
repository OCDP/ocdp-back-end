package br.ufg.api.ocd.monitoramento.email;

import lombok.Data;

import java.util.Map;

@Data
public class Mail {

    private String de;
    private String para;
    private String assunto;
    private Map model;

    public Mail() {
    }

    public Mail(String de, String para, String assunto) {
        this.de = de;
        this.para = para;
        this.assunto = assunto;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "de='" + de + '\'' +
                ", para='" + para + '\'' +
                ", assunto='" + assunto + '\'' +
                '}';
    }
}
