package ru.epam.javacore.homework20200207.common.solutions.functionaliterfaces;

public interface JdbcConsumer<T> {
    void accept(T t) throws Exception;
}