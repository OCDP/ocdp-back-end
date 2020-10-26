package br.ufg.api.ocd.model;

import br.ufg.api.ocd.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "paciente")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    private String id;
    private String nome;
    private String enderecoCompleto;
    private String cpf;
    private LocalDateTime dataNascimento;
    private Sexo sexo;
    private String email;
    private String telefoneCelular;
    private String nomeDaMae;
    private String telefoneResponsavel;
    private Bairro bairro;
    private LogAtendimentos log;

}



