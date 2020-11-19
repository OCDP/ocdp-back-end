package br.ufg.api.ocd.model;

import br.ufg.api.ocd.enums.TipoAtendimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogAtendimentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;
    private Long idPaciente;
    private Long idUltimoAtendimento;
    private TipoAtendimento tipoAtendimento;
    private LocalDateTime dataAtendimento;
    private LocalDateTime dataSugeridaAcompanhamento;
    private LocalDateTime dataSugeridaTratamento;
    private Long idLocalAtendimento;
    private Long idLocalEncaminhado;

    public void atualiza(Atendimento atendimento) {
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
