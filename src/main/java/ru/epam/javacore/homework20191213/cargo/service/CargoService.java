package ru.epam.javacore.homework20191213.cargo.service;

import ru.epam.javacore.homework20191213.cargo.domain.Cargo;

public interface CargoService {
    void add(Cargo cargo);

    Cargo[] getAll();

    Cargo[] getByName(String name);

    Cargo getById(long id);

    Cargo remove(long id);
}
