package net.helix.pvp.inventory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.format.HelixDecimalFormat;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitManager;

public class ShopInventory {
	
	private final static String inventoryName = "PRIMARY KITS SHOP";
	private static ItemStack randomGlass() {
		int randomId = new Random().nextInt(14);
		return new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)randomId);
	}
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
		ItemStack visualItem = new ItemBuilder(HelixPvP.getInstance().getConfig().getString("ShopItem").replace("&", "§"), randomGlass()).toStack();
		
		
		List<HelixKit> availableKits = HelixKit.getKits().stream().filter(
				kit -> !KitManager.getPlayer(player.getName()).getAvailableKits().contains(kit) && !kit.equals(HelixKit.NENHUM)
		).collect(Collectors.toList());
		
		if (availableKits.size() > 0) {
			for (int i = 0; i <= 8; i++) {
				inventory.setItem(i, visualItem);
			}
			
			for (int i = 45; i <= 53; i++) {
				inventory.setItem(i, visualItem);
			}
			
			for (int i = 0; i <= 45; i += 9) {
				inventory.setItem(i, visualItem);
			}
			
			for (int i = 8; i <= 53; i += 9) {
				inventory.setItem(i, visualItem);
			}
			
			availableKits.forEach(kit -> {
				inventory.addItem(new ItemBuilder("§c" + kit.getName(), kit.getIcon())
						.lore("§7Buy for §e" + HelixDecimalFormat.format(kit.getPrice()) + " Coins")
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("shop-kit", kit.getName())
						.toStack());
			});
		}else {
			inventory.setItem(31, new ItemBuilder("§cYou already has all kits", Material.REDSTONE_BLOCK)
					.toStack()
			);
		}
		
		player.openInventory(inventory);
	}
	
	
	public static String getInventoryName() {
		return inventoryName;
	}

}
