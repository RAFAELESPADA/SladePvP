package net.helix.pvp.inventory.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.server.ServerListPingEvent;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.inventory.KitInventory22;
import net.helix.pvp.inventory.KitsInventory;
import net.helix.pvp.inventory.KitsInventory2;
import net.helix.pvp.inventory.KitsInventoryPageThree;
import net.helix.pvp.inventory.KitsInventoryPageTwo;
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
		if (event.getCurrentItem() == null) {
			return;
		}
		if(event.getClick() == ClickType.RIGHT) {
			   if (event.getSlot() >= 45 && event.getSlot() <= 54)
				   event.setCancelled(true);
			   else {
				   if (!ItemBuilder.has(event.getCurrentItem(), "kit-gui")) {
						return;
					}
					String kitName = ItemBuilder.getString(event.getCurrentItem(), "kit-gui");

					HelixKit.findKit(kitName).ifPresent(kit -> {
						player.closeInventory();
						kit.send(player);
					});  
			   }
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
	public void onInvClick55(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventoryPageTwo.getInventoryName())) {
			return;
		}
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "kit-gui")) {
			return;
		}
		if (event.getCurrentItem() == null) {
			return;
		}
		
		String kitName = ItemBuilder.getString(event.getCurrentItem(), "kit-gui");

		HelixKit.findKit(kitName).ifPresent(kit -> {
			player.closeInventory();
			kit.send(player);
		});  
		
	}
	@EventHandler
	public void onInvClick55y(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventoryPageThree.getInventoryName())) {
			return;
		}
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "kit-gui")) {
			return;
		}
		if (event.getCurrentItem() == null) {
			return;
		}
		
		String kitName = ItemBuilder.getString(event.getCurrentItem(), "kit-gui");

		HelixKit.findKit(kitName).ifPresent(kit -> {
			player.closeInventory();
			kit.send(player);
		});  
		
	}
	@EventHandler
	public void onInvClick24(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventory.getInventoryName())) {
			return;
		}
		if (!ItemBuilder.has(event.getCurrentItem(), "prox")) {
			return;
		}
		event.setCancelled(true);
		KitsInventoryPageTwo.open(player);
	}

	@EventHandler
	public void onInvClick25y(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventoryPageThree.getInventoryName())) {
			return;
		}
		if (!ItemBuilder.has(event.getCurrentItem(), "voltar")) {
			return;
		}
		event.setCancelled(true);
		KitsInventoryPageTwo.open(player);
	}

	@EventHandler
	public void onInvClick2456(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventory2.getInventoryName())) {
			return;
		}
		if (!ItemBuilder.has(event.getCurrentItem(), "prox")) {
			return;
		}
		event.setCancelled(true);
		KitInventory22.open(player);
	}
	@EventHandler
	public void onInvClick2456yy7(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitInventory22.getInventoryName())) {
			return;
		}
		if (!ItemBuilder.has(event.getCurrentItem(), "voltar")) {
			return;
		}
		event.setCancelled(true);
		KitsInventory2.open(player);
	}
	@EventHandler
	public void onInvClick242(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventoryPageTwo.getInventoryName())) {
			return;
		}
		if (!ItemBuilder.has(event.getCurrentItem(), "voltar")) {
			return;
		}
		event.setCancelled(true);
		KitsInventory.open(player);
	}

	@EventHandler
	public void onInvClick2424(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventoryPageTwo.getInventoryName())) {
			return;
		}
		if (!ItemBuilder.has(event.getCurrentItem(), "prox")) {
			return;
		}
		event.setCancelled(true);
	KitsInventoryPageThree.open(player);
	}

	@EventHandler
	public void onInvClick2(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventory2.getInventoryName())) {
			return;
		}
		if (event.getCurrentItem() == null) {
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
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.STOMPER && (kitName2 == "Grappler" || kitName2 == "Kangaroo" || kitName2 == "Flash"  || kitName2 == "Neo" || kitName2 == "AntiStomper" || kitName2 == "Ninja")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Stomper");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.TORNADO && (kitName2 == "Grappler" || kitName2 == "Kangaroo" || kitName2 == "Monk"  || kitName2 == "Ninja")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Tornado");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.HULK && (event.getCurrentItem().getType() == Material.IRON_FENCE)) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Hulk");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.PHANTOM && (kitName2 == "Grappler" || kitName2 == "Kangaroo" || kitName2 == "Fisherman" || kitName2 == "Ninja" || kitName2 == "Flash" || kitName2 == "Stomper")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Phantom");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.JUMPER && (event.getCurrentItem().getType() == Material.IRON_FENCE)) {
				player.sendMessage("§cJumper é incompátivel com Gladiator");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.NINJA && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
				player.sendMessage("§cStomper é incompátivel com Ninja");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.SONIC && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
				player.sendMessage("§cStomper é incompátivel com Sonic");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.JUMPER && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
				player.sendMessage("§cJumper é incompátivel com Stomper");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.NEO && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
				player.sendMessage("§cNeo é incompátivel com Stomper");
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
	public void onInvClick5(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitInventory22.getInventoryName())) {
			return;
		}
		if (event.getCurrentItem() == null) {
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
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.STOMPER && (kitName2 == "Grappler" || kitName2 == "Kangaroo" || kitName2 == "Flash"  || kitName2 == "Neo" || kitName2 == "AntiStomper" || kitName2 == "Ninja")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Stomper");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.TORNADO && (kitName2 == "Grappler" || kitName2 == "Kangaroo" || kitName2 == "Monk"  || kitName2 == "Ninja")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Tornado");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.HULK && (event.getCurrentItem().getType() == Material.IRON_FENCE)) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Hulk");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.PHANTOM && (kitName2 == "Grappler" || kitName2 == "Kangaroo" || kitName2 == "Fisherman" || kitName2 == "Ninja" || kitName2 == "Flash" || kitName2 == "Stomper")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Phantom");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.JUMPER && (event.getCurrentItem().getType() == Material.IRON_FENCE)) {
				player.sendMessage("§cJumper é incompátivel com Gladiator");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.NINJA && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
				player.sendMessage("§cStomper é incompátivel com Ninja");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.SONIC && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
				player.sendMessage("§cStomper é incompátivel com Sonic");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.JUMPER && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
				player.sendMessage("§cJumper é incompátivel com Stomper");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.NEO && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
				player.sendMessage("§cNeo é incompátivel com Stomper");
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
