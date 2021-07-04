package net.helix.pvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import net.helix.pvp.util.DamageUtil;

public class PlayerDamageListener implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		if (!DamageUtil.allowedDamage(player.getName(), DamageUtil.DamageType.NATURAL) 
				&& event.getCause() != DamageCause.ENTITY_ATTACK) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		if (!DamageUtil.allowedDamage(player.getName(), DamageUtil.DamageType.PLAYER)) {
			event.setCancelled(true);
		}
	}

}
