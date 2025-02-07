package ru.economyPlugin;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static ru.economyPlugin.EconomyPlugin.*;

public class Utils {

    public static void loadData() {
        try {
            final File dataFolder = new File(dataFolderPath);
            for (File file : dataFolder.listFiles()) {
                final UUID uuid = UUID.fromString(file.getName());
                balances.put(uuid, Double.parseDouble(new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())))));
            }
        } catch (Exception e) {}
    }

    public static void saveData() {
        try {
            for (UUID uuid : balances.keySet()) {
                final File dataFile = new File(dataFolderPath + "/" + uuid);
                final double bal = balances.get(uuid);
                if (bal != 0.0) {
                    if (!dataFile.exists()) dataFile.createNewFile();
                    final FileWriter writer = new FileWriter(dataFile);
                    writer.write(bal + "");
                    writer.close();
                } else {
                    dataFile.delete();
                }
            }
        } catch (Exception e) {}
    }
}
