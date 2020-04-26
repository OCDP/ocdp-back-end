package br.ufg.api.ocd.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "fatorRiscoAvaliacaoClinica")
public class FatorRiscoAcompanhamento {
    @DynamoDBHashKey
    @DynamoDBAutoGeneratedKey
    private String id;
    @DynamoDBAttribute
    private FatorRisco fatorRisco;
    @DynamoDBAttribute
    private Atendimento atendimento;

    public FatorRiscoAcompanhamento(FatorRisco fatorRisco, Atendimento atendimento) {
        this.fatorRisco = fatorRisco;
        this.atendimento = atendimento;
    }




}
