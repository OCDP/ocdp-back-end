package br.ufg.api.ocd.model;

import br.ufg.api.ocd.enums.TipoAtendimento;
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
@DynamoDBTable(tableName = "atendimento")
public class Atendimento {

    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;
    @DynamoDBAttribute
    private LocalDateTime dataAtendimento;
    @DynamoDBAttribute
    private Usuario usuario;
    @DynamoDBAttribute
    private Paciente paciente;
    @DynamoDBAttribute
    private TipoAtendimento tipoAtendimento;
    @DynamoDBAttribute
    private LocalAtendimento localAtendimento;
    @DynamoDBAttribute
    private LocalAtendimento localEncaminhado;
    @DynamoDBAttribute
    private LocalDateTime dataSugeridaAcompanhamento;
    @DynamoDBAttribute
    private LocalDateTime dataSugeridaTratamento;
    @DynamoDBAttribute
    private String hipoteseDiagnostico;
    @DynamoDBAttribute
    private Boolean confirmaRastreamento;
    @DynamoDBAttribute
    private String observacao;
    @DynamoDBAttribute
    private String diagnosticoFinal;
}
