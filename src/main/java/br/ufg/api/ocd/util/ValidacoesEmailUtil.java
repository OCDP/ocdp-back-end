package br.ufg.api.ocd.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacoesEmailUtil {
    public static boolean isEmail(String email) {
        boolean isInvalido = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isInvalido = true;
            }
        }
        return isInvalido;
    }
}
