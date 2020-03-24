package br.ufg.api.ocd.model;

import br.ufg.api.ocd.enums.TipoAtendimento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "logAtendimentos")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogAtendimentos {
    @Id
    private String id;
    private String idPaciente;
    private String idUltimoAtendimento;
    private TipoAtendimento tipoAtendimento;
    private Date dataAtendimento;
    private Date dataSugeridaAcompanhamento;
    private Date dataSugeridaTratamento;
    private String idLocalAtendimentoEncaminhado;

    public void atualiza(Atendimento atendimento){
        this.setDataAtendimento(atendimento.getDataAtendimento());
        this.setDataSugeridaAcompanhamento(atendimento.getDataSugeridaAcompanhamento());
        this.setDataSugeridaTratamento(atendimento.getDataSugeridaTratamento());
        this.setIdLocalAtendimentoEncaminhado(atendimento.getLocalAtendimento().getId());
        this.setIdPaciente(atendimento.getPaciente().getId());
        this.setIdUltimoAtendimento(atendimento.getId());
        this.setTipoAtendimento(atendimento.getTipoAtendimento());
    }
}
