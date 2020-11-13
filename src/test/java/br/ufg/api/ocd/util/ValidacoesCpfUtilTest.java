package br.ufg.api.ocd.util;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidacoesCpfUtilTest {

    @Test
    public void teste_validacao_com_cpf_invalido() {
        String cpf = "23539192000";

        boolean actual = ValidacoesCpfUtil.isCpf(cpf);

        assertTrue(actual);
    }

    @Test
    public void teste_validacao_com_cpf_nulo() {
        String cpf = null;

        boolean actual = ValidacoesCpfUtil.isCpf(cpf);

        assertFalse(actual);
    }

    @Test
    public void teste_validacao_com_cpf_incompleto() {
        String cpf = "2353919200";

        boolean actual = ValidacoesCpfUtil.isCpf(cpf);

        assertFalse(actual);
    }

    @Test
    public void teste_validacao_com_cpf_caracteres_invalidos() {
        String cpf = "235391.200a";

        boolean actual = ValidacoesCpfUtil.isCpf(cpf);

        assertFalse(actual);
    }

}