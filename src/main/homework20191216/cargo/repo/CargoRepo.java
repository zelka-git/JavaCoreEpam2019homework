package main.homework20191216.cargo.repo;

import main.homework20191216.cargo.domain.Cargo;
import main.homework20191216.common.repo.CommonRepo;

public interface CargoRepo extends CommonRepo {
    void add(Cargo cargo);

    Cargo[] getAll();

    Cargo[] getByName(String name);

    Cargo getById(long id);

}
