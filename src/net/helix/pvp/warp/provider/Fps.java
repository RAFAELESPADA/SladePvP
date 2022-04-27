package net.helix.pvp.warp.provider;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.core.bukkit.util.DamageUtil;
import net.helix.pvp.warp.WarpHandle;

public class Fps extends WarpHandle {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		DamageUtil.allowAllDamage(player.getName());
		
		player.getInventory().setItem(0, new ItemBuilder("§fEspada de Diamante", Material.DIAMOND_SWORD)
				.nbt("cancel-drop").addEnchant(Enchantment.DAMAGE_ALL, 1)
				.toStack()
		);
		ItemStack vermelho = new ItemStack(Material.RED_MUSHROOM, 64);

		  
		  ItemStack marrom = new ItemStack(Material.BROWN_MUSHROOM, 64);

		  
		  ItemStack item = new ItemStack(Material.BOWL, 64);

		
		  player.getInventory().setItem(14, vermelho);
		  player.getInventory().setItem(15, marrom);
		  player.getInventory().setItem(13, item);
		player.getInventory().setHelmet(new ItemBuilder("§6§lSLOPER§f§lPVP", Material.IRON_HELMET).toStack());
		player.getInventory().setChestplate(new ItemBuilder("§6§lSLOPER§f§lPVP", Material.IRON_CHESTPLATE).toStack());
		player.getInventory().setLeggings(new ItemBuilder("§6§lSLOPER§f§lPVP", Material.IRON_LEGGINGS).toStack());
		player.getInventory().setBoots(new ItemBuilder("§6§lSLOPER§f§lPVP", Material.IRON_BOOTS).toStack());
		
		for (int i = 0; i < 36; i++) {
			player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
	}
}
