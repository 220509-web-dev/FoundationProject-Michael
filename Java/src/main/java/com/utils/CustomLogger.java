package com.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;

public class CustomLogger {

    private static Path location = Paths.get("src/main/resources/logs.txt");

    public static void log(String message, LogLevel level) {
        String logInfo;

        try {
            if (!Files.exists(location)) {
                logInfo = String.format("[LOG] - %s - %s - %s\n",level.name(), "Log file created.", LocalDateTime.now());
                Files.write(location, logInfo.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE_NEW);
            }
            logInfo = String.format("[LOG] - %s - %s - %s\n",level.name(), message, LocalDateTime.now());
            Files.write(location, logInfo.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void parser(){
        String logInfo;
        try {
            logInfo = "\n";
            Files.write(location, logInfo.getBytes(StandardCharsets.UTF_8), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}