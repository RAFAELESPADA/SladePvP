package net.helix.pvp.warp.provider;

import org.bukkit.Material;
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
		
		player.getInventory().setItem(0, new ItemBuilder("§fEspada de Diamante", Material.DIAMOND_SWORD)
				.nbt("cancel-drop")
				.toStack()
		);
		KitManager.getPlayer(player.getName()).removeKit();
		KitManager2.getPlayer(player.getName()).removekit2();
		player.getInventory().setHelmet(new ItemBuilder("§6§lPVP", Material.IRON_HELMET).toStack());
		player.getInventory().setChestplate(new ItemBuilder("§6PVP", Material.IRON_CHESTPLATE).toStack());
		player.getInventory().setLeggings(new ItemBuilder("§6PVP", Material.IRON_LEGGINGS).toStack());
		player.getInventory().setBoots(new ItemBuilder("§6PVP", Material.IRON_BOOTS).toStack());
		
		for (int i = 0; i < 36; i++) {
			player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
	}
}