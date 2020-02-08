package ru.epam.javacore.homework20200207.storage.initor.multithread;

import ru.epam.javacore.homework20200207.common.solutions.utils.FileUtils;
import ru.epam.javacore.homework20200207.common.solutions.utils.xml.sax.XmlSaxUtils;
import ru.epam.javacore.homework20200207.storage.initor.fileinitor.BaseFileInitor;
import ru.epam.javacore.homework20200207.storage.initor.fileinitor.XmlSaxFileDataInitor;
import ru.epam.javacore.homework20200207.storage.initor.fileinitor.xml.sax.SaxHandler;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TransportationParser {

//    private static final String FILE = "/ru/epam/javacore/lesson_16_concurrency/initdata/xmldata.xml";
    private final String FILE;

    private volatile boolean hasError = false;

    private List<BaseFileInitor.ParsedTransportation> transportations;

    public TransportationParser(String path){
        FILE = path;
    }


    public void run() {
        File file = null;
        try {
            file = getFileWithInitData();
            List<BaseFileInitor.ParsedTransportation> transportations = parseCargosFromFile(file);
            setTransportations(transportations);
        } catch (Exception e) {
            e.printStackTrace();
            hasError = true;
        } finally {
            if (file != null) {
                file.delete();
            }
        }
    }

    private List<BaseFileInitor.ParsedTransportation> parseCargosFromFile(File file) throws Exception {
        SAXParser parser = XmlSaxUtils.getParser();
        SaxHandler saxHandler = new SaxHandler();
        parser.parse(file, saxHandler);
        return saxHandler.getTransportations();
    }

    private File getFileWithInitData() throws IOException {
        return FileUtils
                .createFileFromResource(
                        XmlSaxFileDataInitor.class, "init-data", "lesson", FILE);
    }

    public boolean isHasError() {
        return hasError;
    }

    private synchronized void setTransportations(List<BaseFileInitor.ParsedTransportation> transportations) {
        this.transportations = transportations;
    }

    public synchronized List<BaseFileInitor.ParsedTransportation> getTransportations() {
        return this.transportations;
    }
}
