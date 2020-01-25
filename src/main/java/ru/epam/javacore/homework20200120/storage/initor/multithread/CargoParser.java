package ru.epam.javacore.homework20200120.storage.initor.multithread;

import ru.epam.javacore.homework20200120.cargo.domain.Cargo;
import ru.epam.javacore.homework20200120.common.solutions.utils.FileUtils;
import ru.epam.javacore.homework20200120.common.solutions.utils.xml.sax.XmlSaxUtils;
import ru.epam.javacore.homework20200120.storage.initor.fileinitor.XmlSaxFileDataInitor;
import ru.epam.javacore.homework20200120.storage.initor.fileinitor.xml.sax.SaxHandler;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CargoParser implements Runnable {

//    private static final String FILE = "/ru/epam/javacore/lesson_16_concurrency/initdata/xmldata.xml";
    private final String FILE;

    private volatile boolean hasError = false;

    private Map<String, Cargo> cargoMap;

    public CargoParser(String path){
        FILE = path;
    }

    @Override
    public void run() {
        File file = null;
        try {
            file = getFileWithInitData();
            Map<String, Cargo> cargoMap = parseCargosFromFile(file);
            setCargoMap(cargoMap);
        } catch (Exception e) {
            e.printStackTrace();
            hasError = true;
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    private Map<String, Cargo> parseCargosFromFile(File file) throws Exception {
        SAXParser parser = XmlSaxUtils.getParser();
        SaxHandler saxHandler = new SaxHandler();
        parser.parse(file, saxHandler);
        return saxHandler.getCargoMap();
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils
                .createFileFromResource(
                        XmlSaxFileDataInitor.class, "init-data", "lesson", FILE);
    }

    public boolean isHasError() {
        return hasError;
    }

    private synchronized void setCargoMap(Map<String, Cargo> cargoMap) {
        this.cargoMap = cargoMap;
    }

    public synchronized Map<String, Cargo> getCargoMap() {
        return this.cargoMap;
    }
}