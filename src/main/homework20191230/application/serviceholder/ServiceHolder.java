package main.homework20191230.application.serviceholder;

import main.homework20191230.cargo.repo.CargoArrayRepoImpl;
import main.homework20191230.cargo.repo.CargoCollectionRepoImpl;
import main.homework20191230.cargo.service.CargoService;
import main.homework20191230.cargo.service.CargoServiceImpl;
import main.homework20191230.carrier.repo.CarrierArrayRepoImpl;
import main.homework20191230.carrier.repo.CarrierCollectionRepoImpl;
import main.homework20191230.carrier.service.CarrierService;
import main.homework20191230.carrier.service.CarrierServiceImpl;
import main.homework20191230.transportation.repo.TransportationArrayRepoImpl;
import main.homework20191230.transportation.repo.TransportationCollectionRepoImpl;
import main.homework20191230.transportation.service.TransportationService;
import main.homework20191230.transportation.service.TransportationServiceImpl;

public class ServiceHolder {

    private static ServiceHolder instance = null;

    private final CargoService cargoService;
    private final CarrierService carrierService;
    private final TransportationService transportationService;

    private ServiceHolder(StorageType storageType) {
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
