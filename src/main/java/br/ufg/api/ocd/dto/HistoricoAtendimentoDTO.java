package br.ufg.api.ocd.dto;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date dateComparer, dateActual;

        try {
            dateActual = sdf.parse(this.getDataAtendimento());
            dateComparer = sdf.parse(o.getDataAtendimento());

            if (dateActual.after(dateComparer)) {
                return -1;
            }
            if (dateActual.before(dateComparer)) {
                return 1;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
