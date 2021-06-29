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

public class PlayerCompassListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if (!event.hasItem() || !event.getItem().getType().equals(Material.COMPASS) 
				|| !ItemBuilder.has(event.getItem(), "kit-handler", "search-players")) {
			return;
		}
		event.setCancelled(true);
		
		List<Entity> entities = player.getNearbyEntities(50.0, 50.0, 50.0).stream().filter(
				entity -> entity instanceof Player 
				&& player.getLocation().distance(entity.getLocation()) >= 5
		).collect(Collectors.toList());
		
		if (entities.size() == 0) {
			player.sendMessage("§cNenhum jogador encontrado.");
			HelixBukkit.getInstance().getWarpManager().findWarp("arena").ifPresent(warp -> {
				player.setCompassTarget(warp.getLocation());
				player.sendMessage("§cBússola apontando para o spawn.");
			});
			return;
		}
		
		Entity entity = entities.get(0);
		player.setCompassTarget(entity.getLocation());
		player.sendMessage("§dBússola apontando para §f" + entity.getName() + "§d.");
	}

}
