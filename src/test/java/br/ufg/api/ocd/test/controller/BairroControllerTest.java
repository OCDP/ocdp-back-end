package br.ufg.api.ocd.test.controller;

import br.ufg.api.ocd.controller.BairroController;
import br.ufg.api.ocd.dto.BairroDTO;
import br.ufg.api.ocd.service.BairroService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = BairroController.class, secure = false)
public class BairroControllerTest extends SalvaEhAtualizaTest {

    private static final String PATH_REST = "/api/bairro";

    @Autowired
    private MockMvc mvc;

    private CommonPathChecker commonPathChecker;

    @MockBean
    private BairroService service;

    @MockBean
    private ModelMapper modelMapper;

    @Before
    public void before() {
        this.commonPathChecker = new CommonPathChecker(mvc);
    }

    @Test
    public void atualizar_ErroAoConverterDTO() throws Exception {
        commonPathChecker.checkerConverterErrorPutTo(this.PATH_REST);
    }

    @Test
    public void getById_OK() throws Exception {
        String path = this.PATH_REST+"/byId/1";
        BairroDTO dto = new BairroDTO();
        mvc.perform(get(path)).andExpect(status().isOk());
    }

    @Test
    public void getByName_OK() throws Exception {
        String path = this.PATH_REST+"/byCidade/Anapolis";
        BairroDTO dto = new BairroDTO();
        mvc.perform(get(path)).andExpect(status().isOk());
    }

    @Test
    public void getById_DeveRetornarRecursoNaoEncontrado() throws Exception {
        String path = this.PATH_REST+"/byId/";
        mvc.perform(get(path)).andExpect(status().isNotFound());
    }

    @Test
    public void getByCidade_DeveRetornarRecursoNaoEncontrado() throws Exception {
        String path = this.PATH_REST+"/byCidade/";
        mvc.perform(get(path)).andExpect(status().isNotFound());
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
        BairroDTO dto = new BairroDTO();
        dto.setNome("Nome de um Bairro");
        return dto;
    }

    @Override
    public Object getInvalidContent() {
        return new BairroDTO();
    }

    @Override
    public MockMvc getMvc() {
        return this.mvc;
    }
}