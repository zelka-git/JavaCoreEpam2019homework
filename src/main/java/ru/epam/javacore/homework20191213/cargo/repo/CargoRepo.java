package ru.epam.javacore.homework20191213.cargo.repo;

import main.homework20191213.cargo.domain.Cargo;

public interface CargoRepo {
    void add(Cargo cargo);

    Cargo[] getAll();

    Cargo[] getByName(String name);

    Cargo getById(long id);

    boolean remove(long id);
}
