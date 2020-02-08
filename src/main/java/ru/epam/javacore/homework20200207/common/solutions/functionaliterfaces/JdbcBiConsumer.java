package ru.epam.javacore.homework20200207.common.solutions.functionaliterfaces;

public interface JdbcBiConsumer<T, V> {
    void accept(T t, V v) throws Exception;
}
