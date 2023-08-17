package net.helix.pvp.listener;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.event.HelixPlayerDeathEvent;
import net.helix.pvp.warp.HelixWarp;


public class SumoKillStreak implements Listener {
		

			
	
			
			

		
		
	@EventHandler(priority = EventPriority.MONITOR)
	public void destroySumo(Player killer) {

		if (!HelixWarp.SUMO.hasPlayer(killer.getName())) {
			return;
		}
		
	}
	public static void checkKillStreakLose(int winstreak, Player killer, String victim) {
		HelixPlayer victimA = HelixBukkit.getInstance().getPlayerManager().getPlayer(victim);
	
		if (winstreak >= 3) {
			Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("§6" + victimA.getName() + " §eperdeu seu winstreak de §6" + victimA.getPvp().getKillstreak() + " §e na Sumo para §6" +
	                killer.getName() + "§e!"));
		}
}
}