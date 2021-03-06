package br.ufg.api.ocd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tipoLocalAtendimento")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoLocalAtendimento {
    @Id
    private String id;
    private String nome;
}
