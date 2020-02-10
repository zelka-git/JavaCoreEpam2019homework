package ru.epam.javacore.homework20200207.cargo.service;

import ru.epam.javacore.homework20200207.application.serviceholder.ServiceHolder;
import ru.epam.javacore.homework20200207.cargo.domain.Cargo;
import ru.epam.javacore.homework20200207.cargo.exception.unckecked.CargoDeleteConstraintViolationException;
import ru.epam.javacore.homework20200207.cargo.repo.CargoRepo;
import ru.epam.javacore.homework20200207.cargo.search.CargoSearchCondition;
import ru.epam.javacore.homework20200207.cargo.service.CargoService;
import ru.epam.javacore.homework20200207.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20200207.carrier.domain.Carrier;
import ru.epam.javacore.homework20200207.common.business.connectionbd.ConnectionBdH2;
import ru.epam.javacore.homework20200207.common.solutions.utils.ArrayUtils;
import ru.epam.javacore.homework20200207.transportation.domain.Transportation;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Cargo> getById(Long id) {
        if (id != null) {
            return cargoRepo.getById(id);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Cargo> getByIdFetchingTransportations(Long id) {
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
    public int countAll() {
        return cargoRepo.countAll();
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
        Optional<Cargo> cargoOptional = this.getByIdFetchingTransportations(id);
        if (cargoOptional.isPresent()) {
            List<Transportation> transportation = cargoOptional.get().getTransportations();

            if (transportation != null && transportation.size() > 0) {
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

    @Override
    public void addList(List<Cargo> cargos) {
        try {
            Connection connection = null;
            try {
                connection = ConnectionBdH2
                        .getInstance().getConnection();
                connection.setAutoCommit(false);
                for (Cargo item : cargos) {
                    cargoRepo.add(item);
                }
            } catch (Exception e) {
                if (connection != null) {
                    System.out.println("Revert ");
                    connection.rollback();
                }
            } finally {
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveCargosWithCarriers(List<Cargo> cargos, List<Carrier> carriers) {
        cargoRepo.addList(cargos);
        ServiceHolder.getInstance().getCarrierService().addList(carriers);
    }
}

