package net.helix.pvp.warp.provider;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.warp.WarpHandle;

public class FPS extends WarpHandle {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(0, new ItemBuilder("§bSword", Material.DIAMOND_SWORD)
				.nbt("cancel-drop").addEnchant(Enchantment.DAMAGE_ALL, 1)
				.toStack()
		);
		KitManager.getPlayer(player.getName()).removeKit();
		KitManager2.getPlayer(player.getName()).removekit2(); 
		player.getInventory().setHelmet(new ItemBuilder("§6§lPVP", Material.IRON_HELMET).toStack());
		player.getInventory().setChestplate(new ItemBuilder("§6PVP", Material.IRON_CHESTPLATE).toStack());
		player.getInventory().setLeggings(new ItemBuilder("§6PVP", Material.IRON_LEGGINGS).toStack());
		player.getInventory().setBoots(new ItemBuilder("§6PVP", Material.IRON_BOOTS).toStack());
		
		ItemStack vermelho = new ItemStack(Material.RED_MUSHROOM, 64);
		
		KitManager.getPlayer(player.getName()).removeKit();
		KitManager2.getPlayer(player.getName()).removekit2();
		  ItemStack marrom = new ItemStack(Material.BROWN_MUSHROOM, 64);
		  
		  ItemStack marrom2 = new ItemStack(Material.INK_SACK, 64 ,(short)3);;
		  
		  ItemStack item = new ItemStack(Material.BOWL, 64);
		  player.getInventory().setItem(14, vermelho);
		  player.getInventory().setItem(15, marrom);
		  player.getInventory().setItem(16, marrom2);
		  player.getInventory().setItem(13, item);
		  for (int i = 0; i < 36; i++) {
				player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
			}
	}
}