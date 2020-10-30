package br.ufg.api.ocd.model;

import br.ufg.api.ocd.enums.TipoAtendimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

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
    private LocalDateTime dataAtendimento;
    private String usuarioId;
    private String pacienteId;
    private TipoAtendimento tipoAtendimento;
    private String localAtendimentoId;
    private String localEncaminhadoId;
    private LocalDateTime dataSugeridaAcompanhamento;
    private LocalDateTime dataSugeridaTratamento;
    private String hipoteseDiagnostico;
    private Boolean confirmaRastreamento;
    private String observacao;
    private String diagnosticoFinal;
    private byte[] foto;
}
