package main.homework20200113.common.business.service;

import java.util.List;

public interface CommonService<T, Id> {
    boolean deleteById(Id id);

    void printAll();

    void add(T element);

    List<T> getAll();

    T getById(Id id);

    boolean update(T element);

}
