package br.ufg.api.ocd.test.controller;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CommonPathChecker {

    private final String JSON_COM_ERRO = "{\"algumAtributo: 12}";//simula um json com erro (aspa não fechada)

    private MockMvc mvc;

    public CommonPathChecker(MockMvc mvc){
        this.mvc = mvc;
    }

    public void verificarStatusOkPut(String path, Object content) throws Exception {
        MockHttpServletRequestBuilder httpMethod = put(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UtilTest.toJson(content));
        performStatusOK(httpMethod);
    }

    public void verificarStatusOkPost(String path, Object content) throws Exception {
        MockHttpServletRequestBuilder httpMethod = post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(UtilTest.toJson(content));
        performStatusOK(httpMethod);
    }

    private void performStatusOK(MockHttpServletRequestBuilder httpMethod) throws Exception {
        mvc.perform(httpMethod).andExpect(status().isOk());
    }

    public void checkerConverterErrorPutTo(String path) throws Exception {
        mvc.perform(put(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON_COM_ERRO)
        ).andExpect(status().isBadRequest());
    }

    public void checkerConverterErrorPostTo(String path) throws Exception {
        mvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON_COM_ERRO)
        ).andExpect(status().isBadRequest());
    }
}
