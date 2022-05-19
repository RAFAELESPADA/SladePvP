package net.helix.pvp.kit.provider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
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

public class Ajnin extends KitHandler {
	
	protected static final HashMap<String, String> map = new HashMap<>();
	
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
		if (event.isSneaking()) return;
		Player player = event.getPlayer();
		
		if (KitManager.getPlayer(player.getName()).hasKit(this) 
				&& map.containsKey(player.getName())) {
			if (HelixCooldown.inCooldown(player.getName(), "ajnin")) {
				player.sendMessage("§cAguarde " + HelixCooldown.getTime(player.getName(), "ajnin") + "s para utilizar este kit novamente.");
				return;
			}
			
			String targetName = map.get(player.getName());
			Player targetPlayer;
			
			if ((targetPlayer = Bukkit.getPlayer(targetName)) != null) {
				if (player.getLocation().distance(targetPlayer.getLocation()) >= 50) {
					player.sendMessage("§cEste jogador estÃ¡ muito longe.");
					return;
				}
				HelixCooldown.create(player.getName(), "ajnin", TimeUnit.SECONDS, 20);
				targetPlayer.teleport(player);
				player.sendMessage("§aVocê puxou §f" + targetName + "§a.");
			}
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		
		if (map.containsKey(player.getName())) {
			map.remove(player.getName());
		}
		
		if (map.containsValue(player.getName())) {
			for (Iterator<Entry<String, String>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
				if (!iterator.next().getValue().equalsIgnoreCase(player.getName())) {
					return;
				}
				iterator.remove();
			}
			
			map.entrySet().stream().filter(
					entry -> entry.getValue().equalsIgnoreCase(player.getName())
			).forEach(entry -> map.remove(entry.getKey()));
		}
	}
}
