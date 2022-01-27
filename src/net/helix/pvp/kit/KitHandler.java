package net.helix.pvp.kit;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import net.helix.core.bukkit.item.ItemBuilder;

public abstract class KitHandler implements Listener {

	public void execute(Player player) {
		player.setGameMode(GameMode.ADVENTURE);
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		player.setAllowFlight(false);
		player.setFlying(false);
		player.getActivePotionEffects().forEach(potion -> player.removePotionEffect(potion.getType()));
		player.getInventory().setHeldItemSlot(0);
		/* 348 */       ItemStack tunic = new ItemStack(Material.LEATHER_CHESTPLATE);
		/* 349 */       ItemMeta tunicmeta = tunic.getItemMeta();
		                tunicmeta.setDisplayName(ChatColor.YELLOW + "Peitoral de Couro");
		/* 350 */       LeatherArmorMeta meta11 = (LeatherArmorMeta)tunic.getItemMeta();
		/* 351 */       meta11.setColor(Color.ORANGE);
		/* 352 */       tunic.setItemMeta(meta11);
		                tunic.setItemMeta(tunicmeta);
		player.getInventory().setItem(0, new ItemBuilder("§7Espada de Pedra", Material.STONE_SWORD)
				.nbt("cancel-drop")
				.toStack()
		);
		player.getInventory().setChestplate(tunic);
		
		player.getInventory().setItem(8, new ItemBuilder("§9Jogadores próximos", Material.COMPASS)
				.nbt("kit-handler", "search-players")
				.nbt("cancel-drop")
				.toStack()
		);
	
		player.getInventory().setItem(13, new ItemStack(Material.BOWL, 32));
		player.getInventory().setItem(14, new ItemStack(Material.RED_MUSHROOM, 32));
		player.getInventory().setItem(15, new ItemStack(Material.BROWN_MUSHROOM, 32));
		
		for (int i = 0; i < 36; i++) {
			player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
	}
}
