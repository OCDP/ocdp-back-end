package br.ufg.api.ocd.test.controller;

import br.ufg.api.ocd.controller.HistoricoPacienteController;
import br.ufg.api.ocd.service.AtendimentoService;
import br.ufg.api.ocd.service.PacienteService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = HistoricoPacienteController.class, secure = false)
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
    public void getPacientesByName_Ok() throws Exception {
        mvc.perform(
                get(this.PATH_REST+"/pacientes/fulano")
        ).andExpect(status().isOk());
    }

    @Test
    public void getPacientesByName_SemNome() throws Exception {
        mvc.perform(
                get(this.PATH_REST+"/pacientes")
        ).andExpect(status().isNotFound());
    }

    @Test
    public void getAtendimentoByNomePaciente_Ok() throws Exception {
        mvc.perform(
                get(this.PATH_REST+"/atendimentos/fulano")
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