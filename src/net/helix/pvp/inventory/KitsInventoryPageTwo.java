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

public class KitsInventoryPageTwo {

	private final static String inventoryName = "Kits Primários 2";
	public static void open(Player player) {
		Inventory inventory = Bukkit.createInventory(null, 6 * 9, inventoryName);
	
		if (player.hasPermission("kombo.kit.fireman")) {
			inventory.setItem(10 , new ItemBuilder("§a" + HelixKit.FIREMAN.getName(), HelixKit.FIREMAN.getIcon())
					.lore("§f" + HelixKit.FIREMAN.getDescription())
							.addFlags(ItemFlag.HIDE_ATTRIBUTES,
									ItemFlag.HIDE_DESTROYS,
									ItemFlag.HIDE_ENCHANTS,
									ItemFlag.HIDE_PLACED_ON,
									ItemFlag.HIDE_POTION_EFFECTS,
									ItemFlag.HIDE_UNBREAKABLE)
							.nbt("kit-gui", HelixKit.FIREMAN.getName())
							.toStack()
					);
			}
		if (player.hasPermission("kombo.kit.leech")) {
		inventory.setItem(11 , new ItemBuilder("§a" + HelixKit.LEECH.getName(), HelixKit.LEECH.getIcon())
				.lore("§f" + HelixKit.LEECH.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.LEECH.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.milkman")) {
		inventory.setItem(12 , new ItemBuilder("§a" + HelixKit.MILKMAN.getName(), HelixKit.MILKMAN.getIcon())
				.lore("§f" + HelixKit.MILKMAN.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.MILKMAN.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.monk")) {
		inventory.setItem(13 , new ItemBuilder("§a" + HelixKit.MONK.getName(), HelixKit.MONK.getIcon())
				.lore("§f" + HelixKit.MONK.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.MONK.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.neo")) {
		inventory.setItem(14 , new ItemBuilder("§a" + HelixKit.NEO.getName(), HelixKit.NEO.getIcon())
				.lore("§f" + HelixKit.NEO.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.NEO.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.ninja")) {
		inventory.setItem(15 , new ItemBuilder("§a" + HelixKit.NINJA.getName(), HelixKit.NINJA.getIcon())
				.lore("§f" + HelixKit.NINJA.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.NINJA.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.phantom")) {
		inventory.setItem(16 , new ItemBuilder("§a" + HelixKit.PHANTOM.getName(), HelixKit.PHANTOM.getIcon())
				.lore("§f" + HelixKit.PHANTOM.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.PHANTOM.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.poseidon")) {
		inventory.setItem(19 , new ItemBuilder("§a" + HelixKit.POSEIDON.getName(), HelixKit.POSEIDON.getIcon())
				.lore("§f" + HelixKit.POSEIDON.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.POSEIDON.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.antistomper")) {
		inventory.setItem(20 , new ItemBuilder("§a" + HelixKit.ANTISTOMPER.getName(), HelixKit.ANTISTOMPER.getIcon())
				.lore("§f" + HelixKit.ANTISTOMPER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.ANTISTOMPER.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.scout")) {
		inventory.setItem(21 , new ItemBuilder("§a" + HelixKit.SCOUT.getName(), HelixKit.SCOUT.getIcon())
				.lore("§f" + HelixKit.SCOUT.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.SCOUT.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.sight")) {
		inventory.setItem(22 , new ItemBuilder("§a" + HelixKit.SIGHT.getName(), HelixKit.SIGHT.getIcon())
				.lore("§f" + HelixKit.SCOUT.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.SIGHT.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.snail")) {
		inventory.setItem(23 , new ItemBuilder("§a" + HelixKit.SNAIL.getName(), HelixKit.SNAIL.getIcon())
				.lore("§f" + HelixKit.SNAIL.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.SNAIL.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.sonic")) {
		inventory.setItem(24 , new ItemBuilder("§a" + HelixKit.SONIC.getName(), HelixKit.SONIC.getIcon())
				.lore("§f" + HelixKit.SNAIL.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.SONIC.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.stomper")) {
		inventory.setItem(25 , new ItemBuilder("§a" + HelixKit.STOMPER.getName(), HelixKit.STOMPER.getIcon())
				.lore("§f" + HelixKit.STOMPER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.STOMPER.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.switcher")) {
		inventory.setItem(28 , new ItemBuilder("§a" + HelixKit.SWITCHER.getName(), HelixKit.SWITCHER.getIcon())
				.lore("§f" + HelixKit.SWITCHER.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.SWITCHER.getName())
						.toStack()
				);
		}
		inventory.setItem(29 , new ItemBuilder("§a" + HelixKit.THOR.getName(), HelixKit.THOR.getIcon())
				.lore("§f" + HelixKit.THOR.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.THOR.getName())
						.toStack()
				);
		if (player.hasPermission("kombo.kit.thresh")) {
		inventory.setItem(30 , new ItemBuilder("§a" + HelixKit.THRESH.getName(), HelixKit.THRESH.getIcon())
				.lore("§f" + HelixKit.THRESH.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.THRESH.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.timelord")) {
		inventory.setItem(31 , new ItemBuilder("§a" + HelixKit.TIMELORD.getName(), HelixKit.TIMELORD.getIcon())
				.lore("§f" + HelixKit.TIMELORD.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.TIMELORD.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.tornado")) {
		inventory.setItem(32 , new ItemBuilder("§a" + HelixKit.TORNADO.getName(), HelixKit.TORNADO.getIcon())
				.lore("§f" + HelixKit.TORNADO.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.TORNADO.getName())
						.toStack()
				);
		}
		if (player.hasPermission("kombo.kit.turtle")) {
		inventory.setItem(33 , new ItemBuilder("§a" + HelixKit.TURTLE.getName(), HelixKit.TURTLE.getIcon())
				.lore("§f" + HelixKit.TURTLE.getDescription())
						.addFlags(ItemFlag.HIDE_ATTRIBUTES,
								ItemFlag.HIDE_DESTROYS,
								ItemFlag.HIDE_ENCHANTS,
								ItemFlag.HIDE_PLACED_ON,
								ItemFlag.HIDE_POTION_EFFECTS,
								ItemFlag.HIDE_UNBREAKABLE)
						.nbt("kit-gui", HelixKit.TURTLE.getName())
						.toStack()
				);
		}
				inventory.setItem(53, new ItemBuilder("§aPróximo", Material.ARROW).nbt("prox")
						.toStack()
				);
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

