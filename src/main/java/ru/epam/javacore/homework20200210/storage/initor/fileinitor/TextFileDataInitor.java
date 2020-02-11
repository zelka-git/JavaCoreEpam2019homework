package ru.epam.javacore.homework20200210.storage.initor.fileinitor;

import ru.epam.javacore.homework20200210.cargo.domain.Cargo;
import ru.epam.javacore.homework20200210.cargo.domain.ClothesCargo;
import ru.epam.javacore.homework20200210.cargo.domain.ComputerCargo;
import ru.epam.javacore.homework20200210.cargo.domain.FoodCargo;
import ru.epam.javacore.homework20200210.carrier.domain.Carrier;
import ru.epam.javacore.homework20200210.carrier.domain.CarrierType;
import ru.epam.javacore.homework20200210.common.business.exception.checked.InitStorageException;
import ru.epam.javacore.homework20200210.common.solutions.utils.DataUtils;
import ru.epam.javacore.homework20200210.common.solutions.utils.FileUtils;
import ru.epam.javacore.homework20200210.storage.initor.fileinitor.BaseFileInitor;
import ru.epam.javacore.homework20200210.transportation.domain.Transportation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TextFileDataInitor extends BaseFileInitor {

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
            file = getFileWithInitData();
//            file = new File(path);
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
            if (file != null) {
                file.delete();
            }
        }
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils.createFileFromResource("init-data", "lesson12", path);
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
            result.setCargoRef(transportationData[0].trim());
            result.setCarrierRef(transportationData[1].trim());
            Transportation transportation = new Transportation();
            transportation.setDescription(transportationData[2].trim());
            transportation.setBillTo(transportationData[3].trim());
            transportation.setDate(DataUtils.valueOf(transportationData[4].trim()));
            result.setTransportation(transportation);
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


}
