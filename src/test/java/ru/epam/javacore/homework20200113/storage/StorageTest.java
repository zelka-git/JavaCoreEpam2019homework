package ru.epam.javacore.homework20200113.storage;

import main.homework20200113.application.serviceholder.ServiceHolder;
import main.homework20200113.application.serviceholder.StorageType;
import main.homework20200113.cargo.domain.Cargo;
import main.homework20200113.storage.Storage;
import main.homework20200113.storage.initor.InitStorageType;
import main.homework20200113.storage.initor.StorageInitor;
import main.homework20200113.storage.initor.StorageInitorFactory;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {
    @Test
    public void testServiceHolder() throws Exception {
        fillStorage();

        Path file = null;
        try {
            file = Files.createTempFile("testStorage", ".txt");

            try (ObjectOutput objectOutput = new ObjectOutputStream(
                    new FileOutputStream(file.toFile()))) {

                objectOutput.writeObject(ServiceHolder.getInstance());
            }

            ServiceHolder storage = readServiceHolderFromFile(file.toFile().getAbsolutePath());

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

    @Test
    public void testStorage() throws Exception {
        fillStorage();

        Path file = null;
        try {
            file = Files.createTempFile("testStorage", ".txt");

            try (ObjectOutput objectOutput = new ObjectOutputStream(
                    new FileOutputStream(file.toFile()))) {

                objectOutput.writeObject(new Storage());
            }

            Storage storage = readStorageFromFile(file.toFile().getAbsolutePath());

            assertEquals(Storage.cargoList, storage.cargoList);
            assertEquals(Storage.carriersList, storage.carriersList);
            assertEquals(Storage.transportationList, storage.transportationList);

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

    @Test
    public void testStorageCargoList() throws Exception {
        fillStorage();

        Path file = null;
        try {
            file = Files.createTempFile("testStorage", ".txt");

            try (ObjectOutput objectOutput = new ObjectOutputStream(
                    new FileOutputStream(file.toFile()))) {

                objectOutput.writeObject(Storage.cargoList);
            }

            List<Cargo> list = readStorageCargoListFromFile(file.toFile().getAbsolutePath());

            String[] name = {"Banana", "Dress", "HP"};
            int[] weight = {123, 312, 213};
            String[] cargoType = {"FOOD", "CLOTHES", "COMPUTERS"};
            for (int i = 0; i < 3; i++) {
                assertEquals(name[i], list.get(i).getName());
                assertEquals(weight[i], list.get(i).getWeight());
                assertEquals(cargoType[i], list.get(i).getCargoType().toString());
            }

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

    private static ServiceHolder readServiceHolderFromFile(String file) throws Exception {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object o = inputStream.readObject();
            return (ServiceHolder) o;
        }
    }

    private static Storage readStorageFromFile(String file) throws Exception {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object o = inputStream.readObject();
            return (Storage) o;
        }
    }

    private static List<Cargo> readStorageCargoListFromFile(String file) throws Exception {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object o = inputStream.readObject();
            return (List<Cargo>) o;
        }
    }


}