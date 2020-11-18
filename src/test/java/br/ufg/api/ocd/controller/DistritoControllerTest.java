package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.CidadeDTO;
import br.ufg.api.ocd.dto.DistritoDTO;
import br.ufg.api.ocd.service.DistritoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = DistritoController.class)
public class DistritoControllerTest extends SalvaEhAtualizaTest {

    private static final String PATH_REST = "/api/distrito";

    @Autowired
    private MockMvc mvc;

    private CommonPathChecker commonPathChecker;

    @MockBean
    private DistritoService service;

    @MockBean
    private ModelMapper modelMapper;

    @BeforeEach
    public void before() {
        this.commonPathChecker = new CommonPathChecker(mvc);
    }

    @Override
    public String getPathRest() {
        return this.PATH_REST;
    }

    @Override
    public CommonPathChecker getCommonPathChecker() {
        return this.commonPathChecker;
    }

    @Override
    public Object getValidContent() {
        CidadeDTO dto = new CidadeDTO();
        dto.setNome("Nome Cidade");
        return dto;
    }

    @Override
    public Object getInvalidContent() {
        return new CidadeDTO();
    }

    @Override
    public MockMvc getMvc() {
        return this.mvc;
    }

    @Test
    public void getById_OK() throws Exception {
        String path = this.PATH_REST+"/byId/1";
        DistritoDTO dto = new DistritoDTO();
        mvc.perform(get(path)).andExpect(status().isOk());
    }

    @Test
    public void getById_DeveRetornarRecursoNaoEncontrado() throws Exception {
        String path = this.PATH_REST+"/byId/";
        mvc.perform(get(path)).andExpect(status().isNotFound());
    }

}