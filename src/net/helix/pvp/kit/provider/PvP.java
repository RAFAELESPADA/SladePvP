package net.helix.pvp.kit.provider;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.KitHandler;

public class PvP extends KitHandler {
	
	@Override
	public void execute(Player player) {
		super.execute(player);
		
		player.getInventory().setItem(0, new ItemBuilder("§fEspada de Pedra", Material.STONE_SWORD)
				.addEnchant(Enchantment.DAMAGE_ALL, 1)
				.nbt("cancel-drop")
				.toStack()
		);
	}

}
