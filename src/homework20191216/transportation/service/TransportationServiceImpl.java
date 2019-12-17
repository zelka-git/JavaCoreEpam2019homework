package homework20191216.transportation.service;

import homework20191216.cargo.repo.CargoArrayRepoImpl;
import homework20191216.cargo.repo.CargoCollectionRepoImpl;
import homework20191216.cargo.repo.CargoRepo;
import homework20191216.carrier.repo.CarrierArrayRepoImpl;
import homework20191216.carrier.repo.CarrierCollectionRepoImpl;
import homework20191216.carrier.repo.CarrierRepo;
import homework20191216.transportation.domain.Transportation;
import homework20191216.transportation.repo.TransportationArrayRepoImpl;
import homework20191216.transportation.repo.TransportationCollectionRepoImpl;
import homework20191216.transportation.repo.TransportationRepo;

public class TransportationServiceImpl implements TransportationService {

    @Override
    public void add(Transportation transportation, TransportationRepo typeStorage) {
        CargoRepo cargoStorage;
        CarrierRepo carrierStorage;
        if (typeStorage.getClass() == TransportationArrayRepoImpl.class) {
            cargoStorage = new CargoArrayRepoImpl();
            carrierStorage = new CarrierArrayRepoImpl();
        } else if (typeStorage.getClass() == TransportationCollectionRepoImpl.class) {
            cargoStorage = new CargoCollectionRepoImpl();
            carrierStorage = new CarrierCollectionRepoImpl();
        } else {
            System.out.println("fail typeStorage");
            return;
        }

        System.out.println("Begin to add transportation!");
        if (transportation.getId() == null) {
            System.out.println("transportation must have a ID!");
        } else if (typeStorage.getById(transportation.getId()) != null) {
            System.out.println("transportation with such ID already exist!");
        } else if (transportation.getCargo() == null) {
            System.out.println("transportation must have a cargo!");
        } else if (cargoStorage.getById(transportation.getCargo().getId()) == null){
            System.out.println("such cargo is not in storage");
        }else if(transportation.getCarrier() == null){
            System.out.println("transportation must have a carrier!");
        }else if(carrierStorage.getById(transportation.getCarrier().getId()) == null){
            System.out.println("such carrier is not in storage");
        }else if(transportation.getDescription() == null){
            System.out.println("transportation must have a description!");
        }else if(transportation.getBillTo() == null){
            System.out.println("transportation must have a BillTo!");
        }else if(transportation.getDate() == null){
            System.out.println("transportation must have a date!");
        }else{
            typeStorage.add(transportation);
            cargoStorage.getById(transportation.getCargo().getId()).setTransportations(
                    new Transportation[]{transportation}
            );
            carrierStorage.getById(transportation.getCarrier().getId()).setTransportations(
                    new Transportation[]{transportation}
            );
        }
    }

    @Override
    public Transportation[] getAll(TransportationRepo typeStorage) {
        return typeStorage.getAll();
    }

    @Override
    public Transportation getById(Long id, TransportationRepo typeStorage) {
        if(id != null){
            typeStorage.getById(id);
            }
        return null;
    }

    @Override
    public Transportation remove(Long id, TransportationRepo typeStorage) {
        if(id != null){
            return typeStorage.getById(id);
        }
        return null;
    }
}
