package net.helix.pvp.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;

import net.helix.pvp.kit.KitManager;

public class ServerEssentialsListener implements Listener {
	
	@EventHandler
	public void onFome(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}
	@EventHandler
	public void onChuva(WeatherChangeEvent e) {
		e.setCancelled(e.toWeatherState());
	}
	@EventHandler
	public void onChuvat(PlayerDropItemEvent e) {
	      ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		if (e.getPlayer().getItemInHand() == item && !KitManager.getPlayer(e.getPlayer().getName()).hasKit()) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onSpawner(CreatureSpawnEvent e) {
		if (e.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM) {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onLeavesDecay(LeavesDecayEvent event) {
		event.setCancelled(true);
	}
}