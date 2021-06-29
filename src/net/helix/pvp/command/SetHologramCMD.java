package net.helix.pvp.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.gmail.filoghost.holographicdisplays.api.Hologram;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.warp.HelixWarp;
import net.helix.pvp.HelixPvP;

public class SetHologramCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		if (!sender.hasPermission("helix.cmd.sethologram")) {
			sender.sendMessage("§cVocê não tem permissão para executar este comando.");
			return true;
		}
		
		if (args.length == 0) {
			sender.sendMessage("§eUtilize §f/" + label + " <top-players> §epara setar a posição de um holograma.");
			return true;
		}
		
		String hologramName = args[0].toLowerCase();
		
		if (hologramName.equals("top-players")) {
			HelixWarp warp = HelixBukkit.getInstance().getWarpManager().findWarp(hologramName).isPresent() ?
					HelixBukkit.getInstance().getWarpManager().findWarp(hologramName).get() 
					: HelixBukkit.getInstance().getWarpManager().createWarp(hologramName);
			
			Location location = ((Player)sender).getLocation().clone().add(0.5, 0, 0.5);
			Hologram topPlayersHd;
			if ((topPlayersHd = HelixPvP.getInstance().getTopPlayersHd()) != null) {
				topPlayersHd.teleport(location);
			}
			
			warp.setLocation(location);
			sender.sendMessage("§aVocê setou a posição do holograma §f" + hologramName + "§a.");
		}else {
			sender.sendMessage("§cHolograma não encontrado.");
		}
		return true;
	}

}
