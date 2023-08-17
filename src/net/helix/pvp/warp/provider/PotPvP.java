package net.helix.pvp.warp.provider;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.helix.pvp.warp.WarpHandle;

public class PotPvP extends WarpHandle {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(0, new ItemBuilder("§fEspada de Diamante", Material.DIAMOND_SWORD)
				.nbt("cancel-drop").addEnchant(Enchantment.DAMAGE_ALL, 1)
				.toStack()
		);
		KitManager.getPlayer(player.getName()).removeKit();
		KitManager2.getPlayer(player.getName()).removekit2(); 
		player.getInventory().setHelmet(new ItemBuilder("§6§lPVP", Material.IRON_HELMET).toStack());
		player.getInventory().setChestplate(new ItemBuilder("§6PVP", Material.IRON_CHESTPLATE).toStack());
		player.getInventory().setLeggings(new ItemBuilder("§6PVP", Material.IRON_LEGGINGS).toStack());
		player.getInventory().setBoots(new ItemBuilder("§6PVP", Material.IRON_BOOTS).toStack());
		
		  ItemStack sopa = new ItemStack(Material.POTION, 1, (short)16421);
		    ItemMeta sopas = sopa.getItemMeta();
		    sopas.setDisplayName("§ePotinho Magico");
		    sopa.setItemMeta(sopas);
		KitManager.getPlayer(player.getName()).removeKit();
		KitManager2.getPlayer(player.getName()).removekit2();
		  for (int i = 0; i < 36; i++) {
				player.getInventory().addItem(sopa);
			}
	}
}