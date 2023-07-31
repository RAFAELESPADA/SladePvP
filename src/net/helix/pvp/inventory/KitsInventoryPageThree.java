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
import net.helix.pvp.kit.KitManager;

public class KitsInventoryPageThree {

	private final static String inventoryName = "Kits Primários 3";
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
	
		if (player.hasPermission("kombo.kit.vacuum")) {
		inventory.setItem(10 , new ItemBuilder("§a" + HelixKit.VACUUM.getName(), HelixKit.VACUUM.getIcon())
				.lore("§f" + HelixKit.VACUUM.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.VACUUM.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.viper")) {
		inventory.setItem(11 , new ItemBuilder("§a" + HelixKit.VIPER.getName(), HelixKit.VIPER.getIcon())
				.lore("§f" + HelixKit.VIPER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.VIPER.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.waterbender")) {
		inventory.setItem(12 , new ItemBuilder("§a" + HelixKit.WATERBENDER.getName(), HelixKit.WATERBENDER.getIcon())
				.lore("§f" + HelixKit.WATERBENDER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.WATERBENDER.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.meteor")) {
			inventory.setItem(13 , new ItemBuilder("§a" + HelixKit.METEOR.getName(), HelixKit.METEOR.getIcon())
					.lore("§f" + HelixKit.METEOR.getDescription())
							.addFlags(ItemFlag.HIDE_ATTRIBUTES,
									ItemFlag.HIDE_DESTROYS,
									ItemFlag.HIDE_ENCHANTS,
									ItemFlag.HIDE_PLACED_ON,
									ItemFlag.HIDE_POTION_EFFECTS,
									ItemFlag.HIDE_UNBREAKABLE)
							.nbt("kit-gui", HelixKit.METEOR.getName())
							.toStack()
					);
			}
			
			
				inventory.setItem(45, new ItemBuilder("§aVoltar", Material.ARROW).nbt("voltar")
						.toStack()
				);
				inventory.setItem(4, new ItemBuilder("§aVisualizar todos os kits", Material.CHEST).nbt("visuali")
						.toStack()
				);
				ItemStack i =  new ItemStack(KitManager.getPlayer(player.getName()).getKit().getIcon());
				ItemMeta i2 = i.getItemMeta();
			    ArrayList<String> lore = new ArrayList<String>();
			    lore.add("§f" + KitManager.getPlayer(player.getName()).getKit().getDescription());
			    if (KitManager.getPlayer(player.getName()).getKit() != HelixKit.NENHUM) {
					i2.setDisplayName(KitManager.getPlayer(player.getName()).getKit().getName());
				    }
					else {
						i2.setDisplayName("§eNenhum kit selecionado.");	
					}
				i2.setLore(lore);
				i.setItemMeta(i2);
				inventory.setItem(49, new ItemBuilder(i).nbt("visual")
						.toStack()
				);
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
