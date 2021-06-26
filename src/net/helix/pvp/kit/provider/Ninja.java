package net.helix.pvp.kit.provider;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import net.helix.core.util.HelixCooldown;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Ninja extends KitHandler {
	
	private static final HashMap<String, String> map = new HashMap<>();
	
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		Player victim = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		
		if (KitManager.getPlayer(damager.getName()).hasKit(this) 
				&& KitManager.getPlayer(victim.getName()).hasKit()) {
			map.put(damager.getName(), victim.getName());
		}
	}
	
	@EventHandler
	public void onSneaking(PlayerToggleSneakEvent event) {
		Player player = event.getPlayer();
		
		if (KitManager.getPlayer(player.getName()).hasKit(this) 
				&& map.containsKey(player.getName())) {
			if (HelixCooldown.inCooldown(player.getName(), "ninja")) {
				player.sendMessage("§cAguarde " + HelixCooldown.getTime(player.getName(), "ninja") + "s para utilizar este kit novamente.");
				return;
			}
			
			String targetName = map.get(player.getName());
			Player targetPlayer;
			
			if ((targetPlayer = Bukkit.getPlayer(targetName)) != null) {
				if (player.getLocation().distance(targetPlayer.getLocation()) >= 50) {
					player.sendMessage("§cEste jogador está muito longe.");
					return;
				}
				HelixCooldown.create(player.getName(), "ninja", TimeUnit.SECONDS, 15);
				player.teleport(targetPlayer);
				player.sendMessage("§aVocê teleportou para §f" + targetName + "§a.");
			}
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		
		if (KitManager.getPlayer(player.getName()).hasKit(this)) {
			map.remove(player.getName());
		}
		if (map.containsValue(player.getName())) {
			map.entrySet().stream().filter(
					entry -> entry.getValue().equalsIgnoreCase(player.getName())
			).forEach(entry -> map.remove(entry.getKey()));
		}
	}

}
