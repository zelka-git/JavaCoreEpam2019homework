package homework20191218.cargo.service;

import homework20191218.cargo.domain.Cargo;
import homework20191218.cargo.repo.CargoRepo;
import homework20191218.common.utils.ArrayUtils;

import java.util.Comparator;

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
    public void sort() {
        Comparator<Cargo> comparator = new Comparator<>() {
            @Override
            public int compare(Cargo o1, Cargo o2) {
                if (!o1.getName().equals(o2.getName())) {
                    return o1.getName().compareTo(o2.getName());
                }
                return Integer.compare(o1.getWeight(), o2.getWeight());
            }
        };
        cargoRepo.sort(comparator);
    }

    @Override
    public void sort(TypeSortCargo typeSortCargo) {
        Comparator<Cargo> comparator;
        switch (typeSortCargo) {
            case NAME:
                comparator = new CompareByName();
                break;
            case WEIGHT:
                comparator = new CompareByWeight();
                break;
            case NAME_WEIGHT:
                comparator = new CompareByNameThenWeight();
                break;
            default:
                comparator = new CompareByWeightThenName();
        }
        cargoRepo.sort(comparator);
    }


    @Override
    public boolean deleteById(Long id) {
        if (id != null) {
            cargoRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void printAll() {
        ArrayUtils.printArray(cargoRepo.getAll());
    }
}

class CompareByName implements Comparator<Cargo> {
    @Override
    public int compare(Cargo o1, Cargo o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class CompareByWeight implements Comparator<Cargo> {
    @Override
    public int compare(Cargo o1, Cargo o2) {
        return Integer.compare(o1.getWeight(), o2.getWeight());
    }
}

class CompareByNameThenWeight implements Comparator<Cargo> {
    @Override
    public int compare(Cargo o1, Cargo o2) {
        if (!o1.getName().equals(o2.getName())) {
            return o1.getName().compareTo(o2.getName());
        }
        return Integer.compare(o1.getWeight(), o2.getWeight());
    }
}

class CompareByWeightThenName implements Comparator<Cargo> {
    @Override
    public int compare(Cargo o1, Cargo o2) {
        if (Integer.compare(o1.getWeight(), o2.getWeight()) != 0) {
            return Integer.compare(o1.getWeight(), o2.getWeight());
        }
        return o1.getName().compareTo(o2.getName());
    }
}
