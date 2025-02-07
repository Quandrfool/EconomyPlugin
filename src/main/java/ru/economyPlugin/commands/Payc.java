package ru.economyPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ru.economyPlugin.EconomyPlugin.*;

public class Payc implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (!players.contains(sender)) return false;
            if (args.length == 2) {
                final Player player = Bukkit.getPlayer(args[0]);
                if (player != null && players.contains(player)) {
                    try {
                        final double amount = Double.parseDouble(args[1]);
                        final UUID senderUuid = ((Player) sender).getUniqueId();
                        final double senderBal = balances.get(senderUuid);
                        if (senderBal < amount) {
                            sender.sendMessage(nomoneymsg);
                            return false;
                        }
                        final UUID targetUuid = player.getUniqueId();
                        balances.put(senderUuid, senderBal - amount);
                        balances.put(targetUuid, balances.get(targetUuid) + amount);
                        sender.sendMessage(paymsg.replace("%nick%", args[0]).replace("%amount%", amount + ""));
                    } catch (Exception e) {
                        sender.sendMessage(invalidamountmsg);
                    }
                } else {
                    sender.sendMessage(playernotfoundmsg.replace("%nick%", args[0]));
                }
            } else {
                sender.sendMessage(payusagemsg);
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            for (Player player : players) {
                completions.add(player.getName());
            }
        }
        return completions;
    }
}
