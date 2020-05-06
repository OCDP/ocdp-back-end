package br.ufg.api.ocd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fatorRisco")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FatorRisco {
    @Id
    private String id;
    private String nome;
}
