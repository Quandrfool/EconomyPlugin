package ru.economyPlugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static ru.economyPlugin.EconomyPlugin.*;

public class bal implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        switch (args.length) {
            case 0:
                if (!(sender instanceof Player)) return false;
                if (!players.contains(sender)) return false;
                sender.sendMessage(balmsg.replace("%amount%", balances.get(((Player) sender).getUniqueId()) + ""));
                break;
            case 1:
                if (sender.hasPermission("economy.bal.others")) {
                    final Player player = Bukkit.getPlayer(args[0]);
                    if (player != null && players.contains(player)) {
                        sender.sendMessage(balothersmsg.replace("%nick%", args[0]).replace("%amount%", balances.get(player.getUniqueId()) + ""));
                    } else {
                        sender.sendMessage(playernotfoundmsg.replace("%nick%", args[0]));
                    }
                } else {
                    sender.sendMessage(nopermmsg);
                }
                break;
            default:
                sender.sendMessage(balusagemsg);
                break;
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        final List<String> completions = new ArrayList<>();
        if (sender.hasPermission("economy.bal.others") && args.length == 1) {
            for (Player player : players) {
                completions.add(player.getName());
            }
        }
        return completions;
    }
}
