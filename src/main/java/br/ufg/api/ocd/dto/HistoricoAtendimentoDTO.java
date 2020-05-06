package br.ufg.api.ocd.dto;

import br.ufg.api.ocd.util.DataUtil;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Data
@Builder
public class HistoricoAtendimentoDTO implements Comparable<HistoricoAtendimentoDTO> {
    private String idAtendimento;
    private String tipoAtendiemtento;
    private String diferencaMeses;
    private String localAtendimento;
    private String profissionalDeSaude;
    private String dataAtendimento;

    @Override
    public int compareTo(HistoricoAtendimentoDTO o) {
        LocalDateTime dateComparer, dateActual;
            dateActual = DataUtil.stringToDate(this.getDataAtendimento());
            dateComparer = DataUtil.stringToDate(this.getDataAtendimento());
            if (dateActual.isAfter(dateComparer)) {
                return -1;
            }
            if (dateActual.isBefore(dateComparer)) {
                return 1;
            }

        return 0;
    }
}
