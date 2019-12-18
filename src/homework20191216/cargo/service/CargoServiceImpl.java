package homework20191216.cargo.service;

import homework20191216.cargo.domain.Cargo;
import homework20191216.cargo.repo.CargoCollectionRepoImpl;
import homework20191216.cargo.repo.CargoRepo;

public class CargoServiceImpl implements CargoService {

    private CargoRepo cargoRepo;

    public CargoServiceImpl(CargoRepo cargoRepo) {
        this.cargoRepo = cargoRepo;
    }

    @Override
    public void add(Cargo cargo) {
        System.out.println("Begin to add cargo!");
        if (cargo.getId() == null) {
            System.out.println("cargo must have a ID!");
        } else if (cargoRepo.getById(cargo.getId()) != null) {
            System.out.println("cargo with such ID already exist!");
        } else if (cargo.getName() == null || cargo.getName() == "") {
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
        return new Cargo[0];
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
