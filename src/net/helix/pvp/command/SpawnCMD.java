package net.helix.pvp.command;

import java.util.ArrayList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import net.helix.pvp.util.DamageUtil;
import net.helix.pvp.util.SpawnUtil;
import net.helix.pvp.warp.HelixWarp;

public class SpawnCMD implements Listener, CommandExecutor {
	
	public static ArrayList<String> indo = new ArrayList<String>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		Player p = (Player) sender;
		
		SpawnUtil.apply(p);
		DamageUtil.denyDamage(p.getName());
		HelixWarp.removeHandle(p.getName());
		p.sendMessage("§aTeleportado!");
		return true;
	}
}
