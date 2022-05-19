package net.helix.pvp.inventory;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.warp.HelixWarp;
import org.bukkit.inventory.ItemStack;

public class WarpsInventory {

	private final static String inventoryName = "§bWarps do servidor";
	private static ItemStack randomGlass() {
		int randomId = new Random().nextInt(14);
		return new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)randomId);
	}
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 3 * 9, inventoryName);
		


		for (int i = 0; i <= 8; i++)  {
			inventory.setItem(i, randomGlass());
		}


		inventory.setItem(9, randomGlass());
		inventory.setItem(17, randomGlass());

		for (int i = 18; i <= 26; i++)  {
			inventory.setItem(i, randomGlass());
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
