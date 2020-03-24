package br.ufg.api.ocd.test.controller;

import br.ufg.api.ocd.controller.LesaoController;
import br.ufg.api.ocd.dto.LesaoDTO;
import br.ufg.api.ocd.model.Lesao;
import br.ufg.api.ocd.service.LesaoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LesaoController.class, secure = false)
public class LesaoControllerTest extends SalvaEhAtualizaTest{

    private static final String PATH_REST = "/api/lesao";

    @Autowired
    private MockMvc mvc;

    private CommonPathChecker commonPathChecker;

    @MockBean
    private LesaoService service;

    @MockBean
    private ModelMapper modelMapper;

    @Before
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
        LesaoDTO dto = new LesaoDTO();
        dto.setNome("nome lesão");
        return dto;
    }

    @Override
    public Object getInvalidContent() {
        return new LesaoDTO();
    }

    @Override
    public MockMvc getMvc() {
        return this.mvc;
    }

    @Test
    public void getById_OK() throws Exception {
        mvc.perform(
                get(this.PATH_REST+"/byId/1")
        ).andExpect(status().isOk());
    }

    @Test
    public void getByTipoLesao_OK() throws Exception {
        mvc.perform(
                get(this.PATH_REST+"/byTipo/Maligna")
        ).andExpect(status().isOk());
    }

    @Test
    public void getLesaos_OK() throws Exception {
        mvc.perform(
                get(this.PATH_REST)
        ).andExpect(status().isOk());
    }

}