package homework20191230.storage.initor.fileinitor.handlers;

import homework20191230.carrier.domain.Carrier;
import homework20191230.carrier.domain.CarrierType;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.LinkedHashMap;
import java.util.Map;

public class CarrierHandler extends DefaultHandler {

    private StringBuilder stringBuilder = new StringBuilder();
    ;
    private Map<String, Carrier> carrierMap;
    private Carrier carrier;
    private String IdCarrier;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        stringBuilder.setLength(0);
        switch (qName) {
            case "carriers":
                carrierMap = new LinkedHashMap<>();
                break;
            case "carrier":
                IdCarrier = attributes.getValue("id");
                carrier = new Carrier();
                carrier.setCarrierType(CarrierType.valueOf(attributes.getValue("type")));
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String data = stringBuilder.toString();
        switch (qName) {
            case "name":
                if (carrier != null) {
                    carrier.setName(data);
                }
                break;
            case "address":
                carrier.setAddress(data);
                break;
            case "carrier":
                carrierMap.put(IdCarrier, carrier);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringBuilder.append(new String(ch, start, length));
    }

    public Map<String, Carrier> getCarrierMap() {
        return carrierMap;
    }
}
