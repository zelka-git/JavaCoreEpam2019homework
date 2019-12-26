package homework20191225.application;

import homework20191225.application.serviceholder.ServiceHolder;
import homework20191225.application.serviceholder.StorageType;
import homework20191225.cargo.service.CargoService;
import homework20191225.cargo.service.TypeSortCargo;
import homework20191225.carrier.service.CarrierService;
import homework20191225.common.solutions.utils.ArrayUtils;
import homework20191225.storage.initor.FileStorageInitor;
import homework20191225.storage.initor.InMemoryStorageInitor;
import homework20191225.storage.initor.StorageInitor;
import homework20191225.transportation.service.TransportationService;

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

        String path = "C:\\Anzhelika\\projects\\ideaProjects\\JavaCore\\src\\homework20191225\\inputData.txt";
        String typeInitor = "File"; //Memory || File
        StorageInitor storageInitor;

        if ("Memory".equals(typeInitor)) {
            storageInitor = new InMemoryStorageInitor();
        } else {
            storageInitor = new FileStorageInitor(path);
        }

        storageInitor.initStorage();


        printStorage();
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
