package br.ufg.api.ocd.controller;

import br.ufg.api.ocd.dto.VersaoBancoDTO;
import br.ufg.api.ocd.service.ParametrosSchedulerService;
import br.ufg.api.ocd.service.VersaoBancoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(value = AdminController.class)
public class AdminControllerTest {

    private static final String PATH_REST = "/api/admin";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VersaoBancoService versaoBancoService;

    @MockBean
    private ParametrosSchedulerService parametrosService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void atualizaVersaoBanco_OK() throws Exception {
        VersaoBancoDTO dto = new VersaoBancoDTO();
        mvc.perform(
                put(this.PATH_REST+"/versaoBanco")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(UtilTest.toJson(dto))
        ).andExpect(status().isOk());
    }

    @Test
    public void atualizaVersaoBanco_ErroAoConverterDTO() throws Exception {
        mvc.perform(put(this.PATH_REST+"/versaoBanco")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"algumAtributo: 12}")//simula um json com erro (aspa não fechada)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void atualizaVersaoBanco_ServiceLancaException() throws Exception {
        Mockito.when(versaoBancoService.atualizar(Mockito.any())).thenThrow(Exception.class);
        mvc.perform(put(this.PATH_REST+"/versaoBanco")
                .contentType(MediaType.APPLICATION_JSON)
                .content(UtilTest.toJson(new VersaoBancoDTO()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void getByIdVersaoBanco_OK() throws Exception {
        VersaoBancoDTO dto = new VersaoBancoDTO();
        mvc.perform(
                get(this.PATH_REST+"/byId/1")
        ).andExpect(status().isOk());
    }

    @Test
    public void get_OK() throws Exception {
        VersaoBancoDTO dto = new VersaoBancoDTO();
        mvc.perform(
                get(this.PATH_REST)
        ).andExpect(status().isOk());
    }

}