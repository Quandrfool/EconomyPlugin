package ru.economyPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.WorldSaveEvent;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import static ru.economyPlugin.EconomyPlugin.*;

public class ListenerRAM implements org.bukkit.event.Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();
        final File dataFile = new File(dataFolderPath + "/" + uuid);
        try {
            if (dataFile.exists()) {
                balances.put(uuid, Double.parseDouble(new String(Files.readAllBytes(Paths.get(dataFile.getAbsolutePath())))));
            } else {
                balances.put(uuid, 0.0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        players.add(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        final Player player = event.getPlayer();
        players.remove(player);
        final UUID uuid = player.getUniqueId();
        final File dataFile = new File(dataFolderPath + "/" + uuid);
        try {
            final double bal = balances.get(uuid);
            if (bal != 0.0) {
                if (!dataFile.exists()) dataFile.createNewFile();
                final FileWriter writer = new FileWriter(dataFile);
                writer.write(bal + "");
                writer.close();
            } else {
                dataFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onSave(WorldSaveEvent event) {
        final long currTime = System.currentTimeMillis();
        if (currTime - saveTime > 5000) Utils.saveData();
        saveTime = currTime;
    }
}
