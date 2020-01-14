package main.homework20191225.application.serviceholder;

import main.homework20191225.cargo.repo.CargoArrayRepoImpl;
import main.homework20191225.cargo.repo.CargoCollectionRepoImpl;
import main.homework20191225.cargo.service.CargoService;
import main.homework20191225.cargo.service.CargoServiceImpl;
import main.homework20191225.carrier.repo.CarrierArrayRepoImpl;
import main.homework20191225.carrier.repo.CarrierCollectionRepoImpl;
import main.homework20191225.carrier.service.CarrierService;
import main.homework20191225.carrier.service.CarrierServiceImpl;
import main.homework20191225.transportation.repo.TransportationArrayRepoImpl;
import main.homework20191225.transportation.repo.TransportationCollectionRepoImpl;
import main.homework20191225.transportation.service.TransportationService;
import main.homework20191225.transportation.service.TransportationServiceImpl;

public class ServiceHolder {

    private static ServiceHolder instance = null;

    private final CargoService cargoService;
    private final CarrierService carrierService;
    private final TransportationService transportationService;

    private ServiceHolder(main.homework20191225.application.serviceholder.StorageType storageType) {
        switch (storageType) {
            case ARRAY:
                cargoService = new CargoServiceImpl(new CargoArrayRepoImpl());
                carrierService = new CarrierServiceImpl(new CarrierArrayRepoImpl());
                transportationService = new TransportationServiceImpl(new TransportationArrayRepoImpl());
                break;
            default:
                cargoService = new CargoServiceImpl(new CargoCollectionRepoImpl());
                carrierService = new CarrierServiceImpl(new CarrierCollectionRepoImpl());
                transportationService = new TransportationServiceImpl(new TransportationCollectionRepoImpl());
        }
    }

    public static void initServiceHolder(StorageType storageType) {
        ServiceHolder.instance = new ServiceHolder(storageType);
    }

    public static ServiceHolder getInstance(){
        return instance;
    }

    public CargoService getCargoService(){
        return cargoService;
    }

    public CarrierService getCarrierService(){
        return carrierService;
    }

    public TransportationService getTransportationService(){
        return transportationService;
    }
}
