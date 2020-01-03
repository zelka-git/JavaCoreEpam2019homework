package homework20191230.storage.initor.fileinitor.handlers;

import homework20191230.cargo.domain.Cargo;
import homework20191230.cargo.domain.ClothesCargo;
import homework20191230.cargo.domain.ComputerCargo;
import homework20191230.cargo.domain.FoodCargo;
import homework20191230.common.solutions.utils.DataUtils;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CargoHalder extends DefaultHandler {

    private StringBuilder stringBuilder = new StringBuilder();
    private Map<String, Cargo> cargoMap;
    private Cargo cargo;
    private String IdCargo;

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
                switch (cargoType) {
                    case "FOOD":
                        cargo = new FoodCargo();
                        break;
                    case "COMPUTER":
                        cargo = new ComputerCargo();
                        break;
                    case "CLOTHES":
                        cargo = new ClothesCargo();
                        break;
                }
                break;


        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String data = stringBuilder.toString();
        switch (qName) {
            case "name":
                cargo.setName(data);
                break;
            case "weight":
                cargo.setWeight(Integer.parseInt(data));
                break;
            case "expirationdate":
                try {
                    ((FoodCargo) cargo).setExpirationDate(DataUtils.valueOf(data));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
                ((ComputerCargo) cargo).setDescription(data);
                break;
            case "cargo":
                cargoMap.put(IdCargo, cargo);
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringBuilder.append(new String(ch, start, length));
    }

    public Map<String, Cargo> getCargoMap() {
        return cargoMap;
    }
}
