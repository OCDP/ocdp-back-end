package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.LogradouroDTO;
import br.ufg.api.ocd.model.Logradouro;
import br.ufg.api.ocd.service.LogradouroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = LogradouroController.class)
public class LogradouroControllerTest {

    private static final String PATH_REST = "/api/logradouros";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LogradouroService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void valida_salvar_logradouro() throws Exception {

        final var logradouro = LogradouroDTO.builder()
                .id(1L)
                .cep("74482444")
                .nome("Teste")
                .build();

        Mockito.when(service.salvar(Mockito.any())).thenReturn(Logradouro.builder().build());

        mvc.perform(post(PATH_REST)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(logradouro)))
                .andExpect(status().isOk());
    }

    @Test
    public void valida_buscar_logradouro_por_cep() throws Exception {
        Long id = 1L;
        String cep = "74483300";
        Logradouro entity = Logradouro.builder().id(id).cep(cep).build();

        Mockito.when(service.findByCep(cep)).thenReturn(entity);

        mvc.perform(get(PATH_REST).param("cep", cep))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id.intValue())))
                .andExpect(jsonPath("$.cep", is(cep)));
    }

}