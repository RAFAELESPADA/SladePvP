package net.helix.pvp.inventory;

import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.HelixKit;

public class ShopInventory {
	
	private final static String inventoryName = "§7§nLoja de Kits";
	
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)4);
		
		
		List<HelixKit> availableKits = HelixKit.getKits().stream().filter(
				kit -> !kit.isFree() && !player.hasPermission("helix.kit." + kit.toString().toLowerCase())
		).collect(Collectors.toList());
		
		if (availableKits.size() > 0) {
			for (int i = 0; i <= 8; i++) {
				inventory.setItem(i, glass);
			}
			
			for (int i = 45; i <= 53; i++) {
				inventory.setItem(i, glass);
			}
			
			for (int i = 0; i <= 45; i += 9) {
				inventory.setItem(i, glass);
			}
			
			for (int i = 8; i <= 53; i += 9) {
				inventory.setItem(i, glass);
			}
			
			availableKits.forEach(kit -> {
				inventory.addItem(new ItemBuilder("§a" + kit.getName(), kit.getIcon())
						.lore("§fPreço: §e" + kit.getPrice(),
								"",
								"§aClique para comprar.")
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("shop-kit", kit.getName())
						.toStack()
				);
			});
		}else {
			inventory.setItem(31, new ItemBuilder("§cVocê já possui todos os kits", Material.BARRIER)
					.toStack()
				
			);
		}
		
		player.openInventory(inventory);
	}
	
	
	public static String getInventoryName() {
		return inventoryName;
	}

}
