package net.helix.pvp.inventory.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.server.ServerListPingEvent;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.inventory.KitsInventory;
import net.helix.pvp.kit.HelixKit;

public class SelectKitListener implements Listener {
	
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventory.getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "kit-gui")) {
			return;
		}
		
		String kitName = ItemBuilder.getString(event.getCurrentItem(), "kit-gui");
		HelixKit.findKit(kitName).ifPresent(kit -> {
			player.closeInventory();
			kit.send(player);
		});
	}
	@EventHandler
	public void onInvClick(ServerListPingEvent event) {
		if (!Bukkit.getServer().hasWhitelist()) {
			event.setMotd("                 §5§lSloper §7» §aloja.slopermc.com\n        §e§lVenha conhecer nosso §5§l§nKITPVP§7 [1.7 - 1.8]");
		} else {
				event.setMotd("             §5§lSloper §7» §aloja.slopermc.com\n     §cO Servidor está em manutenção §7[1.7 - 1.8]");	
			}
		}
}
