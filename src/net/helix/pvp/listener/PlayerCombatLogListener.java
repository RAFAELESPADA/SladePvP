package net.helix.pvp.listener;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.helix.core.util.HelixCooldown;

public class PlayerCombatLogListener implements Listener {

	private final List<String> allowCommands = Arrays.asList("tell", "r", "report", "reportar", "admin", "tag");
	private final static TimeUnit timeUnit = TimeUnit.SECONDS;
	private final static long time = 12;
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		Player victim = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		
		if (victim.getGameMode().equals(GameMode.CREATIVE) || damager.getGameMode().equals(GameMode.CREATIVE)) {
			return;
		}
		
		HelixCooldown.create(victim.getName(), "combat", timeUnit, time);
		HelixCooldown.create(damager.getName(), "combat", timeUnit, time);
	}
	
	@EventHandler
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String command = event.getMessage().split(" ")[0].toLowerCase();
		if (HelixCooldown.inCooldown(player.getName(), "combat") && !allowCommands.contains(command)) {
			event.setCancelled(true);
			player.sendMessage("§cVocê não pode executar comandos em combate. Você irá sair de combate em " + HelixCooldown.getTime(player.getName(), "combat") + "s.");
		}
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		if  (HelixCooldown.inCooldown(player.getName(), "combat")) {
			HelixCooldown.delete(player.getName(), "combat");
		}
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player player = event.getPlayer();
		if  (HelixCooldown.inCooldown(player.getName(), "combat")) {
			HelixCooldown.delete(player.getName(), "combat");
		}
	}
}
