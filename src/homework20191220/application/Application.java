package homework20191220.application;

import homework20191220.application.serviceholder.ServiceHolder;
import homework20191220.application.serviceholder.StorageType;
import homework20191220.cargo.domain.Cargo;
import homework20191220.cargo.domain.ClothesCargo;
import homework20191220.cargo.domain.ComputerCargo;
import homework20191220.cargo.domain.FoodCargo;
import homework20191220.cargo.service.CargoService;
import homework20191220.cargo.service.TypeSortCargo;
import homework20191220.carrier.domain.Carrier;
import homework20191220.carrier.service.CarrierService;
import homework20191220.common.utils.ArrayUtils;
import homework20191220.transportation.domain.Transportation;
import homework20191220.transportation.service.TransportationService;

import java.util.Date;

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

        initCargos();
        initCarriers();
        initTransportatons();
        printStorage();
        testSortCargo();

        System.out.println("\ntest delete by Id ");
//        System.out.println(cargoService.deleteById(null));
        System.out.println(cargoService.deleteById(Long.valueOf(1)));
        cargoService.printAll();

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
        carrierService.printAll();
        cargoService.printAll();
        transportationService.printAll();
    }

    private static void initCargos() {
        System.out.println("ADD CARGOS");
        for (int i = 0; i < AMOUNT_OF_ELEMENTS / 3; i++) {
            cargoService.add(createClothesCargo(i));
        }
        for (int i = 0; i < AMOUNT_OF_ELEMENTS / 3; i++) {
            cargoService.add(createComputersCargo(i));
        }
        for (int i = 0; i < AMOUNT_OF_ELEMENTS / 3; i++) {
            cargoService.add(createFoodCargo(i));
        }
        System.out.println("----------------");
    }

    private static void initCarriers() {
        System.out.println("ADD CARRIERS");
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            carrierService.add(createCarrier(i));
        }
        System.out.println("----------------");
    }

    private static void initTransportatons() {
        System.out.println("ADD TRANSPORTATIONS");
        for (int i = 0; i < AMOUNT_OF_ELEMENTS; i++) {
            transportationService.add(createTransportation(i + 1, i + 1 + AMOUNT_OF_ELEMENTS,
                    cargoService, carrierService));
        }
        System.out.println("----------------");
    }

    private static Transportation createTransportation(long cargoId, long carrierId,
                                                       CargoService cargoRepo, CarrierService carrierRepo) {
        Transportation transportation = new Transportation();
        transportation.setCargo(cargoRepo.getById(cargoId));
        transportation.setCarrier(carrierRepo.getById(carrierId));
        transportation.setDescription("descripton transportation" + (cargoId + carrierId));
        transportation.setBillTo("BillTo" + (cargoId + carrierId));
        transportation.setDate(new Date());
        return transportation;
    }


    private static ClothesCargo createClothesCargo(int i) {
        ClothesCargo cargo = new ClothesCargo();
        cargo.setSize("Clothes size " + i);
        cargo.setName("Clothes name ");
        cargo.setWeight(40 - i);
        return cargo;
    }

    private static Cargo createComputersCargo(int i) {
        ComputerCargo cargo = new ComputerCargo();
        cargo.setDescription("description computer" + i);
        cargo.setName("Computer name " + (5 - i));
        cargo.setWeight(50);
        return cargo;
    }

    private static Cargo createFoodCargo(int i) {
        FoodCargo cargo = new FoodCargo();
        cargo.setWeight(33 + i);
        cargo.setName("Food name " + i);
        return cargo;
    }

    private static Carrier createCarrier(int i) {
        Carrier carrier = new Carrier();
        carrier.setName("Carrier name " + i);
        carrier.setAddress("Adress + " + i);
        return carrier;
    }

}
