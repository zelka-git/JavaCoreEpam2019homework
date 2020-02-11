package ru.epam.javacore.homework20200210.common.solutions.repo.jdbc;

public interface JdbcFunction<FROM, TO> {
    TO apply(FROM from) throws Exception;
}
