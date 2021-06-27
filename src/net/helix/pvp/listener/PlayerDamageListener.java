package net.helix.pvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import net.helix.pvp.util.DamageUtil;

public class PlayerDamageListener implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		event.setCancelled(!DamageUtil.allowedDamage(player.getName(), DamageUtil.DamageType.NATURAL));
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		event.setCancelled(!DamageUtil.allowedDamage(player.getName(), DamageUtil.DamageType.PLAYER));
	}

}
