package br.ufg.api.ocd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "localAtendimento")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LocalAtendimento {
    @Id
    private String id;
    private String nome;
    private Distrito distrito;
    private TipoLocalAtendimento tipoLocalAtendimento;
    private String emailResponsavel;
    private String nomeResponsavel;
}
