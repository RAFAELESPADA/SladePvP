package net.helix.pvp.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.pvp.kit.provider.GladiatorListener;

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
			for (Player p2 : Bukkit.getOnlinePlayers()) {	  
				if (GladiatorListener.blocks.get(p2.getName()) == null || net.helixpvp.kit2.GladiatorListener.blocks.get(p2.getName()) == null) {
					p.sendMessage(ChatColor.RED + "Não há batalhas rodando.");
					return true;
				}
for (final Location loc : GladiatorListener.blocks.get(p2.getName())) {
	
	if (net.helixpvp.kit2.GladiatorListener.blocks.get(p2.getName()) != null) {
	for (final Location loc2 : net.helixpvp.kit2.GladiatorListener.blocks.get(p2.getName())) {
	if (loc == null && loc2 == null) {
		p.sendMessage(ChatColor.RED + "Não há batalhas rodando.");
		return true;
	}
Bukkit.getConsoleSender().sendMessage("[KITPVP] Removendo Glad de " + p.getName());
if (loc != null) {
    loc.getBlock().setType(Material.AIR);
}
if (net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(p2)) {
		Bukkit.getConsoleSender().sendMessage("[KITPVP] Removendo Glad Secundário de " + p2.getName());		
		if (loc2 != null) {
        loc2.getBlock().setType(Material.AIR);
    }
		if (GladiatorListener.combateGlad.containsKey(p2) || net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(p2)) {
			p2.damage(p2.getHealth());
			p2.sendMessage("SUA BATALHA FOI FINALIZADA POR UM STAFF");
		}
	}
	p.sendMessage(ChatColor.GREEN + "Você finalizou todas as batalhas do Gladiator");
	Bukkit.broadcast(p.getName() + " finalizou todas as batalhas do gladiator", "kombo.cmd.report");
}
	  }
	}
}
	return false;
	}

	return false;
	}
}
//
//
//
//
