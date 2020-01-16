package ru.epam.javacore.homework20191227.storage.initor;

import main.homework20191227.application.serviceholder.ServiceHolder;
import main.homework20191227.cargo.domain.*;
import main.homework20191227.carrier.domain.Carrier;
import main.homework20191227.carrier.domain.CarrierType;
import main.homework20191227.common.business.exception.checked.InitStorageException;
import main.homework20191227.common.solutions.utils.DataUtils;
import main.homework20191227.common.solutions.utils.FileUtils;
import main.homework20191227.transportation.domain.Transportation;

import java.io.*;
import java.text.ParseException;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class TextFileDataInitor implements StorageInitor {

    private static final String CARGO_SECTION_LABEL_IN_FILE = "---CARGOS---";
    private static final String CARRIER_SECTION_LABEL_IN_FILE = "---CARRIERS---";
    private static final String TRANSPORTATION_SECTION_LABEL_IN_FILE = "---TRANSPORTATIONS---";

    private final String path;

    public TextFileDataInitor(String path) {
        this.path = path;
    }

    @Override
    public void initStorage() throws InitStorageException {
        File file = null;
        try {
//            file = getFileWithInitData();
            file = new File(path);
            Map<String, Cargo> cargoMap = parseCargosFromFile(file);
            Map<String, Carrier> carrierMap = parseCarriersFromFile(file);
            List<ParsedTransportation> transportations = parseTransportationsFromFile(file);
            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

            persistCargos(cargoMap.values());
            persistCarriers(carrierMap.values());
            List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
            persistTransportations(transportationList);

        } catch (Exception e) {
            e.printStackTrace();
            throw new InitStorageException(e.getMessage());
        } finally {
//            if (file != null) {
//                file.delete();
//            }
        }
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils.createFileFromResource("input", "lesson11", path);
    }

    private void persistTransportations(List<Transportation> transportations) {
        for (Transportation transportation : transportations) {
            ServiceHolder.getInstance().getTransportationService().add(transportation);
        }
    }

    private List<Transportation> getTransportationsFromParsedObject(List<ParsedTransportation> transportations) {
        List<Transportation> result = new ArrayList<>();
        for (ParsedTransportation transportation : transportations) {
            result.add(transportation.transportation);
        }

        return result;
    }

    private void persistCarriers(Collection<Carrier> carriers) {
        for (Carrier carrier : carriers) {
            ServiceHolder.getInstance().getCarrierService().add(carrier);
        }
    }

    private void persistCargos(Collection<Cargo> cargos) {
        for (Cargo cargo : cargos) {
            ServiceHolder.getInstance().getCargoService().add(cargo);
        }
    }

    private void setReferencesBetweenEntities(Map<String, Cargo> cargoMap,
                                              Map<String, Carrier> carrierMap,
                                              List<ParsedTransportation> parsedTransportations) {
        for (ParsedTransportation parsedTransportation : parsedTransportations) {
            Cargo cargo = cargoMap.get(parsedTransportation.cargoRef);
            Carrier carrier = carrierMap.get(parsedTransportation.carrierRef);
            Transportation transportation = parsedTransportation.transportation;

            if (cargo != null) {
                List<Transportation> transportations =
                        cargo.getTransportations() == null ? new ArrayList<>() : cargo.getTransportations();
                transportations.add(transportation);
                transportation.setCargo(cargo);
                cargo.setTransportations(transportations);
            }

            if (carrier != null) {
                List<Transportation> transportations =
                        carrier.getTransportations() == null ? new ArrayList<>() : carrier.getTransportations();
                transportations.add(transportation);
                transportation.setCarrier(carrier);
                carrier.setTransportations(transportations);
            }
        }
    }

    private List<ParsedTransportation> parseTransportationsFromFile(File file) throws IOException, ParseException {
        List<ParsedTransportation> list = new ArrayList<>();
        List<String> fileData = readSectionInFile(file,
                TRANSPORTATION_SECTION_LABEL_IN_FILE);
        for (String transportationString : fileData) {
            list.add(parseTransportationFromString(transportationString));
        }
        return list;
    }

    private ParsedTransportation parseTransportationFromString(String transportationString) throws ParseException {
        String[] transportationData = transportationString.split("\\|");
        ParsedTransportation result = null;
        if (transportationData.length > 0) {
            result = new ParsedTransportation();
            result.cargoRef = transportationData[0].trim();
            result.carrierRef = transportationData[1].trim();
            Transportation transportation = new Transportation();
            transportation.setDescription(transportationData[2].trim());
            transportation.setBillTo(transportationData[3].trim());
            transportation.setDate(DataUtils.valueOf(transportationData[4].trim()));
            result.transportation = transportation;
        }
        return result;
    }

    private Map<String, Carrier> parseCarriersFromFile(File file) throws IOException {
        Map<String, Carrier> carrierMap = new LinkedHashMap<>();
        List<String> fileData = readSectionInFile(file, CARRIER_SECTION_LABEL_IN_FILE);
        for (String carrierString : fileData) {
            SimpleEntry<String, Carrier> carrierEntry = parseCarrierFromString(carrierString);
            if (carrierEntry != null) {
                carrierMap.put(carrierEntry.getKey(), carrierEntry.getValue());
            }
        }
        return carrierMap;
    }

    private SimpleEntry<String, Carrier> parseCarrierFromString(String carrierString) {
        String[] carrierData = carrierString.split("\\|");
        if (carrierData.length > 0) {
            String id = carrierData[0].trim();

            Carrier carrier = new Carrier();
            carrier.setName(carrierData[1].trim());
            carrier.setAddress(carrierData[2].trim());
            carrier.setCarrierType(CarrierType.valueOf(carrierData[3].trim()));

            return new SimpleEntry<>(id, carrier);
        }
        return null;
    }

    private Map<String, Cargo> parseCargosFromFile(File file) throws IOException, ParseException, InitStorageException {
        Map<String, Cargo> cargoMap = new LinkedHashMap<>();
        List<String> fileData = readSectionInFile(file, CARGO_SECTION_LABEL_IN_FILE);
        for (String cargoString : fileData) {
            SimpleEntry<String, Cargo> cargoEntry = parseCargoFromString(cargoString);
            if (cargoEntry != null) {
                cargoMap.put(cargoEntry.getKey(), cargoEntry.getValue());
            }
        }
        return cargoMap;
    }

    private SimpleEntry<String, Cargo> parseCargoFromString(String cargoString) throws ParseException, InitStorageException {
        String[] cargoData = cargoString.split("\\|");
        if (cargoData.length > 0) {
            String id = cargoData[0].trim();
            String name = cargoData[1].trim();
            int weight = Integer.parseInt(cargoData[2].trim());
            String cargoType = cargoData[3].trim();

            Cargo cargo;
            switch (cargoType) {
                case "CLOTHES":
                    ClothesCargo clothesCargo = new ClothesCargo();
                    clothesCargo.setSize(cargoData[4].trim());
                    clothesCargo.setMaterial(cargoData[5].trim());
                    cargo = clothesCargo;
                    break;
                case "COMPUTERS":
                    ComputerCargo computerCargo = new ComputerCargo();
                    computerCargo.setDescription(cargoData[6].trim());
                    cargo = computerCargo;
                    break;
                case "FOOD":
                    FoodCargo foodCargo = new FoodCargo();
                    foodCargo.setExpirationDate(DataUtils.valueOf(cargoData[7].trim()));
                    foodCargo.setStoreTemperature(Integer.parseInt(cargoData[8].trim()));
                    cargo = foodCargo;
                    break;
                default:
                    throw new InitStorageException("wrong value of CargoType");
            }
            cargo.setName(name);
            cargo.setWeight(weight);

            return new SimpleEntry<>(id, cargo);
        }
        return null;
    }

    private List<String> readSectionInFile(File file, String SectionLabelInFile) throws IOException {
        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (SectionLabelInFile.equals(line)) {
                    while (!(line = reader.readLine()).isEmpty()) {
                        list.add(line);
                    }
                }
            }
        }
        return list;
    }

    private static class ParsedTransportation {
        private String cargoRef;
        private String carrierRef;
        private Transportation transportation;
    }


}
