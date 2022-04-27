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
			Bukkit.broadcastMessage("§9§lKS §f" + killer.getName() + " atingiu um killstreak de §9" + killstreak + "§f!");
		}
		
		Player victim = event.getPlayer();
		HelixPlayer victimAccount = HelixBukkit.getInstance().getPlayerManager().getPlayer(victim.getName());
		
	    if (victimAccount.getPvp().getKillstreak() >= 3) {
	    	Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("§6" + victim.getName() + " §eperdeu seu killstreak de §6" + victimAccount.getPvp().getKillstreak() + " §epara §6" +
                killer.getName() + "§e!"));
	}
	}
}
