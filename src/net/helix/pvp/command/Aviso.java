package net.helix.pvp.command;



import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import net.helix.pvp.command.BukkitCommandFramework.Command;
import net.helix.pvp.command.BukkitCommandFramework.CommandArgs;



public class Aviso extends CommandClass {
	
	@Command(name = "bc")
	public void onGamemode(CommandArgs args) {
		if (!args.isPlayer())
			return;
		
		Player player = args.getPlayer();
		String[] a = args.getArgs();
		
		if (player == null) {
			return;
		}
		if (!player.hasPermission("tag.admin")) {
			player.sendMessage("§cVocê não tem permissão para executar esse comando!");
    		return;
    	}
		if (a.length == 0) {
			player.sendMessage("§3§lBC §fUtilize /bc <mensagem>!");
		}
		
		if (a.length >= 1) {
			
			String message = "";
			
			for (int i = 0; i < a.length; i++) {
				message += a[i] + " ";
			}
			
			Bukkit.broadcastMessage("§b§lAVISO: " + " §f" + message.replace("&", "§"));
			for (Player p : Bukkit.getOnlinePlayers()) {
				p.playSound(p.getLocation(), Sound.ARROW_HIT, 1f, 1f);
			}
		}
		
		
	}

}
