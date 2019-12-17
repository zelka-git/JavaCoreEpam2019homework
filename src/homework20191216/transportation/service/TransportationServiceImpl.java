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

    private TransportationRepo transportationRepo;
    private CargoRepo cargoRepo;
    private CarrierRepo carrierRepo;

    TransportationServiceImpl(TransportationRepo transportationRepo) {
        this.transportationRepo = transportationRepo;
        if (transportationRepo.getClass() == TransportationArrayRepoImpl.class) {
            cargoRepo = new CargoArrayRepoImpl();
            carrierRepo = new CarrierArrayRepoImpl();
        } else if (transportationRepo.getClass() == TransportationCollectionRepoImpl.class) {
            cargoRepo = new CargoCollectionRepoImpl();
            carrierRepo = new CarrierCollectionRepoImpl();
        } else {
            System.out.println("fail transportationRepo type");
            return;
        }
    }

    @Override
    public void add(Transportation transportation) {


        System.out.println("Begin to add transportation!");
        if (transportation.getId() == null) {
            System.out.println("transportation must have a ID!");
        } else if (transportationRepo.getById(transportation.getId()) != null) {
            System.out.println("transportation with such ID already exist!");
        } else if (transportation.getCargo() == null) {
            System.out.println("transportation must have a cargo!");
        } else if (cargoRepo.getById(transportation.getCargo().getId()) == null) {
            System.out.println("such cargo is not in storage");
        } else if (transportation.getCarrier() == null) {
            System.out.println("transportation must have a carrier!");
        } else if (carrierRepo.getById(transportation.getCarrier().getId()) == null) {
            System.out.println("such carrier is not in storage");
        } else if (transportation.getDescription() == null) {
            System.out.println("transportation must have a description!");
        } else if (transportation.getBillTo() == null) {
            System.out.println("transportation must have a BillTo!");
        } else if (transportation.getDate() == null) {
            System.out.println("transportation must have a date!");
        } else {
            transportationRepo.add(transportation);
            cargoRepo.getById(transportation.getCargo().getId()).setTransportations(
                    new Transportation[]{transportation}
            );
            carrierRepo.getById(transportation.getCarrier().getId()).setTransportations(
                    new Transportation[]{transportation}
            );
        }
    }

    @Override
    public Transportation[] getAll() {
        return transportationRepo.getAll();
    }

    @Override
    public Transportation getById(Long id) {
        if (id != null) {
            transportationRepo.getById(id);
        }
        return null;
    }

    @Override
    public Transportation remove(Long id) {
        if (id != null) {
            return transportationRepo.getById(id);
        }
        return null;
    }
}
