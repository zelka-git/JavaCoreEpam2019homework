package ru.epam.javacore.homework20191218.cargo.repo;

import main.homework20191218.cargo.domain.Cargo;
import main.homework20191218.cargo.service.TypeSortCargo;
import main.homework20191218.common.repo.CommonRepo;

public interface CargoRepo extends CommonRepo {
    void add(Cargo cargo);

    Cargo[] getAll();

    Cargo[] getByName(String name);

    Cargo getById(long id);

    boolean update(Cargo cargo);

    Cargo[] getAllSortedItems(TypeSortCargo typeSortCargo);
}
