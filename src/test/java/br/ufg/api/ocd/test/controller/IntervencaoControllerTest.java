package br.ufg.api.ocd.test.controller;

import br.ufg.api.ocd.controller.IntervencaoController;
import br.ufg.api.ocd.dto.AtendimentoDTO;
import br.ufg.api.ocd.dto.IntervencaoDTO;
import br.ufg.api.ocd.dto.ProcedimentosIntervencaoDTO;
import br.ufg.api.ocd.model.Atendimento;
import br.ufg.api.ocd.service.AtendimentoService;
import br.ufg.api.ocd.service.BairroService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = IntervencaoController.class, secure = false)
public class IntervencaoControllerTest {

    private final String PATH_REST = "/api/intervencao";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AtendimentoService service;

    private CommonPathChecker commonPathChecker;

    @MockBean
    private ModelMapper modelMapper;

    @Before
    public void before() {

        this.commonPathChecker = new CommonPathChecker(mvc);

    }

    @Test
    public void salvar_OK() throws Exception {
        String path = this.PATH_REST+"/salvar";
        IntervencaoDTO validContent = createValidContent();
        this.commonPathChecker.verificarStatusOkPost(path, validContent);
    }

    private IntervencaoDTO createValidContent() {
        IntervencaoDTO validContent = new IntervencaoDTO();
        validContent.setAtendimento(new AtendimentoDTO());
        validContent.setHipoteseDiagnostico("hipotese");
        ProcedimentosIntervencaoDTO procedimentosIntervencaoDTO =  new ProcedimentosIntervencaoDTO();
        procedimentosIntervencaoDTO.setNome("procedimento");
        validContent.setProcedimentos(Collections.singletonList(procedimentosIntervencaoDTO));
        return validContent;
    }

    @Test
    public void salvar_DtoInvalido() throws Exception {
        String path = this.PATH_REST+"/salvar";
        IntervencaoDTO invalidContent = new IntervencaoDTO();
        this.mvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UtilTest.toJson(invalidContent))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void salvar_ErroAoConverterDTO() throws Exception {
        commonPathChecker.checkerConverterErrorPostTo(this.PATH_REST+"/salvar");
    }
}