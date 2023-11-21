package net.helix.pvp.listener;

import java.util.List;
import java.util.stream.Collectors;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.KitManager;


public class PlayerCompassListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if (!event.hasItem() || !event.getItem().getType().equals(Material.COMPASS) 
				|| !ItemBuilder.has(event.getItem(), "kit-handler", "search-players")) {
			return;
		}
		event.setCancelled(true);
		
		List<Entity> entities = player.getNearbyEntities(250.0, 250.0, 250.0).stream().filter(
				entity -> entity instanceof Player 
				&& player.getLocation().distance(entity.getLocation()) >= 5 
				&& player.canSee((Player)entity)
				&& KitManager.getPlayer(entity.getName()).hasKit()
		).collect(Collectors.toList());
		
		if (entities.size() == 0) {
			player.sendMessage("§eNo players nearby.");
			HelixBukkit.getInstance().getWarpManager().findWarp("arena").ifPresent(warp -> {
				player.setCompassTarget(warp.getLocation());
				player.sendMessage("§eCompass pointing to spawn");
			});
			return;
		}
		
		Entity entity = entities.get(0);
		Player pe = (Player) entity;
		player.setCompassTarget(entity.getLocation());
		event.setCancelled(true);
		player.sendMessage("§eCompass pointing to §2" + entity.getName());
	}

}
