package ru.epam.javacore.homework20191230.storage.initor.fileinitor.handlers;

import main.homework20191230.cargo.domain.Cargo;
import main.homework20191230.cargo.domain.ClothesCargo;
import main.homework20191230.cargo.domain.ComputerCargo;
import main.homework20191230.cargo.domain.FoodCargo;
import main.homework20191230.carrier.domain.Carrier;
import main.homework20191230.carrier.domain.CarrierType;
import main.homework20191230.common.solutions.utils.CargoUtils;
import main.homework20191230.common.solutions.utils.DataUtils;
import main.homework20191230.storage.initor.fileinitor.BaseFileInitor;
import main.homework20191230.transportation.domain.Transportation;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Handler extends DefaultHandler {

    private StringBuilder stringBuilder = new StringBuilder();
    private Map<String, Cargo> cargoMap;
    private Cargo cargo;
    private String IdCargo;

    private Map<String, Carrier> carrierMap;
    private Carrier carrier;
    private String IdCarrier;

    private List<BaseFileInitor.ParsedTransportation> transportations;
    private Transportation transportation;
    private BaseFileInitor.ParsedTransportation parsedTransportation;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        stringBuilder.setLength(0);
        switch (qName) {
            case "cargos":
                cargoMap = new LinkedHashMap<>();
                break;
            case "cargo":
                IdCargo = attributes.getValue("id");
                String cargoType = attributes.getValue("type");
                cargo = CargoUtils.getCargoByCargoType(cargoType);
                break;
            case "carriers":
                carrierMap = new LinkedHashMap<>();
                break;
            case "carrier":
                IdCarrier = attributes.getValue("id");
                carrier = new Carrier();
                carrier.setCarrierType(CarrierType.valueOf(attributes.getValue("type")));
                break;
            case "transportations":
                transportations = new ArrayList<>();
                break;
            case "transportation":
                parsedTransportation = new BaseFileInitor.ParsedTransportation();
                parsedTransportation.setCargoRef(attributes.getValue("refcargo"));
                parsedTransportation.setCarrierRef(attributes.getValue("refcarrier"));
                transportation = new Transportation();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String data = stringBuilder.toString();
        try {
            switch (qName) {
                case "name":
                    if (carrier == null) {
                        cargo.setName(data);
                    } else {
                        carrier.setName(data);
                    }
                    break;
                case "weight":
                    cargo.setWeight(Integer.parseInt(data));
                    break;
                case "expirationdate":
                    ((FoodCargo) cargo).setExpirationDate(DataUtils.valueOf(data));
                    break;
                case "storetemperature":
                    ((FoodCargo) cargo).setStoreTemperature(Integer.parseInt(data));
                    break;
                case "size":
                    ((ClothesCargo) cargo).setSize(data);
                    break;
                case "material":
                    ((ClothesCargo) cargo).setMaterial(data);
                    break;
                case "description":
                    if (transportation == null) {
                        ((ComputerCargo) cargo).setDescription(data);
                    } else {
                        transportation.setDescription(data);
                    }
                    break;
                case "cargo":
                    cargoMap.put(IdCargo, cargo);
                    break;
                case "address":
                    carrier.setAddress(data);
                    break;
                case "carrier":
                    carrierMap.put(IdCarrier, carrier);
                    break;
                case "billto":
                    transportation.setBillTo(data);
                    break;
                case "date":
                    transportation.setDate(DataUtils.valueOf(data));
                    break;
                case "transportation":
                    parsedTransportation.setTransportation(transportation);
                    transportations.add(parsedTransportation);
            }
        } catch (Exception e) {
            throw new SAXException();
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringBuilder.append(new String(ch, start, length));
    }

    public Map<String, Cargo> getCargoMap() {
        return cargoMap;
    }

    public Map<String, Carrier> getCarrierMap() {
        return carrierMap;
    }

    public List<BaseFileInitor.ParsedTransportation> getTransportations() {
        return transportations;
    }
}
