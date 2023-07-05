package net.helix.pvp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Fly implements CommandExecutor {
	


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player target;
		
		if (label.equalsIgnoreCase("fly") && sender instanceof Player) {
	         target = (Player)sender;
	         if (target.hasPermission("kombo.cmd.fly")) {
	            if (!target.getAllowFlight()) {
	               target.setAllowFlight(true);
	               target.sendMessage("§aFly on");
	               return false;
	            } else {
	               target.setAllowFlight(false);
	               target.sendMessage("§cFly off");
	               return false;
	            }
	         } else {
	        	 target.sendMessage("§cYou cant use this command.");
	            return false;
	         }
	     } else {
	         return false;
	      }
	}

}
