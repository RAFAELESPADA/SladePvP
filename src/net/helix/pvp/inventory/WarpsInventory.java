package net.helix.pvp.inventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.warp.HelixWarp;
import org.bukkit.inventory.ItemStack;

public class WarpsInventory {

	private final static String inventoryName = "§bWarps do servidor";
	
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 3 * 9, inventoryName);
		ItemStack visualItem = new ItemBuilder("§6§lKOMBO§f§lPVP", Material.VINE).toStack();


		for (int i = 0; i <= 8; i++)  {
			inventory.setItem(i, visualItem);
		}


		inventory.setItem(9, visualItem);
		inventory.setItem(17, visualItem);

		for (int i = 18; i <= 26; i++)  {
			inventory.setItem(i, visualItem);
		}

		HelixWarp.getWarps().stream().filter(warp -> warp != HelixWarp.SPAWN).forEach(warp -> {
			inventory.addItem(new ItemBuilder("§2" + warp.getName(), warp.getIcon())
					.lore("§7Jogadores: §f" + warp.getPlayerCount())
					.nbt("warp-gui", warp.getName())
					.toStack()
			);
		});
		
		player.openInventory(inventory);
	}
	
	public static String getInventoryName() {
		return inventoryName;
	}
}
