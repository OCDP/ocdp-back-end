package br.ufg.api.ocd.service;

import br.ufg.api.ocd.OCDApplicationTests;
import br.ufg.api.ocd.exception.LogradouroNaoEncontradoException;
import br.ufg.api.ocd.model.Logradouro;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class LogradouroServiceTest extends OCDApplicationTests {

    @Autowired
    private LogradouroService logradouroService;

    @Test
    public void validar_salvar() {
        Logradouro logradouro = Logradouro.builder()
                .cep("74482330")
                .build();

        Logradouro actual = logradouroService.salvar(logradouro);

        assertNotNull(actual);
        assertNotNull(actual.getId());
    }

    @Test
    public void validar_pesquisa_por_cep() throws Exception {
        String cep = "74482331";
        Logradouro logradouro = Logradouro.builder()
                .cep(cep)
                .build();

        logradouroService.salvar(logradouro);

        Logradouro actual = logradouroService.findByCep(cep);

        assertNotNull(actual);
        assertEquals(actual.getCep(), cep);
    }

    @Test(expected = LogradouroNaoEncontradoException.class)
    public void validar_pesquisa_por_cep_inexistente() throws Exception {
        String cep = "74482332";
        logradouroService.findByCep(cep);
    }
}