package br.ufg.api.ocd.model;

import br.ufg.api.ocd.enums.Sexo;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "paciente")
public class Paciente {
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;
    @DynamoDBAttribute
    private String nome;
    @DynamoDBAttribute
    private String enderecoCompleto;
    @DynamoDBAttribute
    private String cpf;
    @DynamoDBAttribute
    private LocalDateTime dataNascimento;
    @DynamoDBAttribute
    private Sexo sexo;
    @DynamoDBAttribute
    private String email;
    @DynamoDBAttribute
    private String telefoneCelular;
    @DynamoDBAttribute
    private String nomeDaMae;
    @DynamoDBAttribute
    private String telefoneResponsavel;
    @DynamoDBAttribute
    private Bairro bairro;
    @DynamoDBAttribute
    private LogAtendimentos log;

}



