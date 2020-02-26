package br.ufg.api.ocd.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tipoLesao")
@Data
@Builder(toBuilder = true)
public class TipoLesao {
    @Id
    private String id;
   // @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String nome;
}
