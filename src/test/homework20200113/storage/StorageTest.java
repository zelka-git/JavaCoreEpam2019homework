package test.homework20200113.storage;

import main.homework20200113.application.serviceholder.ServiceHolder;
import main.homework20200113.application.serviceholder.StorageType;
import main.homework20200113.storage.initor.InitStorageType;
import main.homework20200113.storage.initor.StorageInitor;
import main.homework20200113.storage.initor.StorageInitorFactory;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    @Test
    public void test() throws Exception {
        fillStorage();

        Path file = null;
        try {
            file = Files.createTempFile("testStorage", ".txt");

            try (ObjectOutput objectOutput = new ObjectOutputStream(
                    new FileOutputStream(file.toFile()))) {

                objectOutput.writeObject(ServiceHolder.getInstance());
            }

            ServiceHolder storage = readStorageFromFile(file.toFile().getAbsolutePath());

            assertSame(ServiceHolder.getInstance(), storage);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (file != null) {
                try {
                    Files.delete(file);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void fillStorage() throws Exception {
        ServiceHolder.initServiceHolder(StorageType.COLLECTION);
        StorageInitor storageInitor;
        storageInitor = StorageInitorFactory.getStorageInitor(InitStorageType.XML_SAX_FILE);
        storageInitor.initStorage();
    }

    private static ServiceHolder readStorageFromFile(String file) throws Exception {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object o = inputStream.readObject();
            return (ServiceHolder) o;
        }
    }

}