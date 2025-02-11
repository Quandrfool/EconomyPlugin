package ru.economyPlugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.WorldSaveEvent;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static ru.economyPlugin.EconomyPlugin.*;

public class ListenerCPU implements org.bukkit.event.Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onJoin(PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final UUID uuid = player.getUniqueId();
        if (!balances.containsKey(uuid)) balances.put(uuid, 0.0);
        players.add(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        players.remove(event.getPlayer());
    }

    @EventHandler
    public void onSave(WorldSaveEvent event) {
        final long currentTime = System.currentTimeMillis();
        if (currentTime - saveTime > 5000) {
            ses.schedule(new Runnable() {
                @Override
                public void run() {
                    Utils.saveData();
                }
            }, 0, TimeUnit.MILLISECONDS);
            saveTime = currentTime;
        }
    }
}
