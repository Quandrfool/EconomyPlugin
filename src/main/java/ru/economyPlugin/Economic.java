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
        return EconomyPlugin.plugin != null;
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
        balances.put(Bukkit.getOfflinePlayer(name).getUniqueId(), 0.0);
        return true;
    }

    public boolean createPlayerAccount(OfflinePlayer player) {
        balances.put(player.getUniqueId(), 0.0);
        return true;
    }

    public boolean createPlayerAccount(String name, String world) {
        balances.put(Bukkit.getOfflinePlayer(name).getUniqueId(), 0.0);
        return true;
    }

    public boolean createPlayerAccount(OfflinePlayer player, String world) {
        balances.put(player.getUniqueId(), 0.0);
        return true;
    }

    private boolean createAccount(UUID uuid) {
        balances.put(uuid, 0.0);
        return true;
    }

    public EconomyResponse depositPlayer(String name, double amount) {
        final UUID uuid = Bukkit.getOfflinePlayer(name).getUniqueId();
        final double newbal = balances.get(uuid) + amount;
        balances.put(uuid, newbal);
        return new EconomyResponse(amount, newbal, EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        final UUID uuid = player.getUniqueId();
        final double newbal = balances.get(uuid) + amount;
        balances.put(uuid, newbal);
        return new EconomyResponse(amount, newbal, EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse depositPlayer(String name, String world, double amount) {
        final UUID uuid = Bukkit.getOfflinePlayer(name).getUniqueId();
        final double newbal = balances.get(uuid) + amount;
        balances.put(uuid, newbal);
        return new EconomyResponse(amount, newbal, EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse depositPlayer(OfflinePlayer player, String world, double amount) {
        final UUID uuid = player.getUniqueId();
        final double newbal = balances.get(uuid) + amount;
        balances.put(uuid, newbal);
        return new EconomyResponse(amount, newbal, EconomyResponse.ResponseType.SUCCESS, "");
    }

    private EconomyResponse deposit(UUID uuid, double amount) {
        final double newbal = balances.get(uuid) + amount;
        balances.put(uuid, newbal);
        return new EconomyResponse(amount, newbal, EconomyResponse.ResponseType.SUCCESS, "");
    }

    public double getBalance(String name) {
        return balances.get(Bukkit.getOfflinePlayer(name).getUniqueId());
    }

    public double getBalance(OfflinePlayer player) {
        return balances.get(player.getUniqueId());
    }

    public double getBalance(String name, String world) {
        return balances.get(Bukkit.getOfflinePlayer(name).getUniqueId());
    }

    public double getBalance(OfflinePlayer player, String world) {
        return balances.get(player.getUniqueId());
    }

    private double getBalance(UUID uuid) {
        return balances.get(uuid);
    }

    public String getName() {
        return "EconomyPlugin";
    }

    public boolean has(String name, double amount) {
        return balances.get(Bukkit.getOfflinePlayer(name).getUniqueId()) >= amount;
    }

    public boolean has(OfflinePlayer player, double amount) {
        return balances.get(player.getUniqueId()) >= amount;
    }

    public boolean has(String name, String world, double amount) {
        return balances.get(Bukkit.getOfflinePlayer(name).getUniqueId()) >= amount;
    }

    public boolean has(OfflinePlayer player, String world, double amount) {
        return balances.get(player.getUniqueId()) >= amount;
    }

    private boolean has(UUID uuid, double amount) {
        return balances.get(uuid) >= amount;
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

    private boolean hasAccount(UUID uuid) {
        return balances.containsKey(uuid);
    }

    public EconomyResponse withdrawPlayer(String name, double amount) {
        final UUID uuid = Bukkit.getOfflinePlayer(name).getUniqueId();
        final double newbal = balances.get(uuid) - amount;
        balances.put(uuid, newbal);
        return new EconomyResponse(amount, newbal, EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        final UUID uuid = player.getUniqueId();
        final double newbal = balances.get(uuid) - amount;
        balances.put(uuid, newbal);
        return new EconomyResponse(amount, newbal, EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse withdrawPlayer(String name, String world, double amount) {
        final UUID uuid = Bukkit.getOfflinePlayer(name).getUniqueId();
        final double newbal = balances.get(uuid) - amount;
        balances.put(uuid, newbal);
        return new EconomyResponse(amount, newbal, EconomyResponse.ResponseType.SUCCESS, "");
    }

    public EconomyResponse withdrawPlayer(OfflinePlayer player, String world, double amount) {
        final UUID uuid = player.getUniqueId();
        final double newbal = balances.get(uuid) - amount;
        balances.put(uuid, newbal);
        return new EconomyResponse(amount, newbal, EconomyResponse.ResponseType.SUCCESS, "");
    }

    private EconomyResponse withdraw(UUID uuid, double amount) {
        final double newbal = balances.get(uuid) - amount;
        balances.put(uuid, newbal);
        return new EconomyResponse(amount, newbal, EconomyResponse.ResponseType.SUCCESS, "");
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
