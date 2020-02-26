package br.ufg.api.ocd.util;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.stream.Collectors;

public class ValidacoesDtoUtil {

    public static String createErrorString(Errors errors) {
        return errors.getAllErrors().stream().map(ObjectError::toString).collect(Collectors.joining(","));
    }
}
