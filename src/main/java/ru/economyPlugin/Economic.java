package ru.economyPlugin;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import static ru.economyPlugin.EconomyPlugin.*;

public class Economic implements Economy {
    public boolean isEnabled() {
        return true;
    }

    public String currencyNamePlural() {
        return "EconomyPlugin";
    }

    public String currencyNameSingular() {
        return "EconomyPlugin";
    }

    public String format(double v) {
        BigDecimal bd = new BigDecimal(v).setScale(2, RoundingMode.HALF_EVEN);
        return String.valueOf(bd.doubleValue());
    }

    public int fractionalDigits() {
        return -1;
    }

    public boolean createPlayerAccount(String name) {
        balances.putIfAbsent(Bukkit.getOfflinePlayer(name).getUniqueId(), 0.0);
        return true;
    }

    public boolean createPlayerAccount(OfflinePlayer player) {
        balances.putIfAbsent(player.getUniqueId(), 0.0);
        return true;
    }

    public boolean createPlayerAccount(String name, String world) {
        balances.putIfAbsent(Bukkit.getOfflinePlayer(name).getUniqueId(), 0.0);
        return true;
    }

    public boolean createPlayerAccount(OfflinePlayer player, String world) {
        balances.putIfAbsent(player.getUniqueId(), 0.0);
        return true;
    }

    public EconomyResponse depositPlayer(String name, double amount) {
        try {
            final UUID uuid = Bukkit.getOfflinePlayer(name).getUniqueId();
            final double newBal = balances.get(uuid) + amount;
            balances.put(uuid, newBal);
            return new EconomyResponse(amount, newBal, EconomyResponse.ResponseType.SUCCESS, "");
        } catch (Exception e) {
            e.printStackTrace();
            return failResp;
        }
    }

    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        try {
            final UUID uuid = player.getUniqueId();
            final double newBal = balances.get(uuid) + amount;
            balances.put(uuid, newBal);
            return new EconomyResponse(amount, newBal, EconomyResponse.ResponseType.SUCCESS, "");
        } catch (Exception e) {
            e.printStackTrace();
            return failResp;
        }
    }

    public EconomyResponse depositPlayer(String name, String world, double amount) {
        try {
            final UUID uuid = Bukkit.getOfflinePlayer(name).getUniqueId();
            final double newBal = balances.get(uuid) + amount;
            balances.put(uuid, newBal);
            return new EconomyResponse(amount, newBal, EconomyResponse.ResponseType.SUCCESS, "");
        } catch (Exception e) {
            e.printStackTrace();
            return failResp;
        }
    }

    public EconomyResponse depositPlayer(OfflinePlayer player, String world, double amount) {
        try {
            final UUID uuid = player.getUniqueId();
            final double newBal = balances.get(uuid) + amount;
            balances.put(uuid, newBal);
            return new EconomyResponse(amount, newBal, EconomyResponse.ResponseType.SUCCESS, "");
        } catch (Exception e) {
            e.printStackTrace();
            return failResp;
        }
    }

    public double getBalance(String name) {
        return balances.getOrDefault(Bukkit.getOfflinePlayer(name).getUniqueId(), 0.0);
    }

    public double getBalance(OfflinePlayer player) {
        return balances.getOrDefault(player.getUniqueId(), 0.0);
    }

    public double getBalance(String name, String world) {
        return balances.getOrDefault(Bukkit.getOfflinePlayer(name).getUniqueId(), 0.0);
    }

    public double getBalance(OfflinePlayer player, String world) {
        return balances.getOrDefault(player.getUniqueId(), 0.0);
    }

    public String getName() {
        return "EconomyPlugin";
    }

    public boolean has(String name, double amount) {
        return balances.getOrDefault(Bukkit.getOfflinePlayer(name).getUniqueId(), 0.0) >= amount;
    }

    public boolean has(OfflinePlayer player, double amount) {
        return balances.getOrDefault(player.getUniqueId(), 0.0) >= amount;
    }

    public boolean has(String name, String world, double amount) {
        return balances.getOrDefault(Bukkit.getOfflinePlayer(name).getUniqueId(), 0.0) >= amount;
    }

    public boolean has(OfflinePlayer player, String world, double amount) {
        return balances.getOrDefault(player.getUniqueId(), 0.0) >= amount;
    }

    public boolean hasAccount(String name) {
        return balances.containsKey(Bukkit.getOfflinePlayer(name).getUniqueId());
    }

    public boolean hasAccount(OfflinePlayer player) {
        return balances.containsKey(player.getUniqueId());
    }

    public boolean hasAccount(String name, String world) {
        return balances.containsKey(Bukkit.getOfflinePlayer(name).getUniqueId());
    }

    public boolean hasAccount(OfflinePlayer player, String world) {
        return balances.containsKey(player.getUniqueId());
    }

    public EconomyResponse withdrawPlayer(String name, double amount) {
        try {
            final UUID uuid = Bukkit.getOfflinePlayer(name).getUniqueId();
            final double newBal = balances.get(uuid) - amount;
            balances.put(uuid, newBal);
            return new EconomyResponse(amount, newBal, EconomyResponse.ResponseType.SUCCESS, "");
        } catch (Exception e) {
            e.printStackTrace();
            return failResp;
        }
    }

    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        try {
            final UUID uuid = player.getUniqueId();
            final double newBal = balances.get(uuid) - amount;
            balances.put(uuid, newBal);
            return new EconomyResponse(amount, newBal, EconomyResponse.ResponseType.SUCCESS, "");
        } catch (Exception e) {
            e.printStackTrace();
            return failResp;
        }
    }

    public EconomyResponse withdrawPlayer(String name, String world, double amount) {
        try {
            final UUID uuid = Bukkit.getOfflinePlayer(name).getUniqueId();
            final double newBal = balances.get(uuid) - amount;
            balances.put(uuid, newBal);
            return new EconomyResponse(amount, newBal, EconomyResponse.ResponseType.SUCCESS, "");
        } catch (Exception e) {
            e.printStackTrace();
            return failResp;
        }
    }

    public EconomyResponse withdrawPlayer(OfflinePlayer player, String world, double amount) {
        try {
            final UUID uuid = player.getUniqueId();
            final double newBal = balances.get(uuid) - amount;
            balances.put(uuid, newBal);
            return new EconomyResponse(amount, newBal, EconomyResponse.ResponseType.SUCCESS, "");
        } catch (Exception e) {
            e.printStackTrace();
            return failResp;
        }
    }

    public boolean hasBankSupport() {
        return false;
    }

    public List<String> getBanks() {
        return null;
    }

    public EconomyResponse isBankMember(String arg0, String arg1) {
        return null;
    }

    public EconomyResponse isBankMember(String arg0, OfflinePlayer arg1) {
        return null;
    }

    public EconomyResponse isBankOwner(String arg0, String arg1) {
        return null;
    }

    public EconomyResponse isBankOwner(String arg0, OfflinePlayer arg1) {
        return null;
    }

    public EconomyResponse bankBalance(String arg0) {
        return null;
    }

    public EconomyResponse bankDeposit(String arg0, double arg1) {
        return null;
    }

    public EconomyResponse bankHas(String arg0, double arg1) {
        return null;
    }

    public EconomyResponse bankWithdraw(String arg0, double arg1) {
        return null;
    }

    public EconomyResponse createBank(String arg0, String arg1) {
        return null;
    }

    public EconomyResponse createBank(String arg0, OfflinePlayer arg1) {
        return null;
    }

    public EconomyResponse deleteBank(String arg0) {
        return null;
    }
}
