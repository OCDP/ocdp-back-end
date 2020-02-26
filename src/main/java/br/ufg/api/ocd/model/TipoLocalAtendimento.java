package br.ufg.api.ocd.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tipoLocalAtendimento")
@Data
@Builder(toBuilder = true)
public class TipoLocalAtendimento {
    @Id
    private String id;
    private String nome;
}
