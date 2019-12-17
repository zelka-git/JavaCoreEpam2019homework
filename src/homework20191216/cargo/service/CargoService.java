package homework20191216.cargo.service;

import homework20191216.cargo.domain.Cargo;
import homework20191216.cargo.repo.CargoRepo;

public interface CargoService {
    void add(Cargo cargo, CargoRepo typeStorage);

    Cargo[] getAll(CargoRepo typeStorage);

    Cargo[] getByName(String name, CargoRepo typeStorage);

    Cargo getById(Long id, CargoRepo typeStorage);

    Cargo remove(Long id, CargoRepo typeStorage);
}
