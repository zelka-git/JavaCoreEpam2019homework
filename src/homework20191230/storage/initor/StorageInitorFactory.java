package homework20191230.storage.initor;

import homework20191230.storage.initor.fileinitor.TextFileDataInitor;
import homework20191230.storage.initor.fileinitor.XmlDomFileDataInitor;
import homework20191230.storage.initor.fileinitor.XmlSaxFileDataInitor;

public final class StorageInitorFactory {
    private StorageInitorFactory() {

    }

    private static String pathTxt = "resources/homework20191227/inputData.txt";
    private static String pathXml = "resources/homework20191227/input_xml.xml";

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
