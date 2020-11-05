package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.OCDApplicationTests;
import br.ufg.api.ocd.dto.LocalAtendimentoDTO;
import br.ufg.api.ocd.dto.LogradouroDTO;
import br.ufg.api.ocd.model.Logradouro;
import br.ufg.api.ocd.repository.LogradouroRepository;
import br.ufg.api.ocd.service.BairroService;
import br.ufg.api.ocd.service.LogradouroService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;


import java.util.HashMap;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LogradouroController.class, secure = false)
public class LogradouroControllerTest {

    private static final String PATH_REST = "/api/logradouros";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LogradouroService service;

    @Test
    public void valida_salvar_logradouro() throws Exception {
        Mockito.when(service.salvar(Mockito.any())).thenReturn(Logradouro.builder().build());

        mvc.perform(post(PATH_REST).content(new ObjectMapper().writeValueAsString(LogradouroDTO.builder().build())))
                .andExpect(status().isOk());
    }

    @Test
    public void valida_buscar_logradouro_por_cep() throws Exception {
        String id = "1";
        String cep = "74483300";
        Logradouro entity = Logradouro.builder().id(id).cep(cep).build();

        Mockito.when(service.findByCep(cep)).thenReturn(entity);

        mvc.perform(get(PATH_REST).param("cep", cep))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.cep", is(cep)));
    }
}