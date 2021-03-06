package br.ufg.api.ocd.test.controller;

import br.ufg.api.ocd.controller.FatorRiscoController;
import br.ufg.api.ocd.dto.CidadeDTO;
import br.ufg.api.ocd.dto.FatorRiscoDTO;
import br.ufg.api.ocd.service.FatorRiscoService;
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
@WebMvcTest(value = FatorRiscoController.class, secure = false)
public class FatorRiscoControllerTest extends SalvaEhAtualizaTest {

    private static final String PATH_REST = "/api/fatorRisco";

    @Autowired
    private MockMvc mvc;

    private CommonPathChecker commonPathChecker;

    @MockBean
    private FatorRiscoService service;

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
    public void getAll_OK() throws Exception {
        String path = this.PATH_REST;
        FatorRiscoDTO dto = new FatorRiscoDTO();
        mvc.perform(get(path)).andExpect(status().isOk());
    }

}