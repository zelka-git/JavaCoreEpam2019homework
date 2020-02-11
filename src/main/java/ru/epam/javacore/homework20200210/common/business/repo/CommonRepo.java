package ru.epam.javacore.homework20200210.common.business.repo;


import java.util.List;
import java.util.Optional;

public interface CommonRepo<T, Id> {

    boolean deleteById(Id id);

    void add(T element);

    List<T> getAll();

    Optional<T> getById(Id id);

    boolean update(T element);

    int countAll();
}
