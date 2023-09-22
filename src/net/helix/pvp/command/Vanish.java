package net.helix.pvp.command;


import net.helix.core.bukkit.util.AdminUtil;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Vanish implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Voce precisa ser um player");
            return true;
        }
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("vanish") || command.getName().equalsIgnoreCase("v")) {
            if (!player.hasPermission("kombo.cmd.report")) {
                player.sendMessage("§cYou dont have permission");
                return true;
            }
            if (!VanishUtil.has(player.getName())) {
            if (AdminUtil.has(player.getName())) {
            	player.getInventory().clear();
                player.setGameMode(GameMode.SURVIVAL);
                    String modo = "admin.";
                    player.updateInventory();
                    player.sendMessage("§cYou leave the mode " + modo);
                    AdminUtil.remove(player.getName());
                }
                player.sendMessage("§aYou are now vanished.");
                VanishUtil.add(player.getName());
                player.setAllowFlight(true);
                Bukkit.getOnlinePlayers().forEach(players -> {
                    if (!players.hasPermission("kombo.cmd.report"))
                        players.hidePlayer(player);
                });
            } else {
                player.sendMessage("§cYou are no longer vanished.");
                VanishUtil.remove(player.getName());
                player.setAllowFlight(false);
                Bukkit.getOnlinePlayers().forEach(players -> {
                    players.showPlayer(player);
                });
            }
        return false;
    }
		return false;
}
}