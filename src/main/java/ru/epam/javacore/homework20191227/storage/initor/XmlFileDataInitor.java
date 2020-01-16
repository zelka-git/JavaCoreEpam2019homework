package ru.epam.javacore.homework20191227.storage.initor;

import main.homework20191227.application.serviceholder.ServiceHolder;
import main.homework20191227.cargo.domain.Cargo;
import main.homework20191227.cargo.domain.ClothesCargo;
import main.homework20191227.cargo.domain.ComputerCargo;
import main.homework20191227.cargo.domain.FoodCargo;
import main.homework20191227.carrier.domain.Carrier;
import main.homework20191227.carrier.domain.CarrierType;
import main.homework20191227.common.business.exception.checked.InitStorageException;
import main.homework20191227.common.solutions.utils.DataUtils;
import main.homework20191227.transportation.domain.Transportation;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.text.ParseException;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

public class XmlFileDataInitor implements StorageInitor {
    private final String path;

    public XmlFileDataInitor(String path) {
        this.path = path;
    }

    @Override
    public void initStorage() throws InitStorageException {
        try {
            Document doc = getDocumentFromFile();
            Map<String, Cargo> cargoMap = parseCargosFromDocument(doc);
            Map<String, Carrier> carrierMap = parseCarriersFromDocument(doc);
            List<ParsedTransportation> transportations = parseTransportationsFromDocument(doc);
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

    private List<ParsedTransportation> parseTransportationsFromDocument(Document doc) throws ParseException {
        List<ParsedTransportation> list = new ArrayList<>();
        NodeList transportations = doc.getElementsByTagName("transportation");
        for (int i = 0; i < transportations.getLength(); i++) {
            Node tarnsportation = transportations.item(i);
            list.add(parseTransportationFromNode(tarnsportation));
        }
        return list;
    }

    private ParsedTransportation parseTransportationFromNode(Node tarnsportation) throws ParseException {
        ParsedTransportation parsedTransportation = new ParsedTransportation();
        NamedNodeMap attributes = tarnsportation.getAttributes();
        parsedTransportation.cargoRef = attributes.getNamedItem("refcargo").getNodeValue();
        parsedTransportation.carrierRef = attributes.getNamedItem("refcarrier").getNodeValue();

        Transportation transportationItem = new Transportation();
        transportationItem.setDescription(((Element) tarnsportation).getElementsByTagName("description").item(0).getTextContent());
        transportationItem.setBillTo(((Element) tarnsportation).getElementsByTagName("billto").item(0).getTextContent());
        transportationItem.setDate(DataUtils.valueOf(((Element) tarnsportation).getElementsByTagName("date").item(0).getTextContent()));

        parsedTransportation.transportation = transportationItem;
        return parsedTransportation;
    }

    private Map<String, Carrier> parseCarriersFromDocument(Document doc) {
        Map<String, Carrier> map = new LinkedHashMap<>();
        NodeList carriers = doc.getElementsByTagName("carrier");
        for (int i = 0; i < carriers.getLength(); i++) {
            Node carrier = carriers.item(i);
            SimpleEntry<String, Carrier> cargoEntry = parseCarrierFromNode(carrier);
            map.put(cargoEntry.getKey(), cargoEntry.getValue());
        }
        return map;
    }

    private SimpleEntry<String, Carrier> parseCarrierFromNode(Node carrier) {
        NamedNodeMap attributes = carrier.getAttributes();
        String id = attributes.getNamedItem("id").getNodeValue();
        Carrier carrierItem = new Carrier();
        carrierItem.setCarrierType(CarrierType.valueOf(attributes.getNamedItem("type").getNodeValue()));
        carrierItem.setName(((Element) carrier).getElementsByTagName("name").item(0).getTextContent());
        carrierItem.setAddress(((Element) carrier).getElementsByTagName("address").item(0).getTextContent());
        return new SimpleEntry<String, Carrier>(id, carrierItem);
    }

    private Map<String, Cargo> parseCargosFromDocument(Document doc) throws InitStorageException, ParseException {
        Map<String, Cargo> map = new LinkedHashMap<>();
        NodeList cargos = doc.getElementsByTagName("cargo");
        int length = cargos.getLength();
        for (int i = 0; i < cargos.getLength(); i++) {
            Node cargo = cargos.item(i);
            SimpleEntry<String, Cargo> cargoEntry = parseCargoFromNode(cargo);
            map.put(cargoEntry.getKey(), cargoEntry.getValue());
        }
        return map;
    }

    private SimpleEntry<String, Cargo> parseCargoFromNode(Node cargo) throws ParseException, InitStorageException {

        NamedNodeMap attributes = cargo.getAttributes();
        String id = attributes.getNamedItem("id").getNodeValue();
        String cargoType = attributes.getNamedItem("type").getNodeValue();
        String name = ((Element) cargo).getElementsByTagName("name").item(0).getTextContent();
        int weight = Integer.parseInt(((Element) cargo).getElementsByTagName("weight").item(0).getTextContent());

        Cargo cargoItem;
        switch (cargoType) {
            case "CLOTHES":
                ClothesCargo clothesCargo = new ClothesCargo();
                clothesCargo.setSize(((Element) cargo).getElementsByTagName("size").item(0).getTextContent());
                clothesCargo.setMaterial(((Element) cargo).getElementsByTagName("material").item(0).getTextContent());
                cargoItem = clothesCargo;
                break;
            case "COMPUTER":
                ComputerCargo computerCargo = new ComputerCargo();
                computerCargo.setDescription(((Element) cargo).getElementsByTagName("description").item(0).getTextContent());
                cargoItem = computerCargo;
                break;
            case "FOOD":
                FoodCargo foodCargo = new FoodCargo();
                foodCargo.setExpirationDate(DataUtils.valueOf(((Element) cargo).getElementsByTagName("expirationdate").item(0).getTextContent()));
                foodCargo.setStoreTemperature(Integer.parseInt(((Element) cargo).getElementsByTagName("storetemperature").item(0).getTextContent()));
                cargoItem = foodCargo;
                break;
            default:
                throw new InitStorageException("wrong value of CargoType");
        }
        cargoItem.setName(name);
        cargoItem.setWeight(weight);

        return new SimpleEntry<String, Cargo>(id, cargoItem);
    }

    private Document getDocumentFromFile() throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        return builder.parse(path);
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

    private static class ParsedTransportation {
        private String cargoRef;
        private String carrierRef;
        private Transportation transportation;
    }
}
