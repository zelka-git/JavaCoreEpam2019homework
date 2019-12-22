package homework20191220.cargo.repo;

import homework20191220.cargo.domain.Cargo;
import homework20191220.cargo.service.TypeSortCargo;
import homework20191220.common.repo.CommonRepo;

public interface CargoRepo extends CommonRepo {
    void add(Cargo cargo);

    Cargo[] getAll();

    Cargo[] getByName(String name);

    Cargo getById(long id);

    boolean update(Cargo cargo);

    Cargo[] getAllSortedItems(TypeSortCargo typeSortCargo);
}
