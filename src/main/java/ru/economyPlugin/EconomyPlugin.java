package ru.economyPlugin;

import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import ru.economyPlugin.commands.bal;
import ru.economyPlugin.commands.eco;
import ru.economyPlugin.commands.pay;

import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public final class EconomyPlugin extends JavaPlugin {

    public final static EconomyResponse failresp = new EconomyResponse(-1, -1, EconomyResponse.ResponseType.FAILURE, "Exception");
    public final static HashSet<Player> players = new HashSet<>();
    public final static ConcurrentHashMap<UUID, Double> balances = new ConcurrentHashMap<>();
    final static Random rand = new Random();
    static JavaPlugin plugin;
    static String configfolderpath;
    static String datafolderpath;
    static long savetime = 0;
    static boolean optimizationcpu = true;
    public static String reloadmsg = "";
    public static String playernotfoundmsg = "";
    public static String invalidamountmsg = "";
    public static String nomoneymsg = "";
    public static String ecogivemsg = "";
    public static String ecotakemsg = "";
    public static String ecosetmsg = "";
    public static String paymsg = "";
    public static String balmsg = "";
    public static String balothersmsg = "";
    public static String ecousagemsg = "";
    public static String payusagemsg = "";
    public static String balusagemsg = "";
    public static String nopermmsg = "";

    @Override
    public void onEnable() {
        plugin = getPlugin(EconomyPlugin.class);
        configfolderpath = plugin.getDataFolder().getAbsolutePath();
        final Server server = getServer();
        Config.loadConfig();
        datafolderpath = configfolderpath + "/playerdata";
        final File datafolder = new File(datafolderpath);
        if (!datafolder.exists()) datafolder.mkdir();
        server.getServicesManager().register(net.milkbowl.vault.economy.Economy.class, new Economic(), plugin, ServicePriority.Highest);
        if (optimizationcpu) {
            server.getPluginManager().registerEvents(new ListenerCPU(), plugin);
            Utils.loaddata();
        } else {
            server.getPluginManager().registerEvents(new ListenerRAM(), plugin);
        }
        final PluginCommand ecocommand = getCommand("eco");
        final eco ecoexecutor = new eco();
        final PluginCommand balcommand = getCommand("bal");
        final bal balexecutor = new bal();
        final PluginCommand paycommand = getCommand("pay");
        final pay payexecutor = new pay();
        ecocommand.setExecutor(ecoexecutor);
        ecocommand.setTabCompleter(ecoexecutor);
        balcommand.setExecutor(balexecutor);
        balcommand.setTabCompleter(balexecutor);
        paycommand.setExecutor(payexecutor);
        paycommand.setTabCompleter(payexecutor);
    }

    @Override
    public void onDisable() {
        Utils.savedata();
    }
}
