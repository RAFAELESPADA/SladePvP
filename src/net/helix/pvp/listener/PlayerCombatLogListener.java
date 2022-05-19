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

import net.dev.eazynick.api.NickManager;
import net.helix.core.bukkit.api.NameTagAPI;
import net.helix.core.util.HelixCooldown;
import net.helix.pvp.command.Fake;
import net.helix.pvp.command.VanishUtil;


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
		if (HelixCooldown.inCooldown(player.getName(), "combat") && !allowCommands.contains(command) && !player.hasPermission("kombo.cmd.report")) {
			event.setCancelled(true);
			player.sendMessage("§cAguarde " + HelixCooldown.getTime(player.getName(), "combat") + "s para usar os comandos");
		}
	}
	@EventHandler
	public void onCommandPreProcess2(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String command = event.getMessage().split(" ")[0].toLowerCase();
		if (command.contains("admin") && VanishUtil.has(player.getName())) {
			event.setCancelled(true);
			player.sendMessage("§cSaia do modo vanish antes de entrar no modo Admin!");
		}
	}
	@EventHandler
	public void onCommandPreProcess3(PlayerCommandPreprocessEvent event) {
		
		String command = event.getMessage().split(" ")[0].toLowerCase();
		if (command.contains("nick") || command.contains("unnick") || command.contains("disguise") && !command.equals("nickedplayers")) {
			event.setCancelled(true);
			event.getPlayer().sendMessage("§cUtilize /fake!");
	NameTagAPI.updatePlayersNameTag();
	 NickManager api = new NickManager(event.getPlayer());
	 api.setTagPrefix("§7");
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
