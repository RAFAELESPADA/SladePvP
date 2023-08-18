package net.helix.pvp.inventory.listener;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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

import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.inventory.KitInventory22;
import net.helix.pvp.inventory.KitsInventory;
import net.helix.pvp.inventory.KitsInventory2;
import net.helix.pvp.inventory.KitsInventoryPageThree;
import net.helix.pvp.inventory.KitsInventoryPageTwo;
import net.helix.pvp.inventory.TodosOsKits;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;

public class SelectKitListener implements Listener {
	
    protected Object motdPingInstance;
	
	@EventHandler
	public void onInvClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventory.getInventoryName())) {
			return;
		}
		if (event.getCurrentItem() == null) {
			return;
		}
	
			 
			    
				   if (ItemBuilder.has(event.getCurrentItem(), "visuali")) {
						TodosOsKits.open(player);
						 event.setCancelled(true);
						return;
					}
				   if (ItemBuilder.has(event.getCurrentItem(), "visual")) {
						 event.setCancelled(true);
						return;
					}
				   if (!ItemBuilder.has(event.getCurrentItem(), "kit-gui")) {
						return;
					}
				   
				   
				   String kitName3 = ItemBuilder.getString(event.getCurrentItem(), "kit-gui");
				   if (ItemBuilder.has(event.getCurrentItem(), "visuali")) {
						TodosOsKits.open(player);
						 event.setCancelled(true);
						return;
					}
					if (!ItemBuilder.has(event.getCurrentItem(), "kit-gui")) {
						return;
					}
					
					String kitName = ItemBuilder.getString(event.getCurrentItem(), "kit-gui");
			event.setCancelled(true);
					HelixKit.findKit(kitName).ifPresent(kit -> {
						if (KitManager2.getPlayer(player.getName()).getkit2().getName() == kit.getName()) {
							player.sendMessage("§cVocê já selecionou esse kit como secundário!");
							return;
						}
						player.closeInventory();
					});
					String kitName22 = ItemBuilder.getString(event.getCurrentItem(), "kit-gui2");
					HelixKit.findKit(kitName3).ifPresent(kit ->
					{
						String kitName2 = kit.getName();
						
						if (KitManager2.getPlayer(player.getName()).getkit2().getName() == kit.getName()) {
							player.sendMessage("§cVocê já selecionou esse kit como secundário!");
							player.closeInventory();
							return;
						}
						if (KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.STOMPER && (kitName22 == "Grappler" || kitName22 == "Kangaroo" || kitName22 == "Flash"  || kitName22 == "Neo" || kitName22 == "AntiStomper" || kitName22 == "Ninja")) {
							player.sendMessage("§c" + kitName2 + " é incompátivel com Stomper");
							player.closeInventory();
							return;
						}
						if (KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.ARCHER && (kitName22 == "Grappler" || kitName22 == "Kangaroo")) {
							player.sendMessage("§c" + kitName2 + " é incompátivel com Archer");
							player.closeInventory();
							return;
						}
						if (KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.VACUUM && (kitName22 == "Avatar")) {
							player.sendMessage("§c" + kitName2 + " é incompátivel com Archer");
							player.closeInventory();
							return;
						}
						if (KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.KANGAROO && (kitName22 == "Archer")) {
							player.sendMessage("§c" + kitName2 + " é incompátivel com Kangaroo");
							player.closeInventory();
							return;
						}
						if (KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.METEOR && (kitName22 == "Stomper")) {
							player.sendMessage("§c" + kitName2 + " é incompátivel com Meteor");
							player.closeInventory();
							return;
						}
						
						if (KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.STOMPER && (kitName22 == "Meteor")) {
							player.sendMessage("§c" + kitName2 + " é incompátivel com Stomper");
							player.closeInventory();
							return;
						}
						if (KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.GRAPPLER && (kitName22 == "Archer")) {
							player.sendMessage("§c" + kitName2 + " é incompátivel com Grappler");
							player.closeInventory();
							return;
						}
						if (KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.STOMPER && (kitName22 == "Grappler")) {
							player.sendMessage("§c" + kitName2 + " é incompátivel com Grappler");
							player.closeInventory();
							return;
						}
						if (KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.NINJA && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
							player.sendMessage("§cStomper é incompátivel com Ninja");
							player.closeInventory();
							return;
						}
						///erfgh
						if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.STOMPER && (kitName22 == "Grappler" || kitName22 == "Kangaroo" || kitName22 == "Flash"  || kitName22 == "Neo" || kitName22 == "AntiStomper" || kitName22 == "Ninja")) {
							player.sendMessage("§c" + kitName22 + " é incompátivel com Stomper");
							player.closeInventory();
							return;
						}
						if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.TORNADO && (kitName22 == "Grappler" || kitName22 == "Kangaroo" || kitName22 == "Monk"  || kitName22 == "Ninja")) {
							player.sendMessage("§c" + kitName22 + " é incompátivel com Tornado");
							player.closeInventory();
							return;
						}
						if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.HULK && (event.getCurrentItem().getType() == Material.IRON_FENCE)) {
							player.sendMessage("§c" + kitName22 + " é incompátivel com Hulk");
							player.closeInventory();
							return;
						}
						if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.METEOR && (kitName22 == "Stomper")) {
							player.sendMessage("§c" + kitName22 + " é incompátivel com Meteor");
							player.closeInventory();
							return;
						}
						if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.STOMPER && (kitName22 == "Meteor")) {
							player.sendMessage("§c" + kitName22 + " é incompátivel com Stomper");
							player.closeInventory();
							return;
						}
						if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.PHANTOM && (kitName22 == "Grappler" || kitName22 == "Kangaroo" || kitName22 == "Fisherman" || kitName22 == "Ninja" || kitName22 == "Flash" || kitName22 == "Stomper")) {
							player.sendMessage("§c" + kitName22 + " é incompátivel com Phantom");
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
						if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.DESHFIRE && (kitName2 == "Stomper")) {
							player.sendMessage("§c" + kitName2 + " é incompátivel com Tornado");
							player.closeInventory();
							return;
						}
						if ((KitManager.getPlayer(player.getName()).getKit() == HelixKit.FLASH || KitManager.getPlayer(player.getName()).getKit() == HelixKit.NINJA) && (kitName22 == "Grappler" || kitName22 == "Gladiator")) {
							player.sendMessage("§c" + kitName22 + " é incompátivel com Flash");
							player.closeInventory();
							return;
						}
						if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.GLADIATOR && (kitName2 == "Flash" || kitName22 == "Ninja")) {
							player.sendMessage("§c" + kitName22 + " é incompátivel com Gladiator");
							player.closeInventory();
							return;
						}
							if ((KitManager.getPlayer(player.getName()).getKit() == HelixKit.KANGAROO || KitManager.getPlayer(player.getName()).getKit() == HelixKit.GRAPPLER  || KitManager.getPlayer(player.getName()).getKit() == HelixKit.ANTISTOMPER || KitManager.getPlayer(player.getName()).getKit() == HelixKit.FLASH) && (kitName22 == "Stomper")) {
								player.sendMessage("§c" + kitName22 +" é incompatível com " + KitManager.getPlayer(player.getName()).getKit());
								player.closeInventory();
								return;
							}
						if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.JUMPER && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
							player.sendMessage("§cJumper é incompátivel com Stomper");
							player.closeInventory();
							return;
						}
						if (KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.NEO && (event.getCurrentItem().getType() == Material.IRON_BOOTS)) {
							player.sendMessage("§cNeo é incompátivel com Stomper");
							player.closeInventory();
							return;
						}
						if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.GLADIATOR && (kitName22 == "Flash" || kitName22 == "Ninja")) {
							player.sendMessage("§c" + kitName22 + " é incompátivel com Gladiator");
							player.closeInventory();
							return;
						}
							if ((KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.KANGAROO || KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.GRAPPLER  || KitManager2.getPlayer(player.getName()).getkit2() == HelixKit2.ANTISTOMPER) && (kitName22 == "Stomper")) {
								player.sendMessage("§c" + kitName22 +" é incompatível com " + KitManager.getPlayer(player.getName()).getKit());
								player.closeInventory();
								return;
							};
						player.closeInventory();
						kit.send(player);
					});  
					return;
	
					
					
			   }

			   
	
	
	
	
		
		
	

	@EventHandler
	public void onInvClick55(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(KitsInventoryPageTwo.getInventoryName())) {
			return;
		}
		event.setCancelled(true);
		if (ItemBuilder.has(event.getCurrentItem(), "visuali")) {
			TodosOsKits.open(player);
			return;
		}
		if (!ItemBuilder.has(event.getCurrentItem(), "kit-gui")) {
			return;
		}
		if (event.getCurrentItem() == null) {
			return;
		}
		
		String kitName = ItemBuilder.getString(event.getCurrentItem(), "kit-gui");

		HelixKit.findKit(kitName).ifPresent(kit -> {
			if (KitManager2.getPlayer(player.getName()).getkit2().getName() == kit.getName()) {
				player.sendMessage("§cVocê já selecionou esse kit como secundário!");
				return;
			}
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
		if (ItemBuilder.has(event.getCurrentItem(), "visuali")) {
			TodosOsKits.open(player);
			return;
		}
		if (!ItemBuilder.has(event.getCurrentItem(), "kit-gui")) {
			return;
		}
		if (event.getCurrentItem() == null) {
			return;
		}
		
		String kitName = ItemBuilder.getString(event.getCurrentItem(), "kit-gui");

		HelixKit.findKit(kitName).ifPresent(kit -> {
			if (KitManager2.getPlayer(player.getName()).getkit2().getName() == kit.getName()) {
				player.sendMessage("§cVocê já selecionou esse kit como secundário!");
				return;
			}
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
		if (ItemBuilder.has(event.getCurrentItem(), "visuali")) {
			TodosOsKits.open(player);
			return;
		}
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
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.DESHFIRE && (kitName2 == "Stomper")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Stomper");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.AVATAR && (kitName2 == "Vacuum")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Avatar");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.ARCHER && (kitName2 == "Grappler" || kitName2 == "Kangaroo")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Archer");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.KANGAROO && (kitName2 == "Archer")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Kangaroo");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.METEOR && (kitName2 == "Stomper")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Meteor");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.STOMPER && (kitName2 == "Meteor")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Stomper");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.GRAPPLER && (kitName2 == "Archer")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Grappler");
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
	public void onInvClickt5(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		
		if (!event.getView().getTitle().equals(TodosOsKits.getInventoryName())) {
			return;
		}
		if (event.getCurrentItem() == null) {
			return;
		}
		event.setCancelled(true);
		if (!ItemBuilder.has(event.getCurrentItem(), "visao")) {
			return;
		}
		event.setCancelled(true);
		player.closeInventory();
		player.sendMessage("§b§lKIT §f" + event.getCurrentItem().getItemMeta().getDisplayName());
		player.sendMessage("§b§lDESCRISÃO §f" + event.getCurrentItem().getItemMeta().getLore());
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
		if (ItemBuilder.has(event.getCurrentItem(), "visuali")) {
			TodosOsKits.open(player);
			return;
		}
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
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.METEOR && (kitName2 == "Stomper")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Meteor");
				player.closeInventory();
				return;
			}
			if (KitManager.getPlayer(player.getName()).getKit() == HelixKit.STOMPER && (kitName2 == "Meteor")) {
				player.sendMessage("§c" + kitName2 + " é incompátivel com Stomper");
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
	 protected void setHoverMotd(List<String> hoverMotdLines) {
         int hoverMotdLinesAmount = hoverMotdLines.size();
         WrappedGameProfile[] profiles = new WrappedGameProfile[hoverMotdLinesAmount];
         for (int i = 0; i < hoverMotdLinesAmount; ++i) {
             profiles[i] = new WrappedGameProfile(new UUID(0,0), hoverMotdLines.get(i));
         }
         ((WrappedServerPing) motdPingInstance).setPlayers(Arrays.asList(profiles));
     }
	 public void setMotdPingInstance(Object motdPingInstance) {
	        this.motdPingInstance = motdPingInstance;
	    }
	 private static final List<String> motds = Arrays.asList("§a§lMAPAS §e§lALTERADOS!" , "§b§lSeason 1 §e§lINICIADA!" , "§3§lSISTEMAS §e§lALTERADOS!");

	    private static String getMotdMessage(String motd) {
	        float y = (float) motd.length();
	        for (char c : motd.toCharArray()) {
	            if (c == '§') y -= 2.5;
	        }
	        int ac = (int) ((60 - y) / 2);
	        StringBuilder space = new StringBuilder();
	        for (int i = 0; i < ac; i++) {
	            space.append(" ");
	        }
	        return space + motd;
	    }
	    public static String getName() {
	        return "§6§lSLADEMC";
	    }

	  
	  
	    public void onPacketSending(final PacketEvent event) {
	        try {
	            WrappedServerPing ping = event.getPacket().getServerPings().read(0);
	            setMotdPingInstance(ping);
	            setHoverMotd(Arrays.asList("§fMapa alterado!" , "§eSeason 1 iniciada!"));
	            event.getPacket().getServerPings().write(0, ping);
	        } catch (Exception err) {
	            Bukkit.getConsoleSender().sendMessage("ERRO NO MOTD");
	            err.printStackTrace();
	        }
	        }
	    public static String getPrefix() {
	        return (getName()) + " §7»";
	    }
	    public static String getMotd() {
	        if (Bukkit.getServer().hasWhitelist()) {
	            return getMotdMessage(getName() + "§f » §7[1.7 - 1.8]") + "\n" + getMotdMessage("§cO servidor encontra-se em manutenção.");
	        }
	        return getMotdMessage(getName() + "§f » §7[1.7 - 1.8]") + "\n" + getMotdMessage(motds.get(new Random().nextInt(motds.size())));
	    }
	@EventHandler
	public void onInvClick(ServerListPingEvent event) {
		 event.setMotd(getMotd());
		}
}
