package com.javarush.boyarovich.cryptoanalyzer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileManager {

    public String readFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    public void writeFile(String content, String filePath) throws IOException {
        Files.writeString(Path.of(filePath), content);
    }
}
