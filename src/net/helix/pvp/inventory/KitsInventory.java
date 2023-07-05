package net.helix.pvp.inventory;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitManager;

public class KitsInventory {

	private final static String inventoryName = "Kits Primários";
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
	

		for (int i = 0; i <= 8; i++) {
			inventory.setItem(i, randomGlass());
		}
		
		for (int i = 45; i <= 53; i++) {
			inventory.setItem(i, randomGlass());
		}
		
		for (int i = 0; i <= 45; i += 9) {
			inventory.setItem(i, randomGlass());
		}
		
		for (int i = 8; i <= 53; i += 9) {
			inventory.setItem(i, randomGlass());
		}
		inventory.setItem(9, randomGlass());
		inventory.setItem(18, randomGlass());
		inventory.setItem(27, randomGlass());
		inventory.setItem(36, randomGlass());
		inventory.setItem(45, randomGlass());
		inventory.setItem(53, randomGlass());

		KitManager.getPlayer(player.getName()).getAvailableKits().forEach(kit ->  {
			if (!(kit.equals(HelixKit.NENHUM))) {
	
			inventory.addItem(new ItemBuilder("§a" + kit.getName(), kit.getIcon())
					.lore("§f" + kit.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", kit.getName())
					.toStack()
			);
		}});
		
		player.openInventory(inventory);
	}
	
	public static String getInventoryName() {
		return inventoryName;
	}
	private static ItemStack randomGlass() {
		int randomId = new Random().nextInt(14);
		return new ItemStack(Material.AIR);
	}
	
}
