package ru.epam.javacore.homework20200207.storage.initor.fileinitor;

import ru.epam.javacore.homework20200207.cargo.domain.Cargo;
import ru.epam.javacore.homework20200207.carrier.domain.Carrier;
import ru.epam.javacore.homework20200207.common.business.exception.checked.InitStorageException;
import ru.epam.javacore.homework20200207.common.solutions.utils.FileUtils;
import ru.epam.javacore.homework20200207.storage.initor.fileinitor.BaseFileInitor;
import ru.epam.javacore.homework20200207.storage.initor.fileinitor.xml.sax.SaxHandler;
import ru.epam.javacore.homework20200207.transportation.domain.Transportation;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class XmlSaxFileDataInitorMultithreading extends BaseFileInitor {
    private final String path;

    public XmlSaxFileDataInitorMultithreading(String path) {
        this.path = path;
    }

    @Override
    public void initStorage() throws InitStorageException {
        File file = null;
        try {
            file = getFileWithInitData();

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            SaxHandler handler = new SaxHandler();
            saxParser.parse(file, handler);
            Map<String, Cargo> cargoMap = handler.getCargoMap();
            Map<String, Carrier> carrierMap = handler.getCarrierMap();
            List<ParsedTransportation> transportations = handler.getTransportations();

            setReferencesBetweenEntities(cargoMap, carrierMap, transportations);
            List<Transportation> transportationList = getTransportationsFromParsedObject(transportations);

            Thread persistCar  = new Thread(new Runnable() {
                @Override
                public void run() {
                    persistCargos(cargoMap.values());
                }
            });
            Thread persistCarri = new Thread(new Runnable() {
                @Override
                public void run() {
                    persistCarriers(carrierMap.values());
                }
            });

            persistCar.start();
            persistCarri.start();

            persistCar.join();
            persistCarri.join();
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
        return FileUtils.createFileFromResource(XmlSaxFileDataInitorMultithreading.class, "input", "lesson11", path);
    }
}
