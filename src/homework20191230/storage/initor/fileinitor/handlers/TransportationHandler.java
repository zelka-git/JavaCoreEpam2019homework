package homework20191230.storage.initor.fileinitor.handlers;

import homework20191230.common.solutions.utils.DataUtils;
import homework20191230.transportation.domain.Transportation;
import homework20191230.storage.initor.fileinitor.BaseFileInitor;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TransportationHandler extends DefaultHandler {

    private StringBuilder stringBuilder = new StringBuilder();
    private List<BaseFileInitor.ParsedTransportation> transportations;
    private Transportation transportation;
    private BaseFileInitor.ParsedTransportation parsedTransportation;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        stringBuilder.setLength(0);
        switch (qName) {
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
        switch (qName) {
            case "description":
                if (transportation != null) {
                    transportation.setDescription(data);
                }
                break;
            case "billto":
                transportation.setBillTo(data);
                break;
            case "date":
                try {
                    transportation.setDate(DataUtils.valueOf(data));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case "transportation":
                parsedTransportation.setTransportation(transportation);
                transportations.add(parsedTransportation);

        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        stringBuilder.append(new String(ch, start, length));
    }

    public List<BaseFileInitor.ParsedTransportation> getTransportations() {
        return transportations;
    }
}
