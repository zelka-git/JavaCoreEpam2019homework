package ru.epam.javacore.homework20200205.common.solutions.functionaliterfaces;

public interface JdbcBiConsumer<T, V> {
    void accept(T t, V v) throws Exception;
}
