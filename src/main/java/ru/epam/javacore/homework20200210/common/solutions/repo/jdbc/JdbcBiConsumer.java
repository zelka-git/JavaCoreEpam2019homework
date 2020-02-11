package ru.epam.javacore.homework20200210.common.solutions.repo.jdbc;

public interface JdbcBiConsumer<T, V> {
    void accept(T t, V v) throws Exception;
}
