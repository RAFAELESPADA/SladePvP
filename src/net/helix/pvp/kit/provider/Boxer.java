package net.helix.pvp.kit.provider;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import net.helix.core.bukkit.item.ItemBuilder;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;

public class Boxer extends KitHandler {

	 @Override
		public void execute(Player player) {
			super.execute(player);
		}

	 @EventHandler
		public void onDamage(EntityDamageEvent event) {
			if (!(event.getEntity() instanceof Player)) {
				return;
			}
			 Player rightClicked = (Player) event.getEntity();
			 if (!KitManager.getPlayer(rightClicked.getName()).hasKit(this) || event.getCause() == EntityDamageEvent.DamageCause.VOID)
			      return; 
		     event.setDamage(event.getDamage() - 0.4);
		}
		@EventHandler
		public void onDamage2(EntityDamageByEntityEvent event) {
			if (!(event.getEntity() instanceof Player)) {
				return;
			}
			 if (!KitManager.getPlayer(event.getDamager().getName()).hasKit(this) || event.getCause() == EntityDamageEvent.DamageCause.VOID)
			      return; 
		     event.setDamage(event.getDamage() + 1.8);
		}
}