package ru.economyPlugin;

import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Server;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import ru.economyPlugin.commands.Bal;
import ru.economyPlugin.commands.Eco;
import ru.economyPlugin.commands.Pay;

import java.io.File;
import java.util.HashSet;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class EconomyPlugin extends JavaPlugin {

    public final static EconomyResponse failResp = new EconomyResponse(-1, -1, EconomyResponse.ResponseType.FAILURE, "Exception");
    public final static HashSet<Player> players = new HashSet<>();
    public final static ConcurrentHashMap<UUID, Double> balances = new ConcurrentHashMap<>();
    public final static ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
    final static Random rand = new Random();
    public static Server server;
    static JavaPlugin plugin;
    static String configFolderPath;
    static String dataFolderPath;
    static long saveTime = 0;
    static boolean optimizationCpu = true;
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
        configFolderPath = plugin.getDataFolder().getAbsolutePath();
        server = getServer();
        Config.loadConfig();
        dataFolderPath = configFolderPath + "/playerdata";
        final File dataFolder = new File(dataFolderPath);
        if (!dataFolder.exists()) dataFolder.mkdir();
        server.getServicesManager().register(net.milkbowl.vault.economy.Economy.class, new Economic(), plugin, ServicePriority.Highest);
        if (optimizationCpu) {
            server.getPluginManager().registerEvents(new ListenerCPU(), plugin);
            Utils.loadData();
        } else {
            server.getPluginManager().registerEvents(new ListenerRAM(), plugin);
        }
        final PluginCommand ecoCommand = getCommand("eco");
        final Eco ecoExecutor = new Eco();
        final PluginCommand balCommand = getCommand("bal");
        final Bal balExecutor = new Bal();
        final PluginCommand payCommand = getCommand("pay");
        final Pay payExecutor = new Pay();
        ecoCommand.setExecutor(ecoExecutor);
        ecoCommand.setTabCompleter(ecoExecutor);
        balCommand.setExecutor(balExecutor);
        balCommand.setTabCompleter(balExecutor);
        payCommand.setExecutor(payExecutor);
        payCommand.setTabCompleter(payExecutor);
    }

    @Override
    public void onDisable() {
        Utils.saveData();
    }
}
