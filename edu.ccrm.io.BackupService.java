package edu.ccrm.io;

import edu.ccrm.config.AppConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupService {
    private Path dataPath = AppConfig.getInstance().getDataFolder();

    public void backup() throws IOException {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path backupFolder = dataPath.resolve("backup_" + timestamp);
        Files.createDirectories(backupFolder);

        // Copy files
        try (var walker = Files.walk(dataPath)) {
            walker.filter(p -> !p.equals(dataPath) && !p.startsWith(backupFolder))
                  .forEach(source -> {
                      try {
                          Path dest = backupFolder.resolve(dataPath.relativize(source));
                          Files.copy(source, dest, StandardCopyOption.REPLACE_EXISTING);
                      } catch (IOException e) {
                          e.printStackTrace();
                      }
                  });
        }

        System.out.println("Backup created at: " + backupFolder);
        System.out.println("Backup size: " + calculateDirectorySize(backupFolder) + " bytes");
    }

    // Recursive utility to compute directory size
    public long calculateDirectorySize(Path dir) throws IOException {
        if (Files.isRegularFile(dir)) {
            return Files.size(dir);
        }
        long size = 0;
        try (var walker = Files.walk(dir)) {
            size = walker.filter(Files::isRegularFile).mapToLong(p -> {
                try {
                    return Files.size(p);
                } catch (IOException e) {
                    return 0;
                }
            }).sum();
        }
        return size; // Note: This uses walk, but for pure recursion: implement recursive method calls on subdirs
    }
}