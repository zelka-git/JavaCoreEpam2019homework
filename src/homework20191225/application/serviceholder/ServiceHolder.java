package homework20191225.application.serviceholder;

import homework20191225.application.serviceholder.StorageType;
import homework20191225.cargo.repo.CargoArrayRepoImpl;
import homework20191225.cargo.repo.CargoCollectionRepoImpl;
import homework20191225.cargo.service.CargoService;
import homework20191225.cargo.service.CargoServiceImpl;
import homework20191225.carrier.repo.CarrierArrayRepoImpl;
import homework20191225.carrier.repo.CarrierCollectionRepoImpl;
import homework20191225.carrier.service.CarrierService;
import homework20191225.carrier.service.CarrierServiceImpl;
import homework20191225.transportation.repo.TransportationArrayRepoImpl;
import homework20191225.transportation.repo.TransportationCollectionRepoImpl;
import homework20191225.transportation.service.TransportationService;
import homework20191225.transportation.service.TransportationServiceImpl;

public class ServiceHolder {

    private static ServiceHolder instance = null;

    private final CargoService cargoService;
    private final CarrierService carrierService;
    private final TransportationService transportationService;

    private ServiceHolder(homework20191225.application.serviceholder.StorageType storageType) {
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
