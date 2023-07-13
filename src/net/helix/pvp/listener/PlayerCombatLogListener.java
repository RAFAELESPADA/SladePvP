package net.helix.pvp.listener;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
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
import net.helix.pvp.command.VanishUtil;
import net.helix.pvp.warp.HelixWarp;
import net.md_5.bungee.api.ChatColor;


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
		if (victim.isFlying()) {
		victim.setAllowFlight(victim.hasPermission("kombo.cmd.report"));
		victim.setFlying(victim.hasPermission("kombo.cmd.report"));
	}
	}
	
	@EventHandler
	public void onCommandPreProcess(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String command = event.getMessage().split(" ")[0].toLowerCase();
		if (HelixCooldown.inCooldown(player.getName(), "combat") && !allowCommands.contains(command) && !player.hasPermission("kombo.cmd.report")) {
			event.setCancelled(true);
			player.sendMessage("§cEspere " + HelixCooldown.getTime(player.getName(), "combat") + " segundos para usar comandos");
		}
	}
	@EventHandler
    public void aosair(PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (HelixCooldown.inCooldown(p.getName(), "combat")) {
            p.setHealth(0.0);
            HelixWarp.SPAWN.send(p);
            HelixCooldown.delete(p.getName(), "combat");
        }
	}
	@EventHandler
	public void onCommandPreProcess2(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String command = event.getMessage().split(" ")[0].toLowerCase();
		if (command.contains("admin") && VanishUtil.has(player.getName())) {
			event.setCancelled(true);
			player.sendMessage("§cSaia do vanish antes de entrar no modo admin!");
		}
	}
	@EventHandler
	public void onCommandPreProcess5(PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();
		String command = event.getMessage().toLowerCase();
		if (command.contains("evento entrar") && (!(HelixWarp.SPAWN.hasPlayer(player.getName())) && !player.hasPermission("kombo.cmd.evento"))) {
			event.setCancelled(true);
			player.sendMessage("§cPara entrar no evento você precisa estar no spawn!");
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
			player.setHealth(0);
			Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " deslogou em combate.");
		}
	}
}
