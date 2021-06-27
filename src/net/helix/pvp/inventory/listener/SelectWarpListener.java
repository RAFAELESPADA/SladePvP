package net.helix.pvp.inventory.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.inventory.WarpsInventory;
import net.helix.pvp.warp.HelixWarp;

public class SelectWarpListener implements Listener {
	
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(WarpsInventory.getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "warp-gui")) {
			return;
		}
		
		String warpName = ItemBuilder.getString(event.getCurrentItem(), "warp-gui");
		HelixWarp.findWarp(warpName).ifPresent(warp -> {
			player.closeInventory();
			warp.send(player);
		});
	}

}
