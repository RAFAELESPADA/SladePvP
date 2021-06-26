package net.helix.pvp.warp.provider;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.warp.WarpHandle;

public class Knockback extends WarpHandle {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setHeldItemSlot(4);
		player.getInventory().setItem(4, new ItemBuilder("§aVarinha Mágica", Material.STICK)
				.addEnchant(Enchantment.KNOCKBACK, 5)
				.nbt("cancel-drop")
				.toStack()
		);
	}
}
