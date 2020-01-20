package ru.epam.javacore.homework20200113;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import ru.epam.javacore.homework20200113.cargo.domain.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SerializationTest {
    private Path tempFile = null;
    @BeforeEach
    public void createTempFile() throws IOException{
        tempFile = Files.createTempFile("temp", "test");
    }
    @AfterEach
    public void deleteTempFile(){
        deleteFile(tempFile);
    }

    @Test
    public void testSerializationFoodCargo() throws Exception {
        FoodCargo cargo = prepareFood();
        String pathToFile = tempFile.toAbsolutePath().toString();

        serializeToFile(cargo, pathToFile);
        FoodCargo deserialized = readSerializedObjectFromFile(pathToFile);


        Assertions.assertTrue(areFoodEntitiesEquals(cargo, deserialized));
    }


    @Test
    public void testSerializationFoodCargos() throws Exception {
        List<FoodCargo> foods = Arrays.asList(prepareFood(), prepareFood());
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(foods, pathToFile);
        List<FoodCargo> deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areFoodEntitiesEquals(foods, deserialized));
    }

    @Test
    public void testSerializationFoodNullCargo() throws Exception {
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(null, pathToFile);
        Object deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertNull(deserialized);
    }

    @Test
    public void testSerializationClothesCargo() throws Exception {
        ClothesCargo clothes = prepareClothes();
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(clothes, pathToFile);
        ClothesCargo deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areClotheEntitiesEquals(clothes, deserialized));
    }

    @Test
    public void testSerializationClothesCargos() throws Exception {
        List<ClothesCargo> clothes = Arrays.asList(prepareClothes(), prepareClothes());
        String pathToFile = tempFile.toAbsolutePath().toString();

        serializeToFile(clothes, pathToFile);
        List<ClothesCargo> deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertTrue(areClotheEntitiesEquals(clothes, deserialized));
    }

    @Test
    public void testSerializationClothesNullCargos() throws Exception {
        String pathToFile = tempFile.toAbsolutePath().toString();
        serializeToFile(null, pathToFile);
        Object deserialized = readSerializedObjectFromFile(pathToFile);

        Assertions.assertNull(deserialized);
    }

    private void deleteFile(Path path) {
        if (path != null && path.toFile().isFile()){
            try{
                Files.delete(path);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    private <T> void serializeToFile(T entity, String file) throws Exception {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(entity);
        }
    }

    private <T> T readSerializedObjectFromFile(String file) throws Exception {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            return (T) objectInputStream.readObject();
        }
    }

    private FoodCargo prepareFood() {
        FoodCargo food = new FoodCargo();
        food.setId(randLong());
        food.setName(randString());
        food.setWeight(randInt());
        food.setStoreTemperature(randInt());
        food.setExpirationDate(new Date());

        return food;
    }

    private ClothesCargo prepareClothes() {
        ClothesCargo clothes = new ClothesCargo();
        clothes.setName(randString());
        clothes.setId(randLong());
        clothes.setSize(randString());
        clothes.setWeight(randInt());
        clothes.setMaterial(randString());

        return clothes;
    }

    private String randString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    private int randInt() {
        return RandomUtils.nextInt();
    }

    private long randLong() {
        return RandomUtils.nextLong();
    }

    private boolean areFoodEntitiesEquals(FoodCargo food1, FoodCargo food2) {
        if (food1 == null && food2 == null) {
            return true;
        } else if (food1 != null && food2 == null) {
            return false;
        } else if (food1 == null) {
            return false;
        } else {
            return STRING_COMPARATOR.compare(food1.getName(), food2.getName()) == 0
                    && LONG_COMPARATOR.compare(food1.getId(), food2.getId()) == 0
                    && food1.getWeight() == food2.getWeight()
                    && food1.getStoreTemperature() == food2.getStoreTemperature();
            //continue in this way
        }
    }

    private boolean areFoodEntitiesEquals(List<FoodCargo> foods1, List<FoodCargo> foods2) {
        if (foods1 == null && foods2 == null) {
            return true;
        } else if (foods1 != null && foods2 == null) {
            return false;
        } else if (foods1 == null) {
            return false;
        } else if (foods1.size() != foods2.size()) {
            return false;
        } else {
            for (int i = 0; i < foods1.size(); i++) {
                if (!areFoodEntitiesEquals(foods1.get(i), foods2.get(i))) {
                    return false;
                }
            }

            return true;
        }
    }

    private boolean areClotheEntitiesEquals(ClothesCargo clothe1, ClothesCargo clothe2) {
        if (clothe1 == null && clothe2 == null) {
            return true;
        } else if (clothe1 != null && clothe2 == null) {
            return false;
        } else if (clothe1 == null) {
            return false;
        } else {
            return STRING_COMPARATOR.compare(clothe1.getName(), clothe2.getName()) == 0
                    && LONG_COMPARATOR.compare(clothe1.getId(), clothe2.getId()) == 0
                    && STRING_COMPARATOR.compare(clothe1.getMaterial(), clothe2.getMaterial()) == 0;
            //continue in this way
        }
    }

    private boolean areClotheEntitiesEquals(List<ClothesCargo> clothes1,
                                             List<ClothesCargo> clothes2) {
        if (clothes1 == null && clothes2 == null) {
            return true;
        } else if (clothes1 != null && clothes2 == null) {
            return false;
        } else if (clothes1 == null) {
            return false;
        } else if (clothes1.size() != clothes2.size()) {
            return false;
        } else {
            for (int i = 0; i < clothes1.size(); i++) {
                if (!areClotheEntitiesEquals(clothes1.get(i), clothes2.get(i))) {
                    return false;
                }
            }

            return true;
        }
    }
}
