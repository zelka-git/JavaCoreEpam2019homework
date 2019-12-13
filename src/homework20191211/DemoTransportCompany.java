package homework20191211;

import homework20191211.cargo.domain.Cargo;
import homework20191211.cargo.domain.CargoType;
import homework20191211.cargo.domain.Computer;
import homework20191211.cargo.domain.Food;
import homework20191211.carrier.domain.Carrier;
import homework20191211.carrier.domain.CarrierType;
import homework20191211.transportation.Transportation;

import java.util.Arrays;
import java.util.Date;

public class DemoTransportCompany {
    public static void main(String[] args) {

        Cargo bananas = new Food();
        bananas.setName("Bananas");
        bananas.setWeight(2000);
        bananas.setCargoType(CargoType.FOOD);

        Storage.addCargo(bananas);

        Cargo coats = new Cargo();
        coats.setName("Coats");
        coats.setWeight(500);
        coats.setCargoType(CargoType.CLOTHES);

        Storage.addCargo(coats);

        Cargo computers = new Computer();
        computers.setName("Computers");
        computers.setWeight(4000);
        computers.setCargoType(CargoType.COMPUTERS);

        Storage.addCargo(computers);


        Carrier company_1 = new Carrier();
        company_1.setName("Company 1");
        company_1.setAddress("Saint-Petersburg");
        company_1.setCarrierType(CarrierType.TRAIN);

        Storage.addCarrier(company_1);

        Carrier company_2 = new Carrier();
        company_2.setName("Company 2");
        company_2.setAddress("Moscow");
        company_2.setCarrierType(CarrierType.PLANE);

        Storage.addCarrier(company_2);

        Carrier company_3 = new Carrier();
        company_3.setName("Company 3");
        company_3.setAddress("Samara");
        company_3.setCarrierType(CarrierType.CAR);

        Storage.addCarrier(company_3);

        Transportation transportation_1 = new Transportation();
        transportation_1.setBillTo("Ivan I");
        transportation_1.setCargo(bananas);
        transportation_1.setCarrier(company_1);
        transportation_1.setDescription("transportation bananas by Company 1");
        transportation_1.setDate(new Date(2019, 11, 26));

        bananas.setTransportations(new Transportation[]{transportation_1});
        company_1.setTransportations(new Transportation[]{transportation_1});

        Storage.addTransportation(transportation_1);

        Transportation transportation_2 = new Transportation();
        transportation_2.setBillTo("Petr P");
        transportation_2.setCargo(coats);
        transportation_2.setCarrier(company_2);
        transportation_2.setDescription("transportation coats by Company 2");
        transportation_2.setDate(new Date(2020, 0, 27));

        coats.setTransportations(new Transportation[]{transportation_2});
        company_2.setTransportations(new Transportation[]{transportation_2});

        Storage.addTransportation(transportation_2);

        Transportation transportation_3 = new Transportation();
        transportation_3.setBillTo("Michal M");
        transportation_3.setCargo(computers);
        transportation_3.setCarrier(company_3);
        transportation_3.setDescription("transportation computers by Company 3");
        transportation_3.setDate(new Date(2020, 4,20));

        Storage.addTransportation(transportation_3);

        computers.setTransportations(new Transportation[]{transportation_3});
        company_3.setTransportations(new Transportation[]{transportation_3});

        Transportation transportation_4 = new Transportation();
        transportation_4.setBillTo("Petr P");
        transportation_4.setCargo(computers);
        transportation_4.setCarrier(company_2);
        transportation_4.setDescription("transportation computers by Company 2");
        transportation_4.setDate(new Date(2020, 5, 27));

        computers.setTransportations(new Transportation[]{transportation_3, transportation_4});
        company_2.setTransportations(new Transportation[]{transportation_2, transportation_4});

        Storage.addTransportation(transportation_4);

        Storage.printAllCargo();
        System.out.println("-------------------");
        Storage.printAllCarrier();
        System.out.println("-------------------");
        Storage.printAllTransportation();
        System.out.println("-------------------");
        System.out.println(Arrays.toString(Storage.getAllCargo()));
        System.out.println("-------------------");
        System.out.println(Storage.getCargoById(2));
        System.out.println("-------------------");
        System.out.println(Storage.getCarrierByName("Company 1"));

        System.out.println("-------------------");
        System.out.println(Storage.getCarrierByName("Compa"));

        System.out.println("-------------------");
        System.out.println(Storage.getCarrierByName(null));


    }
}
