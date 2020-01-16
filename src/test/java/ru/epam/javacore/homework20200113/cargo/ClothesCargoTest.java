package ru.epam.javacore.homework20200113.cargo;

import ru.epam.javacore.homework20200113.cargo.domain.CargoType;
import ru.epam.javacore.homework20200113.cargo.domain.ClothesCargo;
import ru.epam.javacore.homework20200113.common.solutions.utils.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClothesCargoTest {
    @Test
    public void testCargoSerialize() throws Exception {

        Path file = null;
        try {
            file = Files.createTempFile("testCargo", ".txt");
            String name = "dress";
            int weight = 123;
            long id = 99L;
            String material = "cotton";
            String size = "M";
            try (ObjectOutput objectOutput = new ObjectOutputStream(
                    new FileOutputStream(file.toFile()))) {
                ClothesCargo cargo = new ClothesCargo();
                cargo.setName(name);
                cargo.setWeight(weight);
                cargo.setId(id);
                cargo.setMaterial(material);
                cargo.setSize(size);

                objectOutput.writeObject(cargo);
            }

            ClothesCargo clothes = FileUtils.readObjectFromFile(file.toFile().getAbsolutePath());

            assertEquals(name, clothes.getName());
            assertEquals(weight, clothes.getWeight());
            assertEquals(CargoType.CLOTHES, clothes.getCargoType());
            assertEquals(material, clothes.getMaterial());
            assertEquals(size, clothes.getSize());
            assertEquals(id, clothes.getId());

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