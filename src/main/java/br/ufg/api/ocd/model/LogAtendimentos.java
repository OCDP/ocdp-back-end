package br.ufg.api.ocd.model;

import br.ufg.api.ocd.enums.TipoAtendimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogAtendimentos {
    @Id
    private String id;
    private String idUsuario;
    private String idPaciente;
    private String idUltimoAtendimento;
    private TipoAtendimento tipoAtendimento;
    private LocalDateTime dataAtendimento;
    private LocalDateTime dataSugeridaAcompanhamento;
    private LocalDateTime dataSugeridaTratamento;
    private String idLocalAtendimento;
    private String idLocalEncaminhado;

    public void atualiza(Atendimento atendimento){
        this.setDataAtendimento(atendimento.getDataAtendimento());
        this.setDataSugeridaAcompanhamento(atendimento.getDataSugeridaAcompanhamento());
        this.setDataSugeridaTratamento(atendimento.getDataSugeridaTratamento());
        this.setIdLocalAtendimento(atendimento.getLocalAtendimentoId());
        this.setIdLocalEncaminhado(atendimento.getLocalEncaminhadoId());
        this.setIdUsuario(atendimento.getUsuarioId());
        this.setIdPaciente(atendimento.getPacienteId());
        this.setIdUltimoAtendimento(atendimento.getId());
        this.setTipoAtendimento(atendimento.getTipoAtendimento());
    }
}
