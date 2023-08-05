package net.helix.pvp.command;


import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;

public class LastLogin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Apenas players");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("lastlogin") || command.getName().equalsIgnoreCase("ll")) {
            if (!player.hasPermission("command.lastlogin")) {
                player.sendMessage("§cVocê não tem autorização");
                return true;
            }
            if (args.length == 0) {
                player.sendMessage("§cUtilize /lastlogin <player>");
                return true;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                for (OfflinePlayer p : Bukkit.getOfflinePlayers()) {
                    if (p.getName().equalsIgnoreCase(args[0])) {
                        Date date = new Date(p.getLastPlayed());
                        player.sendMessage("§aO último login de §e" + p.getName() + " §afoi §7" +
                                new SimpleDateFormat("dd/MM/yyyy").format(date) + " §aàs §7" + new SimpleDateFormat("HH:mm:ss").format(date)
                                + "§a.");
                    }
                }
                return true;
            }
            Date date = new Date(target.getLastPlayed());
            player.sendMessage("§aO último login de §e" + target.getName() + " §afoi §7" +
                    new SimpleDateFormat("dd/MM/yyyy").format(date) + " §aàs §7" + new SimpleDateFormat("HH:mm:ss").format(date)
            + "§a.");
        }
        return false;
    }
}