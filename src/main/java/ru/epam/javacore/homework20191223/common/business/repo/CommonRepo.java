package ru.epam.javacore.homework20191223.common.business.repo;


import java.util.List;

public interface CommonRepo<T> {

    boolean deleteById(long id);

    void add(T element);

    List<T> getAll();

    T getById(long id);

    boolean update(T element);
}
