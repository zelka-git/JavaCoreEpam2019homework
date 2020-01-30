package ru.epam.javacore.homework20200129.application.serviceholder;

import ru.epam.javacore.homework20200129.application.serviceholder.StorageType;
import ru.epam.javacore.homework20200129.cargo.repo.impl.CargoArrayRepoImpl;
import ru.epam.javacore.homework20200129.cargo.repo.impl.CargoCollectionRepoImpl;
import ru.epam.javacore.homework20200129.cargo.service.CargoService;
import ru.epam.javacore.homework20200129.cargo.service.CargoServiceImpl;
import ru.epam.javacore.homework20200129.carrier.repo.impl.CarrierArrayRepoImpl;
import ru.epam.javacore.homework20200129.carrier.repo.impl.CarrierCollectionRepoImpl;
import ru.epam.javacore.homework20200129.carrier.service.CarrierService;
import ru.epam.javacore.homework20200129.carrier.service.CarrierServiceImpl;
import ru.epam.javacore.homework20200129.transportation.repo.impl.TransportationArrayRepoImpl;
import ru.epam.javacore.homework20200129.transportation.repo.impl.TransportationCollectionRepoImpl;
import ru.epam.javacore.homework20200129.transportation.service.TransportationService;
import ru.epam.javacore.homework20200129.transportation.service.TransportationServiceImpl;

import java.io.Serializable;

public class ServiceHolder implements Serializable {

    private static ServiceHolder instance = null;

    private final CargoService cargoService;
    private final CarrierService carrierService;
    private final TransportationService transportationService;

    private ServiceHolder(ru.epam.javacore.homework20200129.application.serviceholder.StorageType storageType) {
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

    private Object readResolve() {
        return instance;
    }
}
