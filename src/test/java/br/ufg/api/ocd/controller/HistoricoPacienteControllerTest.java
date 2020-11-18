package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.service.AtendimentoService;
import br.ufg.api.ocd.service.PacienteService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest(value = HistoricoPacienteController.class)
public class HistoricoPacienteControllerTest {

    private static final String PATH_REST = "/api/historico";

    @Autowired
    private MockMvc mvc;

    private CommonPathChecker commonPathChecker;

    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private AtendimentoService atendimentoService;

    @MockBean
    private PacienteService pacienteService;

    @Test
    public void getAtendimentoByNomeCpf_Ok() throws Exception {
        mvc.perform(
                get(this.PATH_REST+"/atendimentos/cpf/999.999.999-99")
        ).andExpect(status().isOk());
    }

    @Test
    public void getTipos_OK() throws Exception {
        mvc.perform(
                get(this.PATH_REST+"/tiposAtendimentos")
        ).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
        .andExpect(MockMvcResultMatchers.jsonPath(
                "$[*]",
                Matchers.hasItems("ACOMPANHAMENTO", "INTERVENCAO", "RESULTADOS")));
    }

    @Test
    public void getAtendimentoById_Ok() throws Exception {
        mvc.perform(
                get(this.PATH_REST+"/atendimento/1")
        ).andExpect(status().isOk());
    }



}