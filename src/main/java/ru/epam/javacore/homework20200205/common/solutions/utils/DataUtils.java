package ru.epam.javacore.homework20200205.common.solutions.utils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DataUtils {

    private static final String PATTERN = "dd.MM.yyyy";
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN);

    private DataUtils(){
    }

    public static LocalDate valueOf(String dateStr) throws ParseException {
        return valueOf(dateStr, formatter);
    }

    public static LocalDate valueOf(String dateStr, DateTimeFormatter formatter) throws ParseException {
        return LocalDate.parse(dateStr, formatter);
    }
}
