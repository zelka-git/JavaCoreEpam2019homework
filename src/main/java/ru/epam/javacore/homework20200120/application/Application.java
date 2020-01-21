package ru.epam.javacore.homework20200120.application;

import ru.epam.javacore.homework20200120.application.serviceholder.ServiceHolder;
import ru.epam.javacore.homework20200120.application.serviceholder.StorageType;
import ru.epam.javacore.homework20200120.cargo.service.CargoService;
import ru.epam.javacore.homework20200120.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20200120.carrier.service.CarrierService;
import ru.epam.javacore.homework20200120.common.solutions.utils.ArrayUtils;
import ru.epam.javacore.homework20200120.reporting.ReportDefaultService;
import ru.epam.javacore.homework20200120.reporting.ReportService;
import ru.epam.javacore.homework20200120.storage.initor.InitStorageType;
import ru.epam.javacore.homework20200120.storage.initor.StorageInitor;
import ru.epam.javacore.homework20200120.storage.initor.StorageInitorFactory;
import ru.epam.javacore.homework20200120.transportation.service.TransportationService;

import java.util.Collection;

public class Application {

    private static final String SEPARATOR = "--------------";
    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TransportationService transportationService;

    public static void main(String[] args) {
        try {


            ServiceHolder.initServiceHolder(StorageType.COLLECTION);
            ServiceHolder storage = ServiceHolder.getInstance();
            cargoService = storage.getCargoService();
            carrierService = storage.getCarrierService();
            transportationService = storage.getTransportationService();

            StorageInitor storageInitor;
            storageInitor = StorageInitorFactory.getStorageInitor(InitStorageType.XML_SAX_FILE_MULTITHREAD);
            storageInitor.initStorage();

            printStorage();

            demoSearchOperations();
            demoTestSortCargo();
            demoTestException();


            ReportService reportService = new ReportDefaultService(
                    cargoService, carrierService, transportationService
            );
            reportService.exportData();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private static void printStorage() {
        cargoService.printAll();
        carrierService.printAll();
        transportationService.printAll();
    }

    private static void demoSearchOperations() {
        System.out.println();
        System.out.println("SEARCH CARGO BY ID = 1");
        System.out.println(cargoService.getById(1L));
        printSeparator();

        System.out.println("SEARCH CARRIER BY ID = 6");
        System.out.println(carrierService.getById(6L));
        printSeparator();

        System.out.println("SEARCH CARGOES BY NAME = 'Banana'");
        printCollection(cargoService.getByName("Banana"));
        printSeparator();

        System.out.println("SEARCH CARRIERS BY NAME = 'Slow delivery'");
        printCollection(carrierService.getByName("Slow delivery"));
    }

    private static void printSeparator() {
        System.out.println(SEPARATOR);
    }

    private static void printCollection(Collection<?> collection) {
        for (Object obj : collection) {
            System.out.println(obj.toString());
        }
    }


    private static void demoTestSortCargo() {
        System.out.println("\nBegin test sort:");
        System.out.println("Sort by weight");
        ArrayUtils.printArray(cargoService.getAllSortedItems(TypeSortCargo.WEIGHT));
        printSeparator();
        System.out.println("Sort by name");
        ArrayUtils.printArray(cargoService.getAllSortedItems(TypeSortCargo.NAME));
        printSeparator();
        System.out.println("Sort by weight name");
        ArrayUtils.printArray(cargoService.getAllSortedItems(TypeSortCargo.WEIGHT_NAME));
        printSeparator();
        System.out.println("Sort by name weight");
        ArrayUtils.printArray(cargoService.getAllSortedItems(TypeSortCargo.NAME_WEIGHT));
        printSeparator();
    }

    private static void demoTestException() {
        System.out.println("\nTEST DELETE BY Id ");
        System.out.println("Delete by Id = null");
        System.out.println("result = " + cargoService.deleteById(null));
        printSeparator();
        try {
            System.out.println("Delete by Id = 1");
            System.out.println("result = " + cargoService.deleteById(1L));
            printSeparator();
        }catch (Exception e){
            System.out.println("Exception: can't delete cargo");
            System.out.println(e.getMessage());
        }
    }


}
