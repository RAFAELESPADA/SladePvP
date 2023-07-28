package net.helix.pvp.inventory;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.warp.HelixWarp;

public class KitsInventory {

	private final static String inventoryName = "Kits Primários";
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
		ItemStack visualItem = new ItemStack(randomGlass());
		int[] arr = new int[]{19,20,21,22,23,24,25,28,29,30,31,32,33,34,37,38,39,40,41,42,43};
		KitManager.getPlayer(player.getName()).getAvailableKits().forEach(kit -> { 
	if (kit.getPage() == 1 && kit != HelixKit.NENHUM) {
{
	for (int i = 0; i < 18; i++) {
		inventory.setItem(i, visualItem);
	}
	for (int i = 0; i <= 45 && i != 18 && i != 27; i += 9) {
		inventory.setItem(i, visualItem);
	}
	for (int i = 0; i < 44 && i > 32; i++) {
		inventory.setItem(i, visualItem);
	}
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
	
		inventory.setItem(53, new ItemBuilder("§aPróximo", Material.ARROW).nbt("prox")
				.toStack()
		);
		inventory.setItem(45, new ItemBuilder("§aVoltar", Material.ARROW).nbt("voltar")
				.toStack()
		);
		player.openInventory(inventory);
	};
}}
				);
					}
		
					
	
		
				
	
	public static String getInventoryName() {
		return inventoryName;
	}
	private static ItemStack randomGlass() {
		int randomId = new Random().nextInt(14);
		return new ItemStack(Material.THIN_GLASS);
	}
	
}
