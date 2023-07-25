package net.helix.pvp.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.account.HelixPlayer;
import net.helix.pvp.event.HelixPlayerDeathEvent;


public class PlayerKillstreakListener implements Listener {
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDeath(HelixPlayerDeathEvent event) {
		if (!event.isValidKill() || !event.hasKiller()) {
			return;
		}
		
		Player killer = event.getKiller();
		HelixPlayer killerAccount = HelixBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
		
		int killstreak = killerAccount.getPvp().getKillstreak();
		if (String.valueOf(killstreak).contains("5") || (String.valueOf(killstreak).contains("0")) && killstreak != 0) {
			Bukkit.broadcastMessage("§6§lKS §e" + killer.getName() + " tem um killstreak de §b" + killstreak + "§e!");
		}
		
		Player victim = event.getPlayer();
		HelixPlayer victimA = HelixBukkit.getInstance().getPlayerManager().getPlayer(victim.getName());
		int killstreak2 = victimA.getPvp().getKillstreak();
		if (killstreak2 >= 3) {
			Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("§6" + victim.getName() + " §eperdeu seu killstreak de §6" + victimA.getPvp().getKillstreak() + " §epara §6" +
	                killer.getName() + "§e!"));
		}
		victimA.getPvp().setKillstreak(0);
	}
	public static void checkKillStreakLose(int winstreak, Player killer, String victim) {
		HelixPlayer victimA = HelixBukkit.getInstance().getPlayerManager().getPlayer(victim);
	
		if (winstreak >= 3) {
			Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("§6" + victimA.getName() + " §eperdeu seu killstreak de §6" + victimA.getPvp().getKillstreak() + " §epara §6" +
	                killer.getName() + "§e!"));
		}
}
}