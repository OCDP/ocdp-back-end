package br.ufg.api.ocd.util;

import lombok.NonNull;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DataUtil {
    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

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
