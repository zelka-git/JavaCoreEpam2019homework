package ru.epam.javacore.homework20191223.common.business.service;

import java.util.List;

public interface CommonService<T> {
    boolean deleteById(Long id);

    void printAll();

    void add(T element);

    List<T> getAll();

    T getById(Long id);

    boolean update(T element);

}
