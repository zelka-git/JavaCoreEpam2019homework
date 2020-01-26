package ru.epam.javacore.homework20200124.cargo.service;

import ru.epam.javacore.homework20200124.cargo.domain.Cargo;
import ru.epam.javacore.homework20200124.cargo.exception.unckecked.CargoDeleteConstraintViolationException;
import ru.epam.javacore.homework20200124.cargo.repo.CargoRepo;
import ru.epam.javacore.homework20200124.cargo.search.CargoSearchCondition;
import ru.epam.javacore.homework20200124.cargo.service.CargoService;
import ru.epam.javacore.homework20200124.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20200124.common.solutions.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class CargoServiceImpl implements CargoService {

    private static final List<Cargo> EMPTY_CARGO_LIST = new ArrayList<>();

    private CargoRepo cargoRepo;

    public CargoServiceImpl(CargoRepo cargoRepo) {
        this.cargoRepo = cargoRepo;
    }

    @Override
    public void add(Cargo cargo) {
//        System.out.println("Begin to add cargo!");
        if (cargo.getName() == null || cargo.getName().equals("")) {
            System.out.println("cargo must have a name!");
        } else if (cargo.getWeight() == 0) {
            System.out.println("cargo must have a weight!");
        } else {
            cargoRepo.add(cargo);
//            System.out.println("cargo added!!!");
        }
    }

    @Override
    public List<Cargo> getAll() {
        return cargoRepo.getAll();
    }

    @Override
    public List<Cargo> getByName(String name) {
        if (name != null) {
            return cargoRepo.getByName(name);
        }
        return EMPTY_CARGO_LIST;
    }

    @Override
    public Cargo getById(Long id) {
        if (id != null) {
            return cargoRepo.getById(id);
        }
        return null;
    }

    @Override
    public Cargo getByIdFetchingTransportations(Long id) {
        return getById(id);
    }

    @Override
    public boolean update(Cargo cargo) {
        if (cargo == null) {
            System.out.println("value NULL don't available!");
        } else if (cargo.getId() == null) {
            System.out.println("cargo must have a ID!");
        } else if (cargo.getName() == null || cargo.getName().equals("")) {
            System.out.println("cargo must have a name!");
        } else if (cargo.getWeight() == 0) {
            System.out.println("cargo must have a weight!");
        } else if (cargoRepo.update(cargo)) {
            System.out.println("cargo added!!!");
            return true;
        }
        System.out.println("cargo don't found");
        return false;
    }


    @Override
    public List<Cargo> getAllSortedItems(TypeSortCargo typeSortCargo) {
        return cargoRepo.getAllSortedItems(typeSortCargo);
    }

    @Override
    public List<Cargo> search(CargoSearchCondition cargoSearchCondition) {
        return cargoRepo.search(cargoSearchCondition);
    }


    @Override
    public boolean deleteById(Long id) {
        if(id != null){
            if(cargoRepo.getById(id).getTransportations() != null){
                throw new CargoDeleteConstraintViolationException(id);
            }
            return cargoRepo.deleteById(id);
        }
            return false;
    }

    @Override
    public void printAll() {
        ArrayUtils.printArray(cargoRepo.getAll());
    }
}

