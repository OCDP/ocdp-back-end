package br.ufg.api.ocd.model;

import br.ufg.api.ocd.enums.TipoAtendimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Atendimento {
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
