package net.helix.pvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.inventory.KitsInventory;
import net.helix.pvp.inventory.ShopInventory;
import net.helix.pvp.inventory.WarpsInventory;

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
			case "warps":
				WarpsInventory.open(player);
				break;
			case "shop":
				ShopInventory.open(player);
				break;
		}
	}
	
}
