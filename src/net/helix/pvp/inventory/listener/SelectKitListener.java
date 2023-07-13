package net.helix.pvp.inventory.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.server.ServerListPingEvent;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.inventory.KitsInventory;
import net.helix.pvp.inventory.KitsInventory2;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;

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
	public void onInvClick2(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventory2.getInventoryName())) {
			return;
		}
		
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "kit-gui2")) {
			return;
		}
		
		String kitName2 = ItemBuilder.getString(event.getCurrentItem(), "kit-gui2");
		HelixKit2.findKit(kitName2).ifPresent(kit -> {
			if (KitManager.getPlayer(player.getName()).getKit().getName() == kit.getName()) {
				player.sendMessage("§cVocê já selecionou esse kit como primário!");
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.STOMPER && (kitName2 == "Grappler" || kitName2 == "Kangaroo" || kitName2 == "Flash"  || kitName2 == "AntiStomper")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Stomper");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.PHANTOM && (kitName2 == "Grappler" || kitName2 == "Kangaroo" || kitName2 == "Ninja" || kitName2 == "Flash" || kitName2 == "Stomper")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Phantom");
				player.closeInventory();
				return;
			}
			if ((KitManager.getPlayer(player.getName()).getKit() == HelixKit.FLASH || KitManager.getPlayer(player.getName()).getKit() == HelixKit.NINJA) && (kitName2 == "Grappler" || kitName2 == "Gladiator")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Flash");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.GLADIATOR && (kitName2 == "Flash" || kitName2 == "Ninja")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Gladiator");
				player.closeInventory();
				return;
			}
				if ((KitManager.getPlayer(player.getName()).getKit() == HelixKit.KANGAROO || KitManager.getPlayer(player.getName()).getKit() == HelixKit.GRAPPLER  || KitManager.getPlayer(player.getName()).getKit() == HelixKit.ANTISTOMPER || KitManager.getPlayer(player.getName()).getKit() == HelixKit.FLASH) && (kitName2 == "Stomper")) {
					player.sendMessage("§c" + kitName2 +" é incompatível com " + KitManager.getPlayer(player.getName()).getKit());
					player.closeInventory();
					return;
				};
				player.closeInventory();
				kit.send(player);
		});
		
			
	}
	@EventHandler
	public void onInvClick(ServerListPingEvent event) {
		if (!Bukkit.getServer().hasWhitelist()) {
			event.setMotd(HelixPvP.getInstance().getConfig().getString("Motd").replace("&", "§"));
		} else {
				event.setMotd(HelixPvP.getInstance().getConfig().getString("MotdWhitelist").replace("&", "§"));	
			}
		}
}
