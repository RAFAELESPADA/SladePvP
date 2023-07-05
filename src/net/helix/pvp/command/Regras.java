package net.helix.pvp.command;


	import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.helix.pvp.HelixPvP;

	public class Regras 
	implements CommandExecutor
	{
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
	  Player p = (Player)sender;
	  if (label.equalsIgnoreCase("regras"))
	  {
		p.sendMessage("§cLeia as regras");  
		p.sendMessage("");
		p.sendMessage(HelixPvP.getInstance().getConfig().getString("REGRA1").replace("&", "§"));
	    p.sendMessage(HelixPvP.getInstance().getConfig().getString("REGRA2").replace("&", "§"));
	    p.sendMessage(HelixPvP.getInstance().getConfig().getString("REGRA3").replace("&", "§"));
	    p.sendMessage(HelixPvP.getInstance().getConfig().getString("REGRA4").replace("&", "§"));
	    p.sendMessage(HelixPvP.getInstance().getConfig().getString("REGRA5").replace("&", "§"));
	    p.sendMessage(HelixPvP.getInstance().getConfig().getString("REGRA6").replace("&", "§"));
	    p.sendMessage(HelixPvP.getInstance().getConfig().getString("REGRA7").replace("&", "§"));
	    p.playSound(p.getLocation(), Sound.BLAZE_HIT, 12.0F, 12.0F);
	  }
	  return false;
	}
	}
