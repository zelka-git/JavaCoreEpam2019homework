package ru.epam.javacore.homework20200127.application;

import ru.epam.javacore.homework20200127.application.serviceholder.ServiceHolder;
import ru.epam.javacore.homework20200127.application.serviceholder.StorageType;
import ru.epam.javacore.homework20200127.cargo.domain.CargoField;
import ru.epam.javacore.homework20200127.cargo.search.CargoSearchCondition;
import ru.epam.javacore.homework20200127.cargo.service.CargoService;
import ru.epam.javacore.homework20200127.cargo.service.TypeSortCargo;
import ru.epam.javacore.homework20200127.carrier.service.CarrierService;
import ru.epam.javacore.homework20200127.common.solutions.search.OrderType;
import ru.epam.javacore.homework20200127.common.solutions.utils.ArrayUtils;
import ru.epam.javacore.homework20200127.reporting.ReportDefaultService;
import ru.epam.javacore.homework20200127.reporting.ReportService;
import ru.epam.javacore.homework20200127.storage.initor.InitStorageType;
import ru.epam.javacore.homework20200127.storage.initor.StorageInitor;
import ru.epam.javacore.homework20200127.storage.initor.StorageInitorFactory;
import ru.epam.javacore.homework20200127.transportation.service.TransportationService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import static java.util.Collections.singletonList;
import static ru.epam.javacore.homework20200127.cargo.domain.CargoField.NAME;
import static ru.epam.javacore.homework20200127.cargo.domain.CargoField.WEIGHT;
import static ru.epam.javacore.homework20200127.common.solutions.search.OrderType.ASC;
import static ru.epam.javacore.homework20200127.common.solutions.search.OrderType.DESC;

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
            storageInitor = StorageInitorFactory.getStorageInitor(InitStorageType.MULTI_THREAD);
            storageInitor.initStorage();

            printStorage();

            demoSearchOperations();
            demoTestSortCargo();// my sort
            demoSortOperations();//teacher sort
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
    private static void demoSortOperations() {
        demoCargoSorting(singletonList(NAME), ASC);
        demoCargoSorting(singletonList(NAME), DESC);

        demoCargoSorting(singletonList(WEIGHT), ASC);
        demoCargoSorting(singletonList(WEIGHT), DESC);

        demoCargoSorting(Arrays.asList(NAME, WEIGHT), ASC);
        demoCargoSorting(Arrays.asList(NAME, WEIGHT), DESC);
    }
    private static void demoCargoSorting(Collection<CargoField> sortFields, OrderType orderType) {
        CargoSearchCondition cargoSearchCondition = new CargoSearchCondition();
        cargoSearchCondition.setOrderType(orderType);
        cargoSearchCondition.setSortFields(new LinkedHashSet<>(sortFields));
        System.out.println(
                "---------Sorting '" + getOrderingConditionsAsString(cargoSearchCondition) + "'------");
        cargoService.search(cargoSearchCondition);
        cargoService.printAll();
        System.out.println();
    }
    private static String getOrderingConditionsAsString(CargoSearchCondition condition) {
        StringBuilder result = new StringBuilder();
        result.append(" ORDER BY ");

        Iterator<CargoField> iter = condition.getSortFields().iterator();
        while (iter.hasNext()) {
            CargoField fld = iter.next();
            result.append(fld);

            if (iter.hasNext()) {
                result.append(",");
            }
        }

        result.append(" ").append(condition.getOrderType());

        return result.toString();
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
