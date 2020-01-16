package ru.epam.javacore.homework20191225.storage.initor;

import main.homework20191225.application.serviceholder.ServiceHolder;
import main.homework20191225.cargo.domain.Cargo;
import main.homework20191225.cargo.domain.ClothesCargo;
import main.homework20191225.cargo.domain.ComputerCargo;
import main.homework20191225.cargo.domain.FoodCargo;
import main.homework20191225.cargo.service.CargoService;
import main.homework20191225.carrier.domain.Carrier;
import main.homework20191225.carrier.domain.CarrierType;
import main.homework20191225.carrier.service.CarrierService;
import main.homework20191225.transportation.domain.Transportation;
import main.homework20191225.transportation.service.TransportationService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class FileStorageInitor implements StorageInitor {

    private final CarrierService carrierService;
    private final CargoService cargoService;
    private final TransportationService transportationService;

    private final String path;

    private static final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");

    public FileStorageInitor(String path) {
        carrierService = ServiceHolder.getInstance().getCarrierService();
        cargoService = ServiceHolder.getInstance().getCargoService();
        transportationService = ServiceHolder.getInstance().getTransportationService();
        this.path = path;
    }

    @Override
    public void initStorage() {
        File file = new File(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("CARGOS BEGINS")) {
                    while (!(line = reader.readLine()).contains("CARGOS END")) {
                        initCargoFromString(line);
                    }
                } else if (line.contains("CARRIERS BEGINS")) {
                    while (!(line = reader.readLine()).contains("CARRIERS END")) {
                        initCarrierFromString(line);
                    }
                } else if (line.contains("TRANSPORTATIONS BEGINS")) {
                    while (!(line = reader.readLine()).contains("TRANSPORTATIONS END")) {
                        initTransportationFromString(line);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTransportationFromString(String line) throws ParseException {
        List<String> items = transformStringToArray(line);
        if (items.size() < 3) {
            System.out.println("this transportation can't be added!!");
        } else {
            transportationService.add(createTransportation(items));
        }
    }

    private Transportation createTransportation(List<String> items) throws ParseException {
        Transportation transportation = new Transportation();
        transportation.setCargo(cargoService.getById(Long.parseLong(items.get(0))));
        transportation.setCarrier(carrierService.getById(Long.parseLong(items.get(1))));
        transportation.setDescription(items.get(2));
        transportation.setBillTo(items.get(3));
        transportation.setDate(format.parse(items.get(4)));
        return transportation;
    }

    private void initCarrierFromString(String line) throws RuntimeException {
        List<String> items = transformStringToArray(line);
        if (items.size() < 3) {
            throw new RuntimeException("this carrier can't be added!!");
        } else {
            carrierService.add(createCarrier(items));
        }
    }

    private Carrier createCarrier(List<String> items){
        Carrier carrier = new Carrier();
        carrier.setName(items.get(0));
        carrier.setAddress(items.get(1));
        String carrType = items.get(2);
        carrier.setCarrierType(CarrierType.valueOf(carrType));
        return carrier;
    }

    private void initCargoFromString(String line) throws RuntimeException, ParseException {
        List<String> items = transformStringToArray(line);
        if (items.size() < 3) {
            throw new RuntimeException("this cargo can't be added!!");
        } else {
            String cargoType = items.get(2);
            switch (cargoType) {
                case "CLOTHES":
                    cargoService.add(createClothesCargo(items));
                    break;
                case "COMPUTERS":
                    cargoService.add(createComputersCargo(items));
                    break;
                case "FOOD":
                    cargoService.add(createFoodCargo(items));
                    break;
                default:
                    System.out.println("such type of cargo don'e exist! " + cargoType);
            }
        }
    }

    private Cargo createFoodCargo(List<String> items) throws ParseException {
        FoodCargo cargo = new FoodCargo();
        cargo.setName(items.get(0));
        cargo.setWeight(Integer.parseInt(items.get(1)));
        String date = items.get(6);
        if (!"-".equals(date)) {
            cargo.setExpirationDate(format.parse(items.get(6)));
        }
        String storeTemperature = items.get(7);
        if (!"-".equals(storeTemperature)) {
            cargo.setStoreTemperature(Integer.parseInt(storeTemperature));
        }

        return cargo;
    }

    private Cargo createComputersCargo(List<String> items) {
        ComputerCargo cargo = new ComputerCargo();
        cargo.setName(items.get(0));
        cargo.setWeight(Integer.parseInt(items.get(1)));
        String description = items.get(5);
        if (!"-".equals(description)) {
            cargo.setDescription(description);
        }
        return cargo;
    }

    private Cargo createClothesCargo(List<String> items) {
        ClothesCargo cargo = new ClothesCargo();
        cargo.setName(items.get(0));
        cargo.setWeight(Integer.parseInt(items.get(1)));
        String size = items.get(3);
        if (!"-".equals(size))
            cargo.setSize(size);
        String materials = items.get(4);
        if (!"-".equals(materials))
            cargo.setMaterial(materials);
        return cargo;
    }

    private List<String> transformStringToArray(String string) {
        List<String> list;
        String[] strings = string.split("\\|");
        for (int i = 0; i < strings.length; i++) {
            strings[i] = strings[i].trim();
        }
        list = Arrays.asList(strings);
        return list;
    }
}
