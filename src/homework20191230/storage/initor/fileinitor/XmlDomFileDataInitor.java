package homework20191230.storage.initor.fileinitor;

import homework20191230.cargo.domain.Cargo;
import homework20191230.cargo.domain.ClothesCargo;
import homework20191230.cargo.domain.ComputerCargo;
import homework20191230.cargo.domain.FoodCargo;
import homework20191230.carrier.domain.Carrier;
import homework20191230.carrier.domain.CarrierType;
import homework20191230.common.business.exception.checked.InitStorageException;
import homework20191230.common.solutions.utils.DataUtils;
import homework20191230.common.solutions.utils.FileUtils;
import homework20191230.common.solutions.utils.xml.dom.XmlDomUtils;
import homework20191230.transportation.domain.Transportation;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;

public class XmlDomFileDataInitor extends BaseFileInitor {
    private final String path;

    public XmlDomFileDataInitor(String path) {
        this.path = path;
    }

    @Override
    public void initStorage() throws InitStorageException {
        File file = null;
        try {
            file = getFileWithInitData();
            Document doc = XmlDomUtils.getDocument(file);
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
            if (file != null) {
                file.delete();
            }
        }
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils.createFileFromResource("init-data", "lesson12", path);
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
        Element cargoEl = (Element) cargo;
        String name = XmlDomUtils.getOnlyElementTextContent(cargoEl, "name");
        int weight = Integer.parseInt(XmlDomUtils.getOnlyElementTextContent(cargoEl, "weight"));;

        Cargo cargoItem;
        switch (cargoType) {
            case "CLOTHES":
                ClothesCargo clothesCargo = new ClothesCargo();
                clothesCargo.setSize(XmlDomUtils.getOnlyElementTextContent(cargoEl, "size"));
                clothesCargo.setMaterial(XmlDomUtils.getOnlyElementTextContent(cargoEl, "material"));
                cargoItem = clothesCargo;
                break;
            case "COMPUTER":
                ComputerCargo computerCargo = new ComputerCargo();
                computerCargo.setDescription(XmlDomUtils.getOnlyElementTextContent(cargoEl, "description"));
                cargoItem = computerCargo;
                break;
            case "FOOD":
                FoodCargo foodCargo = new FoodCargo();
                foodCargo.setExpirationDate(DataUtils.valueOf(XmlDomUtils.getOnlyElementTextContent(cargoEl, "expirationdate")));
                foodCargo.setStoreTemperature(Integer.parseInt(XmlDomUtils.getOnlyElementTextContent(cargoEl, "storetemperature")));
                cargoItem = foodCargo;
                break;
            default:
                throw new InitStorageException("wrong value of CargoType");
        }
        cargoItem.setName(name);
        cargoItem.setWeight(weight);

        return new SimpleEntry<String, Cargo>(id, cargoItem);
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
        parsedTransportation.setCargoRef(attributes.getNamedItem("refcargo").getNodeValue());
        parsedTransportation.setCarrierRef(attributes.getNamedItem("refcarrier").getNodeValue());

        Transportation transportationItem = new Transportation();
        transportationItem.setDescription(((Element) tarnsportation).getElementsByTagName("description").item(0).getTextContent());
        transportationItem.setBillTo(((Element) tarnsportation).getElementsByTagName("billto").item(0).getTextContent());
        transportationItem.setDate(DataUtils.valueOf(((Element) tarnsportation).getElementsByTagName("date").item(0).getTextContent()));

        parsedTransportation.setTransportation(transportationItem);
        return parsedTransportation;
    }

}
