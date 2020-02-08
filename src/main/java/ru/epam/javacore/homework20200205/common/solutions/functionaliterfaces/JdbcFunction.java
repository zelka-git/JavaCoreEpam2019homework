package ru.epam.javacore.homework20200205.common.solutions.functionaliterfaces;

public interface JdbcFunction<FROM, TO> {
    TO apply(FROM from) throws Exception;
}
