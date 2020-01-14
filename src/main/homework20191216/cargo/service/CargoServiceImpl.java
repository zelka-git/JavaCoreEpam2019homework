package main.homework20191216.cargo.service;

import main.homework20191216.cargo.domain.Cargo;
import main.homework20191216.cargo.repo.CargoRepo;

public class CargoServiceImpl implements CargoService {

    private static final Cargo[] EMPTY_CARGO_ARRAY = new Cargo[0];

    private CargoRepo cargoRepo;

    public CargoServiceImpl(CargoRepo cargoRepo) {
        this.cargoRepo = cargoRepo;
    }

    @Override
    public void add(Cargo cargo) {
        System.out.println("Begin to add cargo!");
        if (cargo.getName() == null || cargo.getName().equals("")) {
            System.out.println("cargo must have a name!");
        } else if (cargo.getWeight() == 0) {
            System.out.println("cargo must have a weight!");
        } else {
            cargoRepo.add(cargo);
            System.out.println("cargo added!!!");
        }
    }

    @Override
    public Cargo[] getAll() {
        return cargoRepo.getAll();
    }

    @Override
    public Cargo[] getByName(String name) {
        if (name != null) {
            return cargoRepo.getByName(name);
        }
        return EMPTY_CARGO_ARRAY;
    }

    @Override
    public Cargo getById(Long id) {
        if (id != null) {
            return cargoRepo.getById(id);
        }
        return null;
    }

    @Override
    public Cargo remove(Long id) {
        if (id != null) {
            cargoRepo.remove(id);
        }
        return null;
    }
}
