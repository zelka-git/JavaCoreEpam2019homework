package homework20191213.cargo.repo;

import homework20191213.cargo.domain.Cargo;

public interface CargoRepo {
    void add(Cargo cargo);

    Cargo[] getAll();

    Cargo[] getByName(String name);

    Cargo getById(long id);

    Cargo remove(long id);
}
