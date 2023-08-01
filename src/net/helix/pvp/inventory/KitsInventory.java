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
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.warp.HelixWarp;

public class KitsInventory {

	private final static String inventoryName = "Kits Primários";
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
		ItemStack visualItem = new ItemStack(randomGlass());
		int[] arr = new int[]{19,20,21,22,23,24,25,28,29,30,31,32,33,34,37,38,39,40,41,42,43}; 
	
		if (player.hasPermission("kombo.kit.anchor")) {
	inventory.setItem(10 , new ItemBuilder("§a" + HelixKit.ANCHOR.getName(), HelixKit.ANCHOR.getIcon())
			.lore("§f" + HelixKit.ANCHOR.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.ANCHOR.getName())
					.toStack()
			);
		}
	inventory.setItem(11 , new ItemBuilder("§a" + HelixKit.PVP.getName(), HelixKit.PVP.getIcon())
			.lore("§f" + HelixKit.PVP.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE).addEnchant(Enchantment.DAMAGE_ALL, 1)
					.nbt("kit-gui", HelixKit.PVP.getName())
					.toStack()
			);
	inventory.setItem(12 , new ItemBuilder("§a" + HelixKit.ARCHER.getName(), HelixKit.ARCHER.getIcon())
			.lore("§f" + HelixKit.ARCHER.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.ARCHER.getName())
					.toStack()
			);
	if (player.hasPermission("kombo.kit.avatar")) {
	inventory.setItem(13 , new ItemBuilder("§a" + HelixKit.AVATAR.getName(), HelixKit.AVATAR.getIcon())
			.lore("§f" + HelixKit.AVATAR.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.AVATAR.getName())
					.toStack()
			);
	}
	if (player.hasPermission("kombo.kit.barbarian")) {
	inventory.setItem(14 , new ItemBuilder("§a" + HelixKit.BARBARIAN.getName(), HelixKit.BARBARIAN.getIcon())
			.lore("§f" + HelixKit.BARBARIAN.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.BARBARIAN.getName())
					.toStack()
			);
	}
	if (player.hasPermission("kombo.kit.bloodgun")) {
	inventory.setItem(15 , new ItemBuilder("§a" + HelixKit.BLOODGUN.getName(), HelixKit.BLOODGUN.getIcon())
			.lore("§f" + HelixKit.BLOODGUN.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.BLOODGUN.getName())
					.toStack()
			);
	}
	if (player.hasPermission("kombo.kit.boxer")) {
	inventory.setItem(16 , new ItemBuilder("§a" + HelixKit.BOXER.getName(), HelixKit.BOXER.getIcon())
			.lore("§f" + HelixKit.BOXER.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.BOXER.getName())
					.toStack()
			);
	}
	if (player.hasPermission("kombo.kit.camel")) {
	inventory.setItem(19 , new ItemBuilder("§a" + HelixKit.CAMEL.getName(), HelixKit.CAMEL.getIcon())
			.lore("§f" + HelixKit.CAMEL.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.CAMEL.getName())
					.toStack()
			);
	}
	if (player.hasPermission("kombo.kit.critical")) {
	inventory.setItem(20 , new ItemBuilder("§a" + HelixKit.CRITICAL.getName(), HelixKit.CRITICAL.getIcon())
			.lore("§f" + HelixKit.CRITICAL.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.CRITICAL.getName())
					.toStack()
			);
	}
	if (player.hasPermission("kombo.kit.deshfire")) {
	inventory.setItem(21 , new ItemBuilder("§a" + HelixKit.DESHFIRE.getName(), HelixKit.DESHFIRE.getIcon())
			.lore("§f" + HelixKit.DESHFIRE.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.DESHFIRE.getName())
					.toStack()
			);
	}
	if (player.hasPermission("kombo.kit.endermage")) {
	inventory.setItem(22 , new ItemBuilder("§a" + HelixKit.ENDERMAGE.getName(), HelixKit.ENDERMAGE.getIcon())
			.lore("§f" + HelixKit.ENDERMAGE.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.ENDERMAGE.getName())
					.toStack()
			);
	}
	if (player.hasPermission("kombo.kit.firebender")) {
	inventory.setItem(23 , new ItemBuilder("§a" + HelixKit.FIREBENDER.getName(), HelixKit.FIREBENDER.getIcon())
			.lore("§f" + HelixKit.FIREBENDER.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.FIREBENDER.getName())
					.toStack()
			);
	}
	inventory.setItem(24 , new ItemBuilder("§a" + HelixKit.KANGAROO.getName(), HelixKit.KANGAROO.getIcon())
			.lore("§f" + HelixKit.KANGAROO.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.KANGAROO.getName())
					.toStack()
			);
	if (player.hasPermission("kombo.kit.fisherman")) {
	inventory.setItem(25 , new ItemBuilder("§a" + HelixKit.FISHERMAN.getName(), HelixKit.FISHERMAN.getIcon())
			.lore("§f" + HelixKit.FISHERMAN.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.FISHERMAN.getName())
					.toStack()
			);
	}
	inventory.setItem(28 , new ItemBuilder("§a" + HelixKit.FLASH.getName(), HelixKit.FLASH.getIcon())
			.lore("§f" + HelixKit.FLASH.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.FLASH.getName())
					.toStack()
			);
	if (player.hasPermission("kombo.kit.gladiator")) {
	inventory.setItem(29 , new ItemBuilder("§a" + HelixKit.GLADIATOR.getName(), HelixKit.GLADIATOR.getIcon())
			.lore("§f" + HelixKit.GLADIATOR.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.GLADIATOR.getName())
					.toStack()
			);
	}
	if (player.hasPermission("kombo.kit.grandpa")) {
	inventory.setItem(30 , new ItemBuilder("§a" + HelixKit.GRANDPA.getName(), HelixKit.GRANDPA.getIcon())
			.lore("§f" + HelixKit.GRANDPA.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.GRANDPA.getName())
					.toStack()
			);
	}
	if (player.hasPermission("kombo.kit.grappler")) {
	inventory.setItem(31 , new ItemBuilder("§a" + HelixKit.GRAPPLER.getName(), HelixKit.GRAPPLER.getIcon())
			.lore("§f" + HelixKit.GRAPPLER.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.GRAPPLER.getName())
				
					.toStack()
			);
	}
	if (player.hasPermission("kombo.kit.hulk")) {
	inventory.setItem(32 , new ItemBuilder("§a" + HelixKit.HULK.getName(), HelixKit.HULK.getIcon())
			.lore("§f" + HelixKit.HULK.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.HULK.getName())
					.toStack()
			);
	}
	if (player.hasPermission("kombo.kit.hotpotato")) {
	inventory.setItem(33 , new ItemBuilder("§a" + HelixKit.HOTPOTATO.getName(), HelixKit.HOTPOTATO.getIcon())
			.lore("§f" + HelixKit.HOTPOTATO.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.HOTPOTATO.getName())
					.toStack()
			);
	}
	if (player.hasPermission("kombo.kit.jumper")) {
	inventory.setItem(34 , new ItemBuilder("§a" + HelixKit.JUMPER.getName(), HelixKit.JUMPER.getIcon())
			.lore("§f" + HelixKit.JUMPER.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", HelixKit.JUMPER.getName())
					.toStack()
			);
	}
		inventory.setItem(53, new ItemBuilder("§aPróximo", Material.ARROW).nbt("prox")
				.toStack()
		);
		inventory.setItem(4, new ItemBuilder("§aVisualizar todos os kits", Material.CHEST).nbt("visuali")
				.toStack()
		);
		ItemStack i =  new ItemStack(KitManager.getPlayer(player.getName()).getKit().getIcon());
		ItemMeta i2 = i.getItemMeta();
	    ArrayList<String> lore = new ArrayList<String>();
	    lore.add("§f" + KitManager.getPlayer(player.getName()).getKit().getDescription());
		i2.setDisplayName(KitManager.getPlayer(player.getName()).getKit().getName());
		i2.setLore(lore);
		i.setItemMeta(i2);
		if (KitManager2.getPlayer(player.getName()).getkit2() != HelixKit2.NENHUM) {
			i2.setDisplayName(KitManager2.getPlayer(player.getName()).getkit2().getName());
		    }
			else {
				i2.setDisplayName("§eNenhum kit selecionado.");	
			}
		inventory.setItem(49, new ItemBuilder(i).nbt("visual")
				.toStack()
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
