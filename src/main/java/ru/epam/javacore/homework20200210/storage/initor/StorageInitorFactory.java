package ru.epam.javacore.homework20200210.storage.initor;

import ru.epam.javacore.homework20200210.storage.initor.fileinitor.TextFileDataInitor;
import ru.epam.javacore.homework20200210.storage.initor.fileinitor.xml.dom.XmlDomFileDataInitor;
import ru.epam.javacore.homework20200210.storage.initor.fileinitor.XmlSaxFileDataInitor;
import ru.epam.javacore.homework20200210.storage.initor.fileinitor.XmlSaxFileDataInitorMultithreading;
import ru.epam.javacore.homework20200210.storage.initor.multithread.MultiThreadStorageInitor;
import ru.epam.javacore.homework20200210.storage.initor.relationaldb.RelationalDbStorageInitor;

public final class StorageInitorFactory {
    private StorageInitorFactory() {

    }

    private static String pathTxt = "/ru/epam/javacore/homework20200210/initdata/inputData.txt";
    private static String pathXml = "/ru/epam/javacore/homework20200210/initdata/input_xml.xml";

    public static StorageInitor getStorageInitor(InitStorageType initStorageType) {
        switch (initStorageType) {
            case MEMORY:
                return new InMemoryStorageInitor();
            case TEXT_FILE:
                return new TextFileDataInitor(pathTxt);
            case XML_DOM_FILE:
                return new XmlDomFileDataInitor(pathXml);
            case XML_SAX_FILE:
                return new XmlSaxFileDataInitor(pathXml);
            case XML_SAX_FILE_MULTITHREAD:
                return new XmlSaxFileDataInitorMultithreading(pathXml);
            case MULTI_THREAD:
                return new MultiThreadStorageInitor(pathXml);
            case SQL_SCRIPTS:
                return new RelationalDbStorageInitor();
            default:
                throw new RuntimeException("Unknown storage init type" + initStorageType);
        }
    }

}
