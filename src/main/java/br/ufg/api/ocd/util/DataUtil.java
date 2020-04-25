package br.ufg.api.ocd.util;

import lombok.NonNull;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class DataUtil {
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static String dateToString(@NonNull LocalDateTime data) {
        return retornaString(fmt, data);
    }

    public static Date stringToDate(@NonNull String data) {
        try {
            return fmt.parse(data);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int diferencaEmMeses(@NonNull LocalDateTime data1, @NonNull LocalDateTime data2) {
        DateTime dateTime1 = new DateTime(data1);
        DateTime dateTime2 = new DateTime(data2);
        return Months.monthsBetween(dateTime1, dateTime2).getMonths();
    }

    public static String retornaApenasDataString(@NonNull LocalDateTime data) {
        return retornaString(new SimpleDateFormat("dd/MM/yyyy"), data);
    }

    public static String retornaApenasHoraString(@NonNull LocalDateTime data) {
        return retornaString(new SimpleDateFormat("HH:mm:ss"), data);
    }

    private static String retornaString(SimpleDateFormat fmt, LocalDateTime data) {
        try {
            return fmt.format(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
