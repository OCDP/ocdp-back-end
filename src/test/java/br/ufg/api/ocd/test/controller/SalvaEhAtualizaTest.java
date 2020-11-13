package br.ufg.api.ocd.test.controller;


import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public abstract class SalvaEhAtualizaTest {

    public abstract String getPathRest();

    public abstract CommonPathChecker getCommonPathChecker();

    public abstract Object getValidContent();

    public abstract Object getInvalidContent();

    public abstract MockMvc getMvc();

    @Test
    public void salvar_OK() throws Exception {
        String path = getPathRest();
        Object validContent = getValidContent();
        getCommonPathChecker().verificarStatusOkPost(path, validContent);
    }

    @Test
    public void salvar_DtoInvalido() throws Exception {
        String path = getPathRest();
        Object invalidContent = getInvalidContent();
        getMvc().perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UtilTest.toJson(invalidContent))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void salvar_ErroAoConverterDTO() throws Exception {
        getCommonPathChecker().checkerConverterErrorPostTo(getPathRest());
    }

    @Test
    public void atualizar_OK() throws Exception {
        String path = getPathRest();
        getCommonPathChecker().verificarStatusOkPut(path, getValidContent());
    }

    @Test
    public void atualizar_DtoInvalido() throws Exception {
        String path = getPathRest();
        getMvc().perform(put(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UtilTest.toJson(getInvalidContent()))
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void atualizar_ErroAoConverterDTO() throws Exception {
        getCommonPathChecker().checkerConverterErrorPutTo(getPathRest());
    }

}
