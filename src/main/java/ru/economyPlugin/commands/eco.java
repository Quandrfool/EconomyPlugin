package ru.economyPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.economyPlugin.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ru.economyPlugin.EconomyPlugin.*;

public class eco implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("economy.eco")) {
            switch (args.length) {
                case 1:
                    if (args[0].equals("reload")) {
                        Config.loadConfig();
                        sender.sendMessage(reloadmsg);
                    } else {
                        sender.sendMessage(ecousagemsg);
                    }
                    break;
                case 3:
                    final Player player = Bukkit.getPlayer(args[1]);
                    if (player != null && players.contains(player)) {
                        try {
                            final double amount = Double.parseDouble(args[2]);
                            final UUID uuid = player.getUniqueId();
                            switch (args[0]) {
                                case "give":
                                    balances.put(uuid, balances.get(uuid) + amount);
                                    sender.sendMessage(ecogivemsg.replace("%nick%", player.getName()).replace("%amount%", amount + ""));
                                    break;
                                case "take":
                                    balances.put(uuid, balances.get(uuid) - amount);
                                    sender.sendMessage(ecotakemsg.replace("%nick%", player.getName()).replace("%amount%", amount + ""));
                                    break;
                                case "set":
                                    balances.put(uuid, amount);
                                    sender.sendMessage(ecosetmsg.replace("%nick%", player.getName()).replace("%amount%", amount + ""));
                                    break;
                                default:
                                    sender.sendMessage(ecousagemsg);
                                    break;
                            }
                        } catch (Exception e) {
                            sender.sendMessage(invalidamountmsg);
                        }
                    } else {
                        sender.sendMessage(playernotfoundmsg.replace("%nick%", args[1]));
                    }
                    break;
                default:
                    sender.sendMessage(ecousagemsg);
                    break;
            }
        } else {
            sender.sendMessage(nopermmsg);
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (sender.hasPermission("economy.eco")) {
            switch (args.length) {
                case 1:
                    completions.add("give");
                    completions.add("take");
                    completions.add("set");
                    completions.add("reload");
                    break;
                case 2:
                    switch (args[0]) {
                        case "give","take","set":
                            for (Player player : players) {
                                completions.add(player.getName());
                            }
                    }
                    break;
            }
        }
        return completions;
    }
}
