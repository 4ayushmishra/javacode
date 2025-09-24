package edu.ccrm.config;

import java.nio.file.Path;
import java.nio.file.Paths;

// Singleton for app configuration
public class AppConfig {
    private static AppConfig instance;
    private final Path dataFolder;

    private AppConfig() {
        dataFolder = Paths.get("data");
    }

    public static synchronized AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public Path getDataFolder() {
        return dataFolder;
    }
}