package net.helix.pvp.kit.provider;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Boxer extends KitHandler {

	 @Override
		public void execute(Player player) {
			super.execute(player);
			player.getInventory().remove(Material.STONE_SWORD);;
			player.getInventory().setItem(0, new ItemBuilder("§bBoxer!", Material.QUARTZ)
					.nbt("kit-handler", "boxer")
					.nbt("cancel-drop")
					.toStack()
			);
		}

@EventHandler
public void onDamage(EntityDamageEvent event) {
	if (!(event.getEntity() instanceof Player)) {
		return;
	}
	 Player rightClicked = (Player) event.getEntity();
	 if (!KitManager.getPlayer(rightClicked.getName()).hasKit(this) || event.getCause() == EntityDamageEvent.DamageCause.VOID)
	      return; 
     event.setDamage(event.getDamage() - 0.8);
}
}