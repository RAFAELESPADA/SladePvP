package net.helix.pvp.inventory;


import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitManager2;

public class KitsInventory2 {

	private final static String inventoryName = "Kits Secundários";
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
	

		for (int i = 0; i <= 8; i++) {
			inventory.setItem(i, randomGlass());
		}
		
		for (int i = 45; i <= 53; i++) {
			inventory.setItem(i, randomGlass());
		}

		KitManager2.getPlayer(player.getName()).getAvailablekit2s().forEach(kit ->  {
 
			if (!(kit.equals(HelixKit2.NENHUM))) {
			inventory.addItem(new ItemBuilder("§a" + kit.getName(), kit.getIcon())
					.lore("§f" + kit.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui2", kit.getName())
					.toStack()
			);
		};
	
		player.openInventory(inventory);
	});
	}
	
	public static String getInventoryName() {
		return inventoryName;
	}
	private static ItemStack randomGlass() {
		int randomId = new Random().nextInt(14);
		return new ItemStack(Material.STAINED_GLASS_PANE , 1 , (short)4);
	}
	
}
