package main.homework20191227.application;

import main.homework20191227.application.serviceholder.ServiceHolder;
import main.homework20191227.application.serviceholder.StorageType;
import main.homework20191227.cargo.service.CargoService;
import main.homework20191227.cargo.service.TypeSortCargo;
import main.homework20191227.carrier.service.CarrierService;
import main.homework20191227.common.business.exception.checked.InitStorageException;
import main.homework20191227.common.business.exception.checked.PrintStorageException;
import main.homework20191227.common.solutions.utils.ArrayUtils;
import main.homework20191227.storage.initor.TextFileDataInitor;
import main.homework20191227.storage.initor.InMemoryStorageInitor;
import main.homework20191227.storage.initor.StorageInitor;
import main.homework20191227.storage.initor.XmlFileDataInitor;
import main.homework20191227.storage.print.PrintToTextFile;
import main.homework20191227.transportation.service.TransportationService;

import java.io.IOException;

public class Application {

    private static CargoService cargoService;
    private static CarrierService carrierService;
    private static TransportationService transportationService;

    private static final int AMOUNT_OF_ELEMENTS = 6;

    public static void main(String[] args) {
        ServiceHolder.initServiceHolder(StorageType.ARRAY);
        ServiceHolder storage = ServiceHolder.getInstance();
        cargoService = storage.getCargoService();
        carrierService = storage.getCarrierService();
        transportationService = storage.getTransportationService();
//        String path = "recources/main.homework20191227/input.txt";
        String path = "resources/main.homework20191227/inputData.txt";
        String path1 = "resources/main.homework20191227/input_xml.xml";
        String typeInitor = "XmlFile"; //Memory || TextFile || XmlFile
        StorageInitor storageInitor;

//        File file = new File(path1);
//        System.out.println(file.exists());

        if ("Memory".equals(typeInitor)) {
            storageInitor = new InMemoryStorageInitor();
        } else if("TextFile".equals(typeInitor)){
            storageInitor = new TextFileDataInitor(path);
        }else{
            storageInitor = new XmlFileDataInitor(path1);
        }


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
