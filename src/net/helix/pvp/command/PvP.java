package net.helix.pvp.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class PvP implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    Player p = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("pvp")) {
    	if (!sender.hasPermission("tag.admin")) {
    		sender.sendMessage("§cVocê não tem permissão para executar esse comando!");
    	}
      if (p.getWorld().getPVP()) {
        p.getWorld().setPVP(false);
        Bukkit.getServer().broadcastMessage("§eO PvP do servidor foi desativado");
        return true;
      } 
      p.getWorld().setPVP(true);
      Bukkit.getServer().broadcastMessage("§eO PvP do servidor foi ativado");
      return true;
    } 
    return false;
  }
}
