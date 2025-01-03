package ru.economyPlugin;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static ru.economyPlugin.EconomyPlugin.*;

public class Utils {

    public static void loaddata() {
        try {
            final File datafolder = new File(datafolderpath);
            for (File file : datafolder.listFiles()) {
                final UUID uuid = UUID.fromString(file.getName());
                balances.put(uuid, Double.parseDouble(new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())))));
            }
        } catch (Exception e) {}
    }

    public static void savedata() {
        for (UUID uuid : balances.keySet()) {
            final File datafile = new File(datafolderpath + "/" + uuid);
            final double bal = balances.get(uuid);
            try {
                if (bal != 0.0) {
                    if (!datafile.exists()) datafile.createNewFile();
                    final FileWriter writer = new FileWriter(datafile);
                    writer.write(bal + "");
                    writer.close();
                } else {
                    datafile.delete();
                }
            } catch (Exception e) {}
        }
    }
}
