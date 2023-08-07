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


public class OnevsOneKS implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDeath(HelixPlayerDeathEvent event) {
		if (!event.isValidKill() || !event.hasKiller()) {
			return;
		}
		
		Player killer = event.getKiller();
		if (!HelixWarp.ONE_VS_ONE.hasPlayer(killer.getName())) {
			return;
		}
		HelixPlayer killerAccount = HelixBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
		
		int killstreak = killerAccount.getPvp().getWinstreakx1();
		if (String.valueOf(killstreak).contains("5") || (String.valueOf(killstreak).contains("0")) && killstreak != 0) {
			Bukkit.broadcastMessage("§6§lWINS §e" + killer.getName() + " tem um winstreak de §b" + killstreak + "§e na 1v1!");
		}
		
		Player victim = event.getPlayer();
		HelixPlayer victimA = HelixBukkit.getInstance().getPlayerManager().getPlayer(victim.getName());
		int killstreak2 = victimA.getPvp().getWinstreakx1();
		if (killstreak2 >= 3) {
			Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("§6" + victimA.getName() + " §eperdeu seu winstreak de §6" + victimA.getPvp().getWinstreakx1() + " §e na 1V1 para §6" +
	                killer.getName() + "§e!"));
		}
		victimA.getPvp().setWinstreakx1(0);
	}
	public static void checkKillStreakLose(int winstreak, Player killer, String victim) {
		HelixPlayer victimA = HelixBukkit.getInstance().getPlayerManager().getPlayer(victim);
	
		if (winstreak >= 3) {
			Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("§6" + victimA.getName() + " §eperdeu seu winstreak de §6" + victimA.getPvp().getKillstreak() + " §e na Sumo para §6" +
	                killer.getName() + "§e!"));
		}
}
}