package br.ufg.api.ocd.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cidade {
    @Id
    private String id;
    private String nome;
    private List<Bairro> bairros;
}

