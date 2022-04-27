package net.helix.pvp.command;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.warp.HelixWarp;
import net.helix.pvp.HelixPvP;

public class SetHologramCMD implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		if (!sender.hasPermission("kombo.cmd.sethologram")) {
			sender.sendMessage("§cVocê não tem permissão.");
			return true;
		}
		
		if (args.length == 0) {
			sender.sendMessage("§cUse /" + label + " <top-players>");
			return true;
		}
		
		String hologramName = args[0].toLowerCase();
		
		if (hologramName.equals("top-players")) {
			HelixWarp warp = HelixBukkit.getInstance().getWarpManager().findWarp(hologramName).isPresent() ?
					HelixBukkit.getInstance().getWarpManager().findWarp(hologramName).get() 
					: HelixBukkit.getInstance().getWarpManager().createWarp(hologramName);
			
			Location location = ((Player)sender).getLocation().clone().add(0.5, 0, 0.5);
			
			HelixPvP.getInstance().handleTopPlayers(location);
			warp.setLocation(location);
			HelixBukkit.getInstance().getWarpManager().getData().save(warp);
			sender.sendMessage("§aVocê setou o holograma " + hologramName + "§a.");
		}else {
			sender.sendMessage("§cHolograma não encontrado.");
		}
		return true;
	}

}
