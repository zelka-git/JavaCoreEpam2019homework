package ru.epam.javacore.homework20200113.storage.initor;

import main.homework20200113.storage.initor.fileinitor.TextFileDataInitor;
import main.homework20200113.storage.initor.fileinitor.XmlDomFileDataInitor;
import main.homework20200113.storage.initor.fileinitor.XmlSaxFileDataInitor;

public final class StorageInitorFactory {
    private StorageInitorFactory() {

    }

    private static String pathTxt = "/homework20200113/inputData.txt";
    private static String pathXml = "/homework20200113/input_xml.xml";

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
            default:
                throw new RuntimeException("Unknown storage init type" + initStorageType);
        }
    }

}
