package br.ufg.api.ocd.model;

import br.ufg.api.ocd.enums.Sexo;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "paciente")
@Data
public class Paciente {
    @Id
    private String id;
    private String nome;
    private String enderecoCompleto;
    private String cpf;
    private Date dataNascimento;
    private Sexo sexo;
    private String email;
    private String telefoneCelular;
    private String nomeDaMae;
    private String telefoneResponsavel;
    private Bairro bairro;
    private LogAtendimentos log;

}



