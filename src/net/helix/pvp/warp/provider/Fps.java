package net.helix.pvp.warp.provider;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.util.DamageUtil;
import net.helix.pvp.warp.WarpHandle;

public class Fps extends WarpHandle {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		DamageUtil.allowAllDamage(player.getName());
		
		player.getInventory().setItem(0, new ItemBuilder("§fEspada de Pedra", Material.STONE_SWORD)
				.nbt("cancel-drop")
				.toStack()
		);
		
		for (int i = 0; i < 36; i++) {
			player.getInventory().addItem(new ItemStack(Material.MUSHROOM_SOUP));
		}
	}
}
