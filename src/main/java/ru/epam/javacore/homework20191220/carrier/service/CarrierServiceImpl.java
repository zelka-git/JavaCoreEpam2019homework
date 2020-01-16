package ru.epam.javacore.homework20191220.carrier.service;

import main.homework20191220.carrier.domain.Carrier;
import main.homework20191220.carrier.repo.CarrierRepo;
import main.homework20191220.common.utils.ArrayUtils;

public class CarrierServiceImpl implements CarrierService {

    private static final Carrier[] EMPTY_CARRIER_ARRAY = new Carrier[0];

    private CarrierRepo carrierRepo;

    public CarrierServiceImpl(CarrierRepo carrierRepo) {
        this.carrierRepo = carrierRepo;
    }

    @Override
    public void add(Carrier carrier) {
        System.out.println("Begin to add carrier!");
        if (carrier.getName() == null || carrier.getName().equals("")) {
            System.out.println("carrier must have a name!");
        } else if (carrier.getAddress() == null) {
            System.out.println("carrier must have a address!");
        } else {
            carrierRepo.add(carrier);
            System.out.println("carrier added!!!");
        }
    }

    @Override
    public Carrier[] getAll() {
        return carrierRepo.getAll();
    }

    @Override
    public Carrier[] getByName(String name) {
        if (name != null) {
            return carrierRepo.getByName(name);
        }
        return EMPTY_CARRIER_ARRAY;
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
                throw new RuntimeException("Carrier has a reference to transportation and can't be deleted");
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
