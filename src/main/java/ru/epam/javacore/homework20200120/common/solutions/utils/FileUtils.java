package ru.epam.javacore.homework20200120.common.solutions.utils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public final class FileUtils {

    private FileUtils(){

    }

    public static File createFileFromResource(String fileNamePrefix ,String fileNameSuffix, String resourcePath) throws IOException {
        try(InputStream inputStream = File.class.getResourceAsStream(resourcePath)) {
            Path tempFile = Files.createTempFile(fileNamePrefix, fileNameSuffix);
            Files.copy(inputStream, tempFile, REPLACE_EXISTING);
            return tempFile.toFile();
        }
    }

    public static File createFileFromResource(Class clazz, String fileNamePrefix ,String fileNameSuffix, String resourcePath) throws IOException {
        try(InputStream inputStream = clazz.getResourceAsStream(resourcePath)) {
            Path tempFile = Files.createTempFile(fileNamePrefix, fileNameSuffix);
            Files.copy(inputStream, tempFile, REPLACE_EXISTING);
            return tempFile.toFile();
        }
    }

    public static <T> T readObjectFromFile(String file) throws Exception {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            Object o = inputStream.readObject();
            return (T) o;
        }
    }

}

