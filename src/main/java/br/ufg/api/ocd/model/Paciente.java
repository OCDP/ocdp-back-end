package br.ufg.api.ocd.model;

import br.ufg.api.ocd.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "paciente")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    private String id;
    private String nomeRegistro;
    private String nomeSocial;
    private String enderecoCompleto;
    private String cpf;
    private LocalDateTime dataNascimento;
    private Sexo sexo;
    private String cns;
    private String rg;
    private String escolaridade;
    private String orgaoEmissor;
    private String nacionalidade;
    private String racaCor;
    private LocalDateTime dataEmissao;
    private String email;
    private String telefoneCelular;
    private String nomeDaMae;
    private String telefoneResponsavel;
    private Bairro bairro;
    private LogAtendimentos log;
}



