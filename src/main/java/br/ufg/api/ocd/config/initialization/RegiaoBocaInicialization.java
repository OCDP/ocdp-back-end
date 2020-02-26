package br.ufg.api.ocd.config.initialization;

import br.ufg.api.ocd.model.RegiaoBoca;
import br.ufg.api.ocd.model.SiglaRegiaoBoca;
import br.ufg.api.ocd.service.RegiaoBocaService;
import br.ufg.api.ocd.service.SiglaRegiaoBocaService;

import java.util.List;

public class RegiaoBocaInicialization {

    private static RegiaoBocaService regiaoBocaService;
    private static SiglaRegiaoBocaService siglaRegiaoBocaService;
    private static List<SiglaRegiaoBoca> tipoRegioes;

    public static void criaRegiaoBoca(RegiaoBocaService repository, SiglaRegiaoBocaService tRService) {
        regiaoBocaService = repository;
        siglaRegiaoBocaService = tRService;
        regiaoBocaService.deleteAll();
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Comissura labial esquerda").siglaRegiaoBoca(retornaTipoRegiao("Região A")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Comissura labial direita").siglaRegiaoBoca(retornaTipoRegiao("Região A")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Lábio inferior (lado esquerdo)").siglaRegiaoBoca(retornaTipoRegiao("Região A")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Lábio inferior (lado direito)").siglaRegiaoBoca(retornaTipoRegiao("Região A")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Lábio inferior (região de linha média)").siglaRegiaoBoca(retornaTipoRegiao("Região A")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Lábio superior (lado esquerdo").siglaRegiaoBoca(retornaTipoRegiao("Região A")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Lábio superior (lado direito)").siglaRegiaoBoca(retornaTipoRegiao("Região A")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Lábio superior (região de linha média)").siglaRegiaoBoca(retornaTipoRegiao("Região A")).build());

        salvarRegiaoBoca(RegiaoBoca.builder().nome("Mucosa labial superior (lado esquerdo)").siglaRegiaoBoca(retornaTipoRegiao("Região B")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Mucosa labial superior (lado direito)").siglaRegiaoBoca(retornaTipoRegiao("Região B")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Mucosa labial superior (região de linha média").siglaRegiaoBoca(retornaTipoRegiao("Região B")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Fundo de vestíbulo superior anterior").siglaRegiaoBoca(retornaTipoRegiao("Região B")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Freio labial superior").siglaRegiaoBoca(retornaTipoRegiao("Região B")).build());

        salvarRegiaoBoca(RegiaoBoca.builder().nome("Mucosa labial inferior (lado esquerdo)").siglaRegiaoBoca(retornaTipoRegiao("Região C")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Mucosa labial inferior (lado direito)").siglaRegiaoBoca(retornaTipoRegiao("Região C")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Mucosa labial inferior (região de linha média)").siglaRegiaoBoca(retornaTipoRegiao("Região C")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Fundo de vestíbulo inferior anterior").siglaRegiaoBoca(retornaTipoRegiao("Região C")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Freio labial inferior").siglaRegiaoBoca(retornaTipoRegiao("Região C")).build());

        salvarRegiaoBoca(RegiaoBoca.builder().nome("Mucosa jugal esquerda (posterior)").siglaRegiaoBoca(retornaTipoRegiao("Região D")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Mucosa jugal esquerda (média)").siglaRegiaoBoca(retornaTipoRegiao("Região D")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Mucosa jugal esquerda (anterior)").siglaRegiaoBoca(retornaTipoRegiao("Região D")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Mucosa jugal direita (posterior)").siglaRegiaoBoca(retornaTipoRegiao("Região D")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Mucosa jugal direita (média)").siglaRegiaoBoca(retornaTipoRegiao("Região D")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Mucosa jugal direita (anterior)").siglaRegiaoBoca(retornaTipoRegiao("Região D")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Fundo de vestíbulo inferior posterior (lado direito)").siglaRegiaoBoca(retornaTipoRegiao("Região D")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Fundo de vestíbulo inferior posterior (lado esquerdo)").siglaRegiaoBoca(retornaTipoRegiao("Região D")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Fundo de vestíbulo superior posterior (lado direito)").siglaRegiaoBoca(retornaTipoRegiao("Região D")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Fundo de vestíbulo superior (lado esquerdo)").siglaRegiaoBoca(retornaTipoRegiao("Região D")).build());

        salvarRegiaoBoca(RegiaoBoca.builder().nome("?????").siglaRegiaoBoca(retornaTipoRegiao("Região E")).build());

        salvarRegiaoBoca(RegiaoBoca.builder().nome("Palato duro").siglaRegiaoBoca(retornaTipoRegiao("Região F")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Palato mole").siglaRegiaoBoca(retornaTipoRegiao("Região F")).build());

        salvarRegiaoBoca(RegiaoBoca.builder().nome("Borda lateral direita da língua (posterior)").siglaRegiaoBoca(retornaTipoRegiao("Região G")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Borda lateral direita da língua (média)").siglaRegiaoBoca(retornaTipoRegiao("Região G")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Borda lateral direita da língua (anterior)").siglaRegiaoBoca(retornaTipoRegiao("Região G")).build());

        salvarRegiaoBoca(RegiaoBoca.builder().nome("Borda lateral esquerda da língua (posterior)").siglaRegiaoBoca(retornaTipoRegiao("Região H")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Borda lateral esquerda da língua (média)").siglaRegiaoBoca(retornaTipoRegiao("Região H")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Borda lateral esquerda da língua (anterior)").siglaRegiaoBoca(retornaTipoRegiao("Região H")).build());

        salvarRegiaoBoca(RegiaoBoca.builder().nome("Assoalho bucal (lado direito)").siglaRegiaoBoca(retornaTipoRegiao("Região I")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Assoalho bucal (lado esquerdo)").siglaRegiaoBoca(retornaTipoRegiao("Região I")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Assoalho bucal (região de linha média)").siglaRegiaoBoca(retornaTipoRegiao("Região I")).build());

        salvarRegiaoBoca(RegiaoBoca.builder().nome("Gengiva superior anterior").siglaRegiaoBoca(retornaTipoRegiao("Região J")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Gengiva superior posterior (lado esquerdo)").siglaRegiaoBoca(retornaTipoRegiao("Região J")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Gengiva superior posterior (lado direito)").siglaRegiaoBoca(retornaTipoRegiao("Região J")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Gengiva inferior anterior").siglaRegiaoBoca(retornaTipoRegiao("Região J")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Gengiva inferior posterior (lado esquerdo)").siglaRegiaoBoca(retornaTipoRegiao("Região J")).build());
        salvarRegiaoBoca(RegiaoBoca.builder().nome("Gengiva inferior posterior (lado direito)").siglaRegiaoBoca(retornaTipoRegiao("Região J")).build());

    }

    private static void salvarRegiaoBoca(RegiaoBoca regiaoBoca) {
        regiaoBocaService.salvar(regiaoBoca);
    }

    private static SiglaRegiaoBoca retornaTipoRegiao(String nome) {
        if (tipoRegioes == null) {
            tipoRegioes = siglaRegiaoBocaService.getAll();
        }
        for (SiglaRegiaoBoca item : tipoRegioes) {
            if (item.getNome().equals(nome)) return item;
        }
        return null;
    }
}
