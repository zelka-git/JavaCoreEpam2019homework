package homework20191230.storage.initor.fileinitor;

import homework20191230.cargo.domain.Cargo;
import homework20191230.carrier.domain.Carrier;
import homework20191230.common.business.exception.checked.InitStorageException;
import homework20191230.storage.initor.fileinitor.handlers.CargoHalder;
import homework20191230.storage.initor.fileinitor.handlers.CarrierHandler;
import homework20191230.storage.initor.fileinitor.handlers.TransportationHandler;
import homework20191230.transportation.domain.Transportation;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.List;
import java.util.Map;

public class XmlSaxFileDataInitor extends BaseFileInitor {
    private final String path;
    public XmlSaxFileDataInitor(String path){
        this.path = path;
    }

    @Override
    public void initStorage() throws InitStorageException {
        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            CargoHalder cargoHalder = new CargoHalder();
            saxParser.parse(path,cargoHalder);
            Map<String, Cargo> cargoMap = cargoHalder.getCargoMap();

            CarrierHandler carrierHandler = new CarrierHandler();
            saxParser.parse(path,carrierHandler);
            Map<String, Carrier> carrierMap = carrierHandler.getCarrierMap();

            TransportationHandler transportationHandler = new TransportationHandler();
            saxParser.parse(path,transportationHandler);
            List<ParsedTransportation> transportations = transportationHandler.getTransportations();

            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);

            persistCargos(cargoMap.values());
            persistCarriers(carrierMap.values());
            List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);
            persistTransportations(transportationList);

        }catch (Exception e){
            e.printStackTrace();
            throw new InitStorageException(e.getMessage());
        }

    }
}
