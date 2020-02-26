package br.ufg.api.ocd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "cidade")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cidade {
    @Id
    private String id;
   // @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String nome;

    //@JsonSerialize(using= DBRefSerializer.class)
    @DBRef(lazy = false)
    private List<Bairro> bairros;
}

