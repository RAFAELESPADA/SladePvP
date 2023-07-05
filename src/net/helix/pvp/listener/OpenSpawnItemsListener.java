package net.helix.pvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.inventory.KitsInventory;
import net.helix.pvp.inventory.KitsInventory2;
import net.helix.pvp.inventory.ShopGUI;
import net.helix.pvp.kit.KitManager;


public class OpenSpawnItemsListener implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if (!event.hasItem() || !ItemBuilder.has(event.getItem(), "spawn-item")) {
			return;
		}
		switch (ItemBuilder.getString(event.getItem(), "spawn-item")) {
			case "kits":
				KitsInventory.open(player);
				break;
			case "kits2":
				KitsInventory2.open(player);
				break;
			case "shop":
				ShopGUI.open(player);
				break;
		}
	}

		@EventHandler
		public void onInteract3(PlayerDropItemEvent event) {
			Player player = event.getPlayer();
			if (!KitManager.getPlayer(event.getPlayer().getName()).hasKit() && player.getLocation().getY() > 145) {
		
			event.setCancelled(true);
		}
}
}