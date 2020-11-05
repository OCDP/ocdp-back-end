package br.ufg.api.ocd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "endereco")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Logradouro {
    @Id
    private String id;
    private String cep;
    private String nome;
    private Bairro bairro;
}