package homework20191216.carrier.service;

import homework20191216.carrier.domain.Carrier;
import homework20191216.carrier.repo.CarrierRepo;

public class CarrierServiceImpl implements CarrierService {

    private CarrierRepo carrierRepo;

    CarrierServiceImpl(CarrierRepo carrierRepo){
        this.carrierRepo = carrierRepo;
    }

    @Override
    public void add(Carrier carrier) {
        System.out.println("Begin to add carrier!");
        if (carrier.getId() == null) {
            System.out.println("carrier must have a ID!");
        } else if (carrierRepo.getById(carrier.getId()) != null) {
            System.out.println("carrier with such ID already exist!");
        } else if (carrier.getName() == null || carrier.getName() == "") {
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
        if(name != null){
            return carrierRepo.getByName(name);
        }
        return new Carrier[0];
    }

    @Override
    public Carrier getById(Long id) {
        if(id != null){
            return carrierRepo.getById(id);
        }
        return null;
    }

    @Override
    public Carrier remove(Long id) {
        if(id != null){
            carrierRepo.remove(id);
        }
        return null;
    }
}
