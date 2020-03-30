package br.ufg.api.ocd.model;

import br.ufg.api.ocd.enums.TipoAtendimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Document(collection = "atendimento")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Atendimento {

    @Transient
    public static final String SEQUENCE_NAME = "atendimento_sequence";

    @Id
    private String id;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date dataAtendimento;
    private Usuario usuario;
    private Paciente paciente;
    private TipoAtendimento tipoAtendimento;
    private LocalAtendimento localAtendimento;
    private LocalAtendimento localEncaminhado;
    private Date dataSugeridaAcompanhamento;
    private Date dataSugeridaTratamento;
    private String hipoteseDiagnostico;
    private Boolean confirmaRastreamento;
    private String observacao;
    private String diagnosticoFinal;
}
