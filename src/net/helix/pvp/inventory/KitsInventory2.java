package net.helix.pvp.inventory;


import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;

public class KitsInventory2 {

	private final static String inventoryName = "Kits Secundários";
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
	
		if (player.hasPermission("kombo.kit2.anchor")) {
		inventory.setItem(30 , new ItemBuilder("§a" + HelixKit2.ANCHOR.getName(), HelixKit2.ANCHOR.getIcon())
				.lore("§f" + HelixKit2.ANCHOR.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.ANCHOR.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.antistomper")) {
		inventory.setItem(12 , new ItemBuilder("§a" + HelixKit2.ANTISTOMPER.getName(), HelixKit2.ANTISTOMPER.getIcon())
				.lore("§f" + HelixKit2.ANTISTOMPER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.ANTISTOMPER.getName())
						.toStack()
				);
		}
		
		inventory.setItem(11 , new ItemBuilder("§a" + HelixKit2.ARCHER.getName(), HelixKit2.ARCHER.getIcon())
				.lore("§f" + HelixKit2.ARCHER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.ARCHER.getName())
						.toStack()
				);
		if (player.hasPermission("kombo.kit2.barbarian")) {
		inventory.setItem(12 , new ItemBuilder("§a" + HelixKit2.BARBARIAN.getName(), HelixKit2.BARBARIAN.getIcon())
				.lore("§f" + HelixKit2.BARBARIAN.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.BARBARIAN.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.boxer")) {
		inventory.setItem(13 , new ItemBuilder("§a" + HelixKit2.BOXER.getName(), HelixKit2.BOXER.getIcon())
				.lore("§f" + HelixKit2.BOXER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.BOXER.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.camel")) {
		inventory.setItem(14 , new ItemBuilder("§a" + HelixKit2.CAMEL.getName(), HelixKit2.CAMEL.getIcon())
				.lore("§f" + HelixKit2.CAMEL.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.CAMEL.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.critical")) {
		inventory.setItem(15 , new ItemBuilder("§a" + HelixKit2.CRITICAL.getName(), HelixKit2.CRITICAL.getIcon())
				.lore("§f" + HelixKit2.CRITICAL.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.CRITICAL.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.firebender")) {
		inventory.setItem(16 , new ItemBuilder("§a" + HelixKit2.FIREBENDER.getName(), HelixKit2.FIREBENDER.getIcon())
				.lore("§f" + HelixKit2.FIREBENDER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.FIREBENDER.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.fireman")) {
		inventory.setItem(19 , new ItemBuilder("§a" + HelixKit2.FIREMAN.getName(), HelixKit2.FIREMAN.getIcon())
				.lore("§f" + HelixKit2.FIREMAN.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.FIREMAN.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.fisherman")) {
		inventory.setItem(20 , new ItemBuilder("§a" + HelixKit2.FISHERMAN.getName(), HelixKit2.FISHERMAN.getIcon())
				.lore("§f" + HelixKit2.FISHERMAN.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.FISHERMAN.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.gladiator")) {
		inventory.setItem(21 , new ItemBuilder("§a" + HelixKit2.GLADIATOR.getName(), HelixKit2.GLADIATOR.getIcon())
				.lore("§f" + HelixKit2.GLADIATOR.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.GLADIATOR.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.grappler")) {
		inventory.setItem(22 , new ItemBuilder("§a" + HelixKit2.GRAPPLER.getName(), HelixKit2.GRAPPLER.getIcon())
				.lore("§f" + HelixKit2.GRAPPLER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.GRAPPLER.getName())
						.toStack()
				);
		}
		inventory.setItem(23 , new ItemBuilder("§a" + HelixKit2.KANGAROO.getName(), HelixKit2.KANGAROO.getIcon())
				.lore("§f" + HelixKit2.KANGAROO.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.KANGAROO.getName())
						.toStack()
				);
		if (player.hasPermission("kombo.kit2.leech")) {
		inventory.setItem(24 , new ItemBuilder("§a" + HelixKit2.LEECH.getName(), HelixKit2.LEECH.getIcon())
				.lore("§f" + HelixKit2.LEECH.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.LEECH.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.monk")) {
		inventory.setItem(25 , new ItemBuilder("§a" + HelixKit2.MONK.getName(), HelixKit2.MONK.getIcon())
				.lore("§f" + HelixKit2.MONK.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.MONK.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.neo")) {
		inventory.setItem(28 , new ItemBuilder("§a" + HelixKit2.NEO.getName(), HelixKit2.NEO.getIcon())
				.lore("§f" + HelixKit2.NEO.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.NEO.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.ninja")) {
		inventory.setItem(29 , new ItemBuilder("§a" + HelixKit2.NINJA.getName(), HelixKit2.NINJA.getIcon())
				.lore("§f" + HelixKit2.NINJA.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.NINJA.getName())
						.toStack()
				);
		}
		
		inventory.setItem(10 , new ItemBuilder("§a" + HelixKit2.PVP.getName(), HelixKit2.PVP.getIcon())
				.lore("§f" + HelixKit2.PVP.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.PVP.getName()).addEnchant(Enchantment.DAMAGE_ALL, 1)					
						.toStack()
				);
		if (player.hasPermission("kombo.kit2.quickdropper")) {
		inventory.setItem(31 , new ItemBuilder("§a" + HelixKit2.QUICKDROPPER.getName(), HelixKit2.QUICKDROPPER.getIcon())
				.lore("§f" + HelixKit2.QUICKDROPPER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.QUICKDROPPER.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.sight")) {
		inventory.setItem(32 , new ItemBuilder("§a" + HelixKit2.SIGHT.getName(), HelixKit2.SIGHT.getIcon())
				.lore("§f" + HelixKit2.SIGHT.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.SIGHT.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.snail")) {
		inventory.setItem(33 , new ItemBuilder("§a" + HelixKit2.SNAIL.getName(), HelixKit2.SNAIL.getIcon())
				.lore("§f" + HelixKit2.SNAIL.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.SNAIL.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit2.stomper")) {
		inventory.setItem(34 , new ItemBuilder("§a" + HelixKit2.STOMPER.getName(), HelixKit2.STOMPER.getIcon())
				.lore("§f" + HelixKit2.STOMPER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.STOMPER.getName())
						.toStack()
				);
		}
		
		inventory.setItem(53, new ItemBuilder("§aPróximo", Material.ARROW).nbt("prox")
				.toStack()
		);
		ItemStack i =  new ItemStack(KitManager2.getPlayer(player.getName()).getkit2().getIcon());
		ItemMeta i2 = i.getItemMeta();
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add(KitManager2.getPlayer(player.getName()).getkit2().getDescription());
		i2.setDisplayName(KitManager2.getPlayer(player.getName()).getkit2().getName());
		i2.setLore(lore);
		i.setItemMeta(i2);
		inventory.setItem(49, new ItemStack(KitManager2.getPlayer(player.getName()).getkit2().getIcon())
				
		);
		player.openInventory(inventory);
					}
				
					

				
	
	public static String getInventoryName() {
		return inventoryName;
	}
	private static ItemStack randomGlass() {
		int randomId = new Random().nextInt(14);
		return new ItemStack(new ItemStack(Material.AIR));
	}
	
}
