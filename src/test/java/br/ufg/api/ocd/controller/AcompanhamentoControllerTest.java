package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.AcompanhamentoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(value = AcompanhamentoController.class)
public class AcompanhamentoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AcompanhamentoController service;

    @Test
    public void getSexo() throws Exception {
        mvc.perform(
                get("/api/acompanhamento/sexos")
        ).andExpect(status().isOk());

    }

    @Test
    public void salvar_OK() throws Exception {

        AcompanhamentoDTO dto = new AcompanhamentoDTO();

        mvc.perform(post("/api/acompanhamento/salvar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(UtilTest.toJson(dto))
        ).andExpect(status().isOk());

    }

    @Test
    public void salvar_ErroAoConverterDTO() throws Exception {

        mvc.perform(post("/api/acompanhamento/salvar")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"algumAtributo: 12}")//simula um json com erro (aspa não fechada)
        ).andExpect(status().isBadRequest());

    }

}