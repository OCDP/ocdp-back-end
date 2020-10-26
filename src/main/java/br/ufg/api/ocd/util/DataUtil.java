package br.ufg.api.ocd.util;

import lombok.NonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DataUtil {
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static DateTimeFormatter fmtImg = DateTimeFormatter.ofPattern("dd-MM-yyyyHH:mm:ss");

    public static String dateToStringImg(@NonNull LocalDateTime data) {
        return retornaString(fmtImg, data);
    }

    public static String dateToString(@NonNull LocalDateTime data) {
        return retornaString(fmt, data);
    }

    public static LocalDateTime stringToDate(@NonNull String data) {
        return LocalDateTime.parse(data, fmt);
    }

    public static int diferencaEmMeses(@NonNull LocalDateTime data1, @NonNull LocalDateTime data2) {
        LocalDate localDate1 = data1.toLocalDate();
        LocalDate localDate2 = data2.toLocalDate();
        Period age = Period.between(localDate1, localDate2);
        return age.getMonths();
    }

    public static String retornaApenasDataString(@NonNull LocalDateTime data) {
        return retornaString(DateTimeFormatter.ofPattern("dd/MM/yyyy"), data);
    }

    public static String retornaApenasHoraString(@NonNull LocalDateTime data) {
        return retornaString(DateTimeFormatter.ofPattern("HH:mm:ss"), data);
    }

    private static String retornaString(DateTimeFormatter fmt, LocalDateTime data) {
        try {
            return data.format(fmt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
