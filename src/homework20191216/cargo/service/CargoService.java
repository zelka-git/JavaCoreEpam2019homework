package homework20191216.cargo.service;

import homework20191216.cargo.domain.Cargo;

public interface CargoService {
    void add(Cargo cargo);

    Cargo[] getAll();

    Cargo[] getByName(String name);

    Cargo getById(Long id);

    Cargo remove(Long id);
}
