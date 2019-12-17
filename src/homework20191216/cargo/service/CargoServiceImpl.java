package homework20191216.cargo.service;

import homework20191216.cargo.domain.Cargo;
import homework20191216.cargo.repo.CargoCollectionRepoImpl;
import homework20191216.cargo.repo.CargoRepo;

public class CargoServiceImpl implements CargoService {
    @Override
    public void add(Cargo cargo, CargoRepo typeStorage) {
        System.out.println("Begin to add cargo!");
        if (cargo.getId() == null) {
            System.out.println("cargo must have a ID!");
        } else if (typeStorage.getById(cargo.getId()) != null) {
            System.out.println("cargo with such ID already exist!");
        } else if (cargo.getName() == null || cargo.getName() == "") {
            System.out.println("cargo must have a name!");
        } else if (cargo.getWeight() == 0) {
            System.out.println("cargo must have a weight!");
        } else {
            typeStorage.add(cargo);
            System.out.println("cargo added!!!");
        }
    }

    @Override
    public Cargo[] getAll(CargoRepo typeStorage) {
        return typeStorage.getAll();
    }

    @Override
    public Cargo[] getByName(String name, CargoRepo typeStorage) {
        if(name != null){
            return typeStorage.getByName(name);
        }
        return new Cargo[0];
    }

    @Override
    public Cargo getById(Long id, CargoRepo typeStorage) {
        if(id != null){
            return typeStorage.getById(id);
        }
        return null;
    }

    @Override
    public Cargo remove(Long id, CargoRepo typeStorage) {
        if(id != null){
            typeStorage.remove(id);
        }
        return null;
    }
}
