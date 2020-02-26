package br.ufg.api.ocd.dto;

import br.ufg.api.ocd.enums.TipoAtendimento;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class TipoAtendimentoDTO {

    public List<TipoAtendimento> getTipos() {
        return Arrays.asList(TipoAtendimento.values());
    }
}
