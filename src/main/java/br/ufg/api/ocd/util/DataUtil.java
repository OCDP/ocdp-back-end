package br.ufg.api.ocd.util;

import lombok.NonNull;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtil {
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static String dateToString(@NonNull Date data) {
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

    public static int diferencaEmDias(@NonNull Date data1, @NonNull Date data2) {
        return Days.daysBetween(new DateTime(data1), new DateTime(data2)).getDays();
    }

    public static String retornaApenasDataString(@NonNull Date data) {
        return retornaString(new SimpleDateFormat("dd/MM/yyyy"), data);
    }

    public static String retornaApenasHoraString(@NonNull Date data) {
        return retornaString(new SimpleDateFormat("HH:mm:ss"), data);
    }

    private static String retornaString(SimpleDateFormat fmt, Date data) {
        try {
            return fmt.format(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
