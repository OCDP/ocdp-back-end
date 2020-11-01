package br.ufg.api.ocd.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidacoesEmailUtilTest {

    @Test
    public void testa_email_valido() {
        String email = "teste@provedor.com.br";

        boolean actual = ValidacoesEmailUtil.isEmail(email);

        assertTrue(actual);
    }

    @Test
    public void testa_emails_invalidos() {
        testarEmailInvalido("asdfasdf");
        testarEmailInvalido("email@");
        testarEmailInvalido("@provedor");
        testarEmailInvalido("e@mail@provedor");
    }

    private void testarEmailInvalido(String email) {
        boolean actual = ValidacoesEmailUtil.isEmail(email);

        assertFalse(actual);
    }

}