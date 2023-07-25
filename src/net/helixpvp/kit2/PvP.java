package net.helixpvp.kit2;


import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager;

public class PvP extends KitHandler2 {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		if (!KitManager.getPlayer(player.getName()).hasKit(HelixKit.PVP)) {
		player.getInventory().setItem(0, new ItemBuilder("§fSword", Material.STONE_SWORD)
				.addEnchant(Enchantment.DAMAGE_ALL, 1)
				.nbt("cancel-drop")
				.toStack()
		);
		}
	}

}
