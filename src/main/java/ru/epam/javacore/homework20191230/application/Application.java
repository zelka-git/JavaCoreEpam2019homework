package ru.epam.javacore.homework20191230.application;

import main.homework20191230.application.serviceholder.ServiceHolder;
import main.homework20191230.application.serviceholder.StorageType;
import main.homework20191230.cargo.service.CargoService;
import main.homework20191230.cargo.service.TypeSortCargo;
import main.homework20191230.carrier.service.CarrierService;
import main.homework20191230.common.business.exception.checked.InitStorageException;
import main.homework20191230.common.business.exception.checked.PrintStorageException;
import main.homework20191230.common.solutions.utils.ArrayUtils;
import main.homework20191230.storage.initor.InitStorageType;
import main.homework20191230.storage.initor.StorageInitor;
import main.homework20191230.storage.initor.StorageInitorFactory;
import main.homework20191230.storage.print.PrintToTextFile;
import main.homework20191230.transportation.service.TransportationService;

import java.io.IOException;

public class Application {

    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TransportationService transportationService;

    public static void main(String[] args) {
        ServiceHolder.initServiceHolder(StorageType.COLLECTION);
        ServiceHolder storage = ServiceHolder.getInstance();
        cargoService = storage.getCargoService();
        carrierService = storage.getCarrierService();
        transportationService = storage.getTransportationService();
        StorageInitor storageInitor;

        storageInitor = StorageInitorFactory.getStorageInitor(InitStorageType.XML_SAX_FILE);

        try {
            storageInitor.initStorage();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InitStorageException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        printStorage();

        PrintToTextFile printToTextFile = new PrintToTextFile();
        try {
            printToTextFile.printStorage();
        } catch (PrintStorageException e) {
            e.printStackTrace();
        }
//        testSortCargo();

//        System.out.println("\ntest delete by Id ");
////        System.out.println(cargoService.deleteById(null));
//        System.out.println(cargoService.deleteById(Long.valueOf(1)));
//        cargoService.printAll();

    }


    private static void testSortCargo() {
        System.out.println("Begin test sort:");
        System.out.println("Sort by weight");
        ArrayUtils.printArray(cargoService.getAllSortedItems(TypeSortCargo.WEIGHT));
        System.out.println("________________________");
        System.out.println("Sort by name");
        ArrayUtils.printArray(cargoService.getAllSortedItems(TypeSortCargo.NAME));
        System.out.println("________________________");
        System.out.println("Sort by weight name");
        ArrayUtils.printArray(cargoService.getAllSortedItems(TypeSortCargo.WEIGHT_NAME));
        System.out.println("________________________");
        System.out.println("Sort by name weight");
        ArrayUtils.printArray(cargoService.getAllSortedItems(TypeSortCargo.NAME_WEIGHT));
        System.out.println("________________________");
    }

    private static void printStorage() {
        cargoService.printAll();
        carrierService.printAll();
        transportationService.printAll();
    }


}
