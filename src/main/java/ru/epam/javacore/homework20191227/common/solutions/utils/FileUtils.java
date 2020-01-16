package ru.epam.javacore.homework20191227.common.solutions.utils;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

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

}

