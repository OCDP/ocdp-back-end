package br.ufg.api.ocd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TipoLesao {
    @Id
    private String id;
   // @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String nome;
}
