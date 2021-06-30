package net.helix.pvp.inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.KitManager;

import org.bukkit.entity.Player;

public class KitsInventory {

	private final static String inventoryName = "§7§nLista de Kits";
	
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
		
		KitManager.getPlayer(player.getName()).getAvailableKits().forEach(kit -> {
			long playerCount = KitManager.getPlayers().stream().filter(
					playerPvP -> playerPvP.hasKit(kit)).count();
			
			List<String> lore = new ArrayList<>();
			if (playerCount > 0) {
				lore.add("§f" + playerCount + " §fjogando este kit.");
			}
			lore.add("§eClique para selecionar.");
			
			inventory.addItem(new ItemBuilder("§a" + kit.getName(), kit.getIcon())
					.lore(lore)
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("kit-gui", kit.getName())
					.toStack()
			);
		});
		
		player.openInventory(inventory);
	}
	
	public static String getInventoryName() {
		return inventoryName;
	}
	
	private static ItemStack randomGlass() {
		int randomId = new Random().nextInt(14);
		return new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)randomId);
	}
	
}
