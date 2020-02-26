package br.ufg.api.ocd.dto;

import br.ufg.api.ocd.enums.NivelAtencao;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class NivelAtencaoDTO {

    public List<NivelAtencao> getTipos() {
        return Arrays.asList(NivelAtencao.values());
    }
}
