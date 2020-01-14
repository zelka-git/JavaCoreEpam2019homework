package main.homework20191220.cargo.repo;

import main.homework20191220.cargo.domain.Cargo;
import main.homework20191220.cargo.service.TypeSortCargo;
import main.homework20191220.common.repo.CommonRepo;

public interface CargoRepo extends CommonRepo {
    void add(Cargo cargo);

    Cargo[] getAll();

    Cargo[] getByName(String name);

    Cargo getById(long id);

    boolean update(Cargo cargo);

    Cargo[] getAllSortedItems(TypeSortCargo typeSortCargo);
}
