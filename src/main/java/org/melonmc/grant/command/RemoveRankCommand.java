package org.melonmc.grant.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.melonmc.grant.Grant;

public class RemoveRankCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender p, Command command, String label, String[] args) {
        if(!p.hasPermission("grant.removerank")) {
            p.sendMessage(Grant.formatMsg("NO_PERMISSION"));
            return true;
        }
        if(args.length == 2) {
            String target = args[0];
            if(Bukkit.getPlayer(target) == null) {
                p.sendMessage(Grant.formatMsg("PLAYER_NOT_FOUND"));
                return true;
            }
            String rank = args[1];
            rank = rank.toLowerCase();
            String capital = rank.substring(0, 1).toUpperCase();
            String r = capital + rank.substring(1);
            if(!Grant.getInstance().getConfig().isConfigurationSection("RANKS."+rank.toUpperCase())) {
                p.sendMessage(Grant.formatMsg("RANK_NOT_FOUND").replace("%rank%", r));
                return true;
            }
            Player t = Bukkit.getPlayer(target);
            p.sendMessage(Grant.formatMsg("RANK_REMOVED").replace("%player%", t.getName()).replace("%rank%", r));
            for(String commands : Grant.getInstance().getConfig().getStringList("RANKS."+rank.toUpperCase()+".REMOVE_COMMANDS")) {
                commands = commands.replace("%player%", t.getName()).replace("%executor%", p.getName());
                Bukkit.getServer().dispatchCommand(p, commands);
            }
            return true;
        }
        p.sendMessage(Grant.colourize("&cUsage: /removerank <player> <rank>"));
        return true;
    }
}
