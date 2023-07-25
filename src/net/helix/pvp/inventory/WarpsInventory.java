package net.helix.pvp.inventory;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.warp.HelixWarp;

public class WarpsInventory {

	private final static String inventoryName = HelixPvP.getInstance().getConfig().getString("WarpsInv").replace("&", "§");
	private static ItemStack randomGlass() {
		int randomId = new Random().nextInt(14);
		return new ItemStack(Material.VINE, 1);
	}
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 9, inventoryName);
		

		HelixWarp.getWarps().stream().filter(warp -> warp != HelixWarp.SPAWN).forEach(warp -> {
			inventory.addItem(new ItemBuilder("§e" + warp.getName(), warp.getIcon())
					.lore("§bPlayers: §f" + warp.getPlayerCount())
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
