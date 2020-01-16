package ru.epam.javacore.homework20200113.carrier;

import ru.epam.javacore.homework20200113.carrier.domain.Carrier;
import ru.epam.javacore.homework20200113.carrier.domain.CarrierType;
import ru.epam.javacore.homework20200113.common.solutions.utils.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CarrierTest {
    @Test
    public void testCarrierSerialize() throws Exception {

        Path file = null;
        try {
            file = Files.createTempFile("testCargo", ".txt");
            String name = "delivery";
            String address = "Perm";
            long id = 199L;
            CarrierType carrierType = CarrierType.CAR;
            try (ObjectOutput objectOutput = new ObjectOutputStream(
                    new FileOutputStream(file.toFile()))) {
                Carrier carrier = new Carrier();
                carrier.setName(name);
                carrier.setAddress(address);
                carrier.setId(id);
                carrier.setCarrierType(carrierType);

                objectOutput.writeObject(carrier);
            }

            Carrier carrierRead = FileUtils.readObjectFromFile(file.toFile().getAbsolutePath());

            assertEquals(name, carrierRead.getName());
            assertEquals(address, carrierRead.getAddress());
            assertEquals(carrierType, carrierRead.getCarrierType());
            assertEquals(id, carrierRead.getId());

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
}