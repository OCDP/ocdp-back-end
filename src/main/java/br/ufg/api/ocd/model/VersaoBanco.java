package br.ufg.api.ocd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "versaoBanco")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VersaoBanco {
    @Id
    private String id;
    private String descricao;
    private LocalDateTime data;
}
