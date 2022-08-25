package org.melonmc.grant;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.melonmc.grant.command.RankGrantCommand;
import org.melonmc.grant.command.RemoveRankCommand;

public final class Grant extends JavaPlugin {
    private static Grant instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getCommand("rankgrant").setExecutor(new RankGrantCommand());
        getCommand("removerank").setExecutor(new RemoveRankCommand());
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static Grant getInstance() { return instance; }
    public static String formatMsg(String input) {
        return ChatColor.translateAlternateColorCodes('&', getInstance().getConfig().getString(input));
    }

    public static String colourize(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
