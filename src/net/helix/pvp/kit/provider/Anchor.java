package net.helix.pvp.kit.provider;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Anchor extends KitHandler {
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		
		Player damager = (Player) event.getDamager();
		Player victim = (Player) event.getEntity();
		
		if (KitManager.getPlayer(damager.getName()).hasKit(this) 
				|| KitManager.getPlayer(victim.getName()).hasKit(this)) {
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
