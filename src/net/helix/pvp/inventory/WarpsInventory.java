package net.helix.pvp.inventory;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.warp.HelixWarp;

public class WarpsInventory {

	private final static String inventoryName = "§7§nWarps disponíveis";
	
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 3 * 9, inventoryName);
		
		int initialSlot = 10;
		int finalSlot = 16;
		for (int i = initialSlot; i < finalSlot; i++) {
			int index = (i - initialSlot);
			if ((HelixWarp.getWarps().size() - 1) < index) {
				break;
			}
			HelixWarp warp = HelixWarp.getWarps().get(index);
			
			inventory.setItem(i, new ItemBuilder("§a" + warp.getName(), warp.getIcon())
					.lore("§f" + warp.getPlayerCount() + " §7jogando.")
					.nbt("warp-gui", warp.getName())
					.toStack()
			);
		}
		
		player.openInventory(inventory);
	}
	
	public static String getInventoryName() {
		return inventoryName;
	}
}
