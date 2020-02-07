package ru.epam.javacore.homework20200205.storage.initor.multithread;

import ru.epam.javacore.homework20200205.carrier.domain.Carrier;
import ru.epam.javacore.homework20200205.common.solutions.utils.FileUtils;
import ru.epam.javacore.homework20200205.common.solutions.utils.xml.sax.XmlSaxUtils;
import ru.epam.javacore.homework20200205.storage.initor.fileinitor.XmlSaxFileDataInitor;
import ru.epam.javacore.homework20200205.storage.initor.fileinitor.xml.sax.SaxHandler;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CarrierParser {

    //    private static final String FILE = "/ru/epam/javacore/lesson_16_concurrency/initdata/xmldata.xml";
    private final String FILE;

    private volatile boolean hasError = false;

    private Map<String, Carrier> carrierMap;

    public CarrierParser(String path) {
        FILE = path;
    }


    public void run() {
        File file = null;
        try {
            file = getFileWithInitData();
            Map<String, Carrier> carrierMap = parseCarriersFromFile(file);
            setCarrierMap(carrierMap);
        } catch (Exception e) {
            e.printStackTrace();
            hasError = true;
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    private Map<String, Carrier> parseCarriersFromFile(File file) throws Exception {
        SAXParser parser = XmlSaxUtils.getParser();
        SaxHandler saxHandler = new SaxHandler();
        parser.parse(file, saxHandler);
        return saxHandler.getCarrierMap();
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils
                .createFileFromResource(
                        XmlSaxFileDataInitor.class, "init-data", "lesson", FILE);
    }

    public boolean isHasError() {
        return hasError;
    }

    private synchronized void setCarrierMap(Map<String, Carrier> carrierMap) {
        this.carrierMap = carrierMap;
    }

    public synchronized Map<String, Carrier> getCarrierMap() {
        return this.carrierMap;
    }
}