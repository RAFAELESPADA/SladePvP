package net.helix.pvp.kit.provider;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.KitHandler;

public class AntiStomper extends KitHandler {

@Override
public void execute(Player player) {
	super.execute(player);
	
	Habilidade.setAbility(player, "SteelHead");
}
}
