package net.helix.pvp.inventory;



import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;

public class KitInventory22 {

	private final static String inventoryName = "Kits Secundários 2";
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
	
		ItemStack visualItem = new ItemStack(randomGlass());
		
		inventory.setItem(10 , new ItemBuilder("§a" + HelixKit2.SWITCHER.getName(), HelixKit2.SWITCHER.getIcon())
				.lore("§f" + HelixKit2.SWITCHER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.SWITCHER.getName())
						.toStack()
				);
		inventory.setItem(11 , new ItemBuilder("§a" + HelixKit2.THOR.getName(), HelixKit2.THOR.getIcon())
				.lore("§f" + HelixKit2.THOR.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.THOR.getName())
						.toStack()
				);
		inventory.setItem(12 , new ItemBuilder("§a" + HelixKit2.VACUUM.getName(), HelixKit2.VACUUM.getIcon())
				.lore("§f" + HelixKit2.VACUUM.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.VACUUM.getName())
						.toStack()
				);
		inventory.setItem(13 , new ItemBuilder("§a" + HelixKit2.VIPER.getName(), HelixKit2.VIPER.getIcon())
				.lore("§f" + HelixKit2.VIPER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.VIPER.getName())
						.toStack()
				);
		inventory.setItem(14 , new ItemBuilder("§a" + HelixKit2.WATERBENDER.getName(), HelixKit2.WATERBENDER.getIcon())
				.lore("§f" + HelixKit2.WATERBENDER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui2", HelixKit2.WATERBENDER.getName())
						.toStack()
				);
		inventory.setItem(45, new ItemBuilder("§aVoltar", Material.ARROW).nbt("voltar")
				.toStack()
		);
		ItemStack i =  new ItemStack(KitManager2.getPlayer(player.getName()).getkit2().getIcon());
		ItemMeta i2 = i.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
	    lore.add(KitManager2.getPlayer(player.getName()).getkit2().getDescription());
		i2.setDisplayName(KitManager2.getPlayer(player.getName()).getkit2().getName());
		i2.setLore(lore);
		i.setItemMeta(i2);
		inventory.setItem(49, new ItemStack(KitManager2.getPlayer(player.getName()).getkit2().getIcon()));
		player.openInventory(inventory);
			}
		

					
				
	
	public static String getInventoryName() {
		return inventoryName;
	}
	private static ItemStack randomGlass() {
		int randomId = new Random().nextInt(14);
		return new ItemStack(new ItemStack(Material.AIR));
	}
	
}

