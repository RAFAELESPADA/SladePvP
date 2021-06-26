package net.helix.pvp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class DamageCMD implements Listener, CommandExecutor {
	
	public boolean pvp=true;
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("pvp")) {
			if (!(sender instanceof Player)) {
				sender.sendMessage("§c§lPVP §fComando apenas para jogadores.");
				return true;
			}
			Player p = (Player) sender;
			if (!p.hasPermission("pvp.usar")) {
				p.sendMessage("§c§lPVP §fComando apenas para jogadores.");
				return true;
			}
			if (args.length == 0) {
				p.sendMessage("§3§lPVP §fUtilize §3/pvp <off/on>");
				return true;
			}
			if (args.length >= 1) {
				if (args[0].equalsIgnoreCase("off")) {
					p.getWorld().setPVP(false);
					p.sendMessage("§c§lPVP §fVocê desabilitou o pvp do mundo §c" + p.getWorld().getName() + "§f.");
				}else if (args[0].equalsIgnoreCase("on")) {
					p.getWorld().setPVP(true);
					p.sendMessage("§a§lPVP §fVocê hesabilitou o pvp do mundo §a" + p.getWorld().getName() + "§f.");
				}else {
					p.sendMessage("§3§lPVP §fUtilize §3/pvp <off/on>");
				}
			}
		}
		return true;
	}
	

}
