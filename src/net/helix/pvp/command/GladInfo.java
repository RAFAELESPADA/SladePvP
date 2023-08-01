package net.helix.pvp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.pvp.warp.HelixWarp;



public class GladInfo implements CommandExecutor {
	


	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player target;
		
		if (label.equalsIgnoreCase("warpinfo") && sender instanceof Player) {
	         target = (Player)sender;
	         if (!target.hasPermission("kombo.cmd.warpinfo")) {
	        	  target.sendMessage("§eSem permissão");
	        	  return true;
	         }
	         target.sendMessage("§e** WARP INFO §e**");
	         target.sendMessage("§aJogadores No Spawn: §e" + HelixWarp.SPAWN.getPlayerCount());
	         target.sendMessage("§aJogadores No FPS: §e" + HelixWarp.FPS.getPlayerCount());
	         target.sendMessage("§aJogadores No LAVA: §e" + HelixWarp.LAVACHALLENGE.getPlayerCount());
	         target.sendMessage("§aJogadores No 1V1: §e" + HelixWarp.ONE_VS_ONE.getPlayerCount());
	         target.sendMessage("§aJogadores No Sumo: §e" + HelixWarp.SUMO.getPlayerCount());
	               return false;
	            }
	



		return false;
	}
}
