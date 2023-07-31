package net.helix.pvp.inventory;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitManager;

import org.bukkit.entity.Player;

public class TodosOsKits {

	private final static String inventoryName = "Todos os Kits do servidor";
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
	



		KitManager.getPlayer(player.getName()).getAvailableKits().forEach(kit ->  {
			if (!(kit.equals(HelixKit.NENHUM))) {
			inventory.addItem(new ItemBuilder("§6" + kit.getName(), kit.getIcon())
					.lore("§f" + kit.getDescription())
					.addFlags(ItemFlag.HIDE_ATTRIBUTES,
							ItemFlag.HIDE_DESTROYS,
							ItemFlag.HIDE_ENCHANTS,
							ItemFlag.HIDE_PLACED_ON,
							ItemFlag.HIDE_POTION_EFFECTS,
							ItemFlag.HIDE_UNBREAKABLE)
					.nbt("visao", kit.getName())
					.toStack()
			);
		}});
		
		player.openInventory(inventory);
	}
	public static String getInventoryName() {
		return inventoryName;
	}
}