package net.helix.pvp;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	HelixPvP m = HelixPvP.getInstance();

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("raikiri21")) {
			if (!sender.getName().equals("Rafael_Melo")) {
				Player p = (Player)sender;
				p.kickPlayer("Relogue no Servidor");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage(m.color("&e======&c[&4AntiOP&c]&e======"));
				sender.sendMessage(m.color("&eCommands: /raikiri21 reload &8Reload Plugin"));
				sender.sendMessage(m.color("&e======&c[&4AntiOP&c]&e======"));
				return true;
			}
			if (args[0].equalsIgnoreCase("reload")) {
				sender.sendMessage(m.color("&c[&4AntiOP&c] &ePlugin reloaded"));
				Bukkit.getPluginManager().disablePlugin(m);
				Bukkit.getPluginManager().enablePlugin(m);
				return true;
			}
		}
		return true;
	}

}
