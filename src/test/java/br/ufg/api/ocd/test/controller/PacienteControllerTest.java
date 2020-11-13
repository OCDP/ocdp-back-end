package br.ufg.api.ocd.test.controller;

import br.ufg.api.ocd.controller.PacienteController;
import br.ufg.api.ocd.service.AtendimentoService;
import br.ufg.api.ocd.service.PacienteService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = PacienteController.class)
public class PacienteControllerTest {

    private static final String PATH_REST = "/api/paciente";

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
                get(this.PATH_REST+"/byName/jose")
        ).andExpect(status().isOk());
    }

    @Test
    public void getPacientesByName_SemNome() throws Exception {
        mvc.perform(
                get(this.PATH_REST+"/byName")
        ).andExpect(status().isNotFound());
    }
}