package net.helix.pvp.kit.provider;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemFlag;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class AntiStomper extends KitHandler {

@Override
public void execute(Player player) {
	super.execute(player);
	
	Habilidade.setAbility(player, "SteelHead");
}

@EventHandler
public void onDamage(EntityDamageEvent event) {
	if (!(event.getEntity() instanceof Player)) {
		return;
	}
	Player player = (Player) event.getEntity();
	
	if (KitManager.getPlayer(player.getName()).hasKit(this) 
			&& (event.getCause().equals(DamageCause.PROJECTILE))) {
		event.setCancelled(true);
	}
}
}