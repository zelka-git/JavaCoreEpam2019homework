package ru.epam.javacore.homework20200210.common.solutions.repo.jdbc;

public interface JdbcConsumer<T> {
    void accept(T t) throws Exception;
}