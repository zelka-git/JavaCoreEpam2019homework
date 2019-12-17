package homework20191216.carrier.service;

import homework20191216.cargo.domain.Cargo;
import homework20191216.cargo.service.CargoService;
import homework20191216.carrier.domain.Carrier;
import homework20191216.carrier.repo.CarrierRepo;

public class CarrierServiceImpl implements CarrierService {

    @Override
    public void add(Carrier carrier, CarrierRepo typeStorage) {
        System.out.println("Begin to add carrier!");
        if (carrier.getId() == null) {
            System.out.println("carrier must have a ID!");
        } else if (typeStorage.getById(carrier.getId()) != null) {
            System.out.println("carrier with such ID already exist!");
        } else if (carrier.getName() == null || carrier.getName() == "") {
            System.out.println("carrier must have a name!");
        } else if (carrier.getAddress() == null) {
            System.out.println("carrier must have a address!");
        } else {
            typeStorage.add(carrier);
            System.out.println("carrier added!!!");
        }
    }

    @Override
    public Carrier[] getAll(CarrierRepo typeStorage) {
        return typeStorage.getAll();
    }

    @Override
    public Carrier[] getByName(String name, CarrierRepo typeStorage) {
        if(name != null){
            return typeStorage.getByName(name);
        }
        return new Carrier[0];
    }

    @Override
    public Carrier getById(Long id, CarrierRepo typeStorage) {
        if(id != null){
            return typeStorage.getById(id);
        }
        return null;
    }

    @Override
    public Carrier remove(Long id, CarrierRepo typeStorage) {
        if(id != null){
            typeStorage.remove(id);
        }
        return null;
    }
}
