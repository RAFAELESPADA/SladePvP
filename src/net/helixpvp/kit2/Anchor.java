package net.helixpvp.kit2;


import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager2;

public class Anchor extends KitHandler2 {
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		
		Player damager = (Player) event.getDamager();
		Player victim = (Player) event.getEntity();
		
		if (KitManager2.getPlayer(damager.getName()).haskit2(this) 
				|| KitManager2.getPlayer(victim.getName()).haskit2(this)) {
			damager.playSound(damager.getLocation(), Sound.ANVIL_LAND, 10.0f, 10.0f);
			victim.playSound(damager.getLocation(), Sound.ANVIL_LAND, 10.0f, 10.0f);
			
			new BukkitRunnable() {
				@Override
				public void run() {
					Vector vector = new Vector();
					damager.setVelocity(vector);
					victim.setVelocity(vector);
				}
			}.runTaskLater(HelixPvP.getInstance(), 1L);
		}
	}
}
