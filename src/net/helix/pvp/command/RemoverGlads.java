package net.helix.pvp.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helixpvp.kit2.GladiatorListener;

public class RemoverGlads 

	implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
	  Player p = (Player)sender;
	  if (label.equalsIgnoreCase("finalizarbatalhas"))
	  {
		  if (!sender.hasPermission("kombo.cmd.report")) {
	    		sender.sendMessage("§cVocê não tem permissão para executar esse comando!");
	    		return true;
	    	}
//if (GladiatorListener.combateGlad.containsKey(p)) {
for (final Location loc : GladiatorListener.blocks.get(p.getName())) {
	
Bukkit.getConsoleSender().sendMessage("[KITPVP] Removendo Glad de " + p.getName());
    loc.getBlock().setType(Material.AIR);
}
if (net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(p)) {
	for (final Location loc : net.helixpvp.kit2.GladiatorListener.blocks.get(p.getName())) {
		Bukkit.getConsoleSender().sendMessage("[KITPVP] Removendo Glad Secundário de " + p.getName());				
        loc.getBlock().setType(Material.AIR);
    }
	for (Player p2 : Bukkit.getOnlinePlayers()) {
		if (GladiatorListener.combateGlad.containsKey(p2) || net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(p2)) {
			p2.damage(p2.getHealth());
			p2.sendMessage("SUA BATALHA FOI FINALIZADA POR UM STAFF");
		}
	}
	p.sendMessage(ChatColor.GREEN + "Você finalizou todas as batalhas do Gladiator");
	Bukkit.broadcast(p.getName() + " finalizou todas as batalhas do gladiator", "kombo.cmd.report");
}
	  }
	return false;
	}
}
//
//
//
//
