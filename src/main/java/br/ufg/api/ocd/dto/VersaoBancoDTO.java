package br.ufg.api.ocd.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class VersaoBancoDTO {
    private Long id;
    private String descricao;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern="dd-MM-yyyyy HH:mm:ss")
    private LocalDateTime data;
}
