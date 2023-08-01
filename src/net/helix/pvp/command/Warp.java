package net.helix.pvp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import net.helix.pvp.inventory.WarpsInventory;

	

		public class Warp
		implements CommandExecutor
		{
		public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
		{
			{
				  Player p = (Player)sender;
				  if (label.equalsIgnoreCase("warp"))
				  {
					  if (net.helix.pvp.warp.HelixWarp.SPAWN.hasPlayer(p.getName())) {
				   WarpsInventory.open(p);
				  }
				  } else {
					  p.sendMessage("§cVocê precisa estar no spawn");
					  
				  return true;
				  }
		}
			return false;
		}

		}