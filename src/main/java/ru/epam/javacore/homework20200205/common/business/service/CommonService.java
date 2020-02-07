package ru.epam.javacore.homework20200205.common.business.service;

import java.util.List;
import java.util.Optional;

public interface CommonService<T, Id> {
    boolean deleteById(Id id);

    void printAll();

    void add(T element);

    List<T> getAll();

    Optional<T> getById(Id id);

    boolean update(T element);

}
