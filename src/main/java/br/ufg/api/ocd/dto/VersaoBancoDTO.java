package br.ufg.api.ocd.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VersaoBancoDTO {
    private String id;
    private String descricao;
    private Date data;
}
