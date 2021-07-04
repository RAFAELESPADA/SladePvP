package net.helix.pvp.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import net.helix.pvp.event.HelixPlayerDeathEvent;
import net.helix.pvp.event.HelixPlayerDeathEvent.Reason;
import net.helix.pvp.warp.HelixWarp;

public class PlayerDeathListener implements Listener {
	
	private final static HashMap<String, List<String>> lastKillsMap = new HashMap<>();

	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		Player killer = event.getEntity().getKiller();
		Location deathLocation = player.getLocation().clone();
		boolean validKill = false;
		
		player.spigot().respawn();
		event.setDeathMessage(null);
		event.setDroppedExp(0);
		
		if (killer != null) {
			List<String> lastKills = lastKillsMap.containsKey(killer.getName()) ?
					lastKillsMap.get(killer.getName()) : new ArrayList<>();
			
			
			int repeatedKills = (int) lastKills.stream().filter(
					username -> username.equalsIgnoreCase(player.getName())
			).count() + 1;
			
			int allowRepeatedKills = 2;
			if (repeatedKills <= allowRepeatedKills) {
				validKill = true;
				lastKills.add(player.getName());
				System.out.println("kd");
				
				if (lastKills.size() >= 3) {
					lastKills.clear();
				}
			}
			
			System.out.println("KD: " + repeatedKills);
			lastKillsMap.put(killer.getName(), lastKills);
		}
		
		HelixPlayerDeathEvent helixPlayerDeathEvent = new HelixPlayerDeathEvent(
				player, killer, deathLocation,
				new ArrayList<>(event.getDrops()), 
				HelixWarp.ONE_VS_ONE.hasPlayer(player.getName()) ? HelixPlayerDeathEvent.Reason.ONE_VS_ONE : Reason.ARENA,
				validKill
		);
		
		event.getDrops().clear();
		System.out.println("death origin: " + validKill);
		Bukkit.getPluginManager().callEvent(helixPlayerDeathEvent);
	}
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		p.setFireTicks(0);
		p.setNoDamageTicks(0);
	}
}
