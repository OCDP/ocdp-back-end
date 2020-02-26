package br.ufg.api.ocd.strategy;

import br.ufg.api.ocd.dto.AtendimentoBuscarDTO;
import br.ufg.api.ocd.model.Atendimento;

public interface EstrategiaBusca {

    AtendimentoBuscarDTO dto = new AtendimentoBuscarDTO();

    public AtendimentoBuscarDTO acao(final Atendimento aendimento);
}
