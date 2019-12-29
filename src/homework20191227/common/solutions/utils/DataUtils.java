package homework20191227.common.solutions.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DataUtils {

    private static final String PATTERN = "dd.MM.yyyy";

    private DataUtils(){

    }

    public static Date valueOf(String dateStr, String pattern) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.parse(dateStr);
    }

    public static Date valueOf(String dateStr) throws ParseException {
        return valueOf(dateStr, PATTERN);
    }
}
