package ru.economyPlugin;

import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.economyPlugin.EconomyPlugin.*;

public class Config {

    public static void loadConfig() {
        final String defCfg = "# указывает на то, что плагину нужно делать\n" +
                "# ram - плагин будет хранить всю информацию о том, у кого сколько денег на диске, и загружать в оперативную память когда игрок будет заходить на сервер\n" +
                "# cpu - плагин будет хранить всю информацию о том, у кого сколько денег всегда в оперативной памяти\n" +
                "# при изменении значения этого параметра изменения применяются только после полной перезагрузки сервера\n" +
                "optimization: cpu\n" +
                "\n" +
                "reload-msg: \"§aКонфигурация плагина перезагружена\"\n" +
                "playernotfound-msg: \"§cИгрок %nick% не найден\"\n" +
                "invalidamount-msg: \"§cВведите сумму числом\"\n" +
                "nomoney-msg: \"§cУ вас недостаточно денег для этого действия\"\n" +
                "ecogive-msg: \"§aВы успешно выдали %amount%$ игроку %nick%\"\n" +
                "ecotake-msg: \"§aВы успешно изьяли %amount%$ у игрока %nick%\"\n" +
                "ecoset-msg: \"§aВы установили баланс %amount%$ игроку %nick%\"\n" +
                "pay-msg: \"§aВы успешно отправили %amount%$ игроку %nick%\"\n" +
                "bal-msg: \"§aВаш баланс: %amount%$\"\n" +
                "balothers-msg: \"§aБаланс игрока %nick%: %amount%$\"\n" +
                "ecousage-msg: \"§cИспользуйте /eco <give/take/set/reload> <игрок> <сумма>\"\n" +
                "payusage-msg: \"§cИспользуйте /pay <игрок> <сумма>\"\n" +
                "balusage-msg: \"§cИспользуйте /bal <игрок>\"\n" +
                "noperm-msg: \"§cУ вас нет права выполнять эту команду\"";
        final File cfgDir = new File(configFolderPath);
        if (!cfgDir.exists()) {
            cfgDir.mkdir();
        }
        final File cfg = new File(configFolderPath + "/config.yml");
        try {
            if (!cfg.exists()) {
                cfg.createNewFile();
                final FileWriter writer = new FileWriter(cfg);
                writer.write(defCfg);
                writer.close();
            }
            processOptions(new String(Files.readAllBytes(Paths.get(cfg.getAbsolutePath()))).split("\n"));
        } catch (Exception e) {
            try {
                final String newName = "config-" + rand.nextInt(999999999) + ".yml";
                final File oldCfg = new File(configFolderPath + "/" + newName);
                oldCfg.createNewFile();
                FileWriter writer = new FileWriter(oldCfg);
                writer.write(new String(Files.readAllBytes(Paths.get(cfg.getAbsolutePath()))));
                writer.close();
                cfg.delete();
                cfg.createNewFile();
                writer = new FileWriter(cfg);
                writer.write(defCfg);
                writer.close();
                processOptions(new String(Files.readAllBytes(Paths.get(cfg.getAbsolutePath()))).split("\n"));
                Bukkit.getLogger().info("§cВо время загрузки конфигурации произошла ошибка, поэтому была загружена дефолтная конфигурация, ваша старая была сохранена в файл " + newName);
                Bukkit.getLogger().info("StackTrace:");
                e.printStackTrace();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    static void processOptions(String[] options) {
        for (String option : options) {
            final int index = option.indexOf(": ");
            if (index == -1) continue;
            final String parameter = option.substring(0, index);
            final String value = option.substring(index + 2).replace("\"", "").replace("\\n", "\n").replace("&", "§");
            switch (parameter) {
                case "optimization":
                    if (value.equals("cpu")) {
                        optimizationCpu = true;
                    } else {
                        optimizationCpu = false;
                    }
                    break;
                case "reload-msg":
                    reloadmsg = value;
                    break;
                case "playernotfound-msg":
                    playernotfoundmsg = value;
                    break;
                case "invalidamount-msg":
                    invalidamountmsg = value;
                    break;
                case "nomoney-msg":
                    nomoneymsg = value;
                    break;
                case "ecogive-msg":
                    ecogivemsg = value;
                    break;
                case "ecotake-msg":
                    ecotakemsg = value;
                    break;
                case "ecoset-msg":
                    ecosetmsg = value;
                    break;
                case "pay-msg":
                    paymsg = value;
                    break;
                case "bal-msg":
                    balmsg = value;
                    break;
                case "balothers-msg":
                    balothersmsg = value;
                    break;
                case "ecousage-msg":
                    ecousagemsg = value;
                    break;
                case "payusage-msg":
                    payusagemsg = value;
                    break;
                case "balusage-msg":
                    balusagemsg = value;
                    break;
                case "noperm-msg":
                    nopermmsg = value;
                    break;
            }
        }
    }
}
