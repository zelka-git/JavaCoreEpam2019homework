package ru.epam.javacore.homework20200207.common.solutions.functionaliterfaces;

public interface JdbcFunction<FROM, TO> {
    TO apply(FROM from) throws Exception;
}
