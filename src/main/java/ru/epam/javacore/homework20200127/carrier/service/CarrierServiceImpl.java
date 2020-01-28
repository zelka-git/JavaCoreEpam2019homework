package ru.epam.javacore.homework20200127.carrier.service;

import ru.epam.javacore.homework20200127.carrier.domain.Carrier;
import ru.epam.javacore.homework20200127.carrier.exception.unckecked.CarrierDeleteConstraintViolationException;
import ru.epam.javacore.homework20200127.carrier.repo.CarrierRepo;
import ru.epam.javacore.homework20200127.carrier.service.CarrierService;
import ru.epam.javacore.homework20200127.common.solutions.utils.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class CarrierServiceImpl implements CarrierService {

    private static final List<Carrier> EMPTY_CARRIER_LIST = new ArrayList<>();

    private CarrierRepo carrierRepo;

    public CarrierServiceImpl(CarrierRepo carrierRepo) {
        this.carrierRepo = carrierRepo;
    }

    @Override
    public void add(Carrier carrier) {
//        System.out.println("Begin to add carrier!");
        if (carrier.getName() == null || carrier.getName().equals("")) {
            System.out.println("carrier must have a name!");
        } else if (carrier.getAddress() == null) {
            System.out.println("carrier must have a address!");
        } else {
            carrierRepo.add(carrier);
//            System.out.println("carrier added!!!");
        }
    }

    @Override
    public List<Carrier> getAll() {
        return carrierRepo.getAll();
    }

    @Override
    public List<Carrier> getByName(String name) {
        if (name != null) {
            return carrierRepo.getByName(name);
        }
        return EMPTY_CARRIER_LIST;
    }

    @Override
    public Carrier getById(Long id) {
        if (id != null) {
            return carrierRepo.getById(id);
        }
        return null;
    }

    @Override
    public boolean update(Carrier carrier) {
        if (carrier == null) {
            System.out.println("value NULL don't available!");
        } else if (carrier.getId() == null) {
            System.out.println("carrier must have a ID!");
        } else if (carrier.getName() == null || carrier.getName().equals("")) {
            System.out.println("carrier must have a name!");
        } else if (carrier.getAddress() == null) {
            System.out.println("carrier must have a address!");
        } else if (carrierRepo.update(carrier)) {
            System.out.println("carrier added!!!");
            return true;
        }
        System.out.println("carrier don't found");
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        if (id != null) {
            if (carrierRepo.getById(id).getTransportations() != null) {
                throw new CarrierDeleteConstraintViolationException(id);
            }
            return carrierRepo.deleteById(id);
        }
        return false;
    }

    @Override
    public void printAll() {
        ArrayUtils.printArray(carrierRepo.getAll());
    }
}
