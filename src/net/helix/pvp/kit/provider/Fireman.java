package net.helix.pvp.kit.provider;

import java.util.Random;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Fireman extends KitHandler {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Block block = player.getLocation().getBlock();
		
		if (KitManager.getPlayer(player.getName()).hasKit(this) 
				&& block.getType().toString().contains("WATER")) {
			player.damage(1.5);
		}
	}
	
	@EventHandler
	public void onDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		
		if (KitManager.getPlayer(player.getName()).hasKit(this) 
				&& event.getCause().equals(DamageCause.LAVA) 
				|| event.getCause().equals(DamageCause.FIRE) 
				|| event.getCause().equals(DamageCause.FIRE_TICK)) {
			event.setCancelled(true);
		}
	}
	
	@EventHandler(ignoreCancelled = true)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		
		Player damager = (Player) event.getDamager();
		if (!KitManager.getPlayer(damager.getName()).hasKit(this)) {
			return;
		}
		
		Player victim = (Player) event.getEntity();
		int percentage = new Random().nextInt(100);
		
		if (percentage < 27) {
			victim.setFireTicks(50);
		}
	}
}
