package net.helixpvp.kit2;

import java.util.Random;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerMoveEvent;

import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager2;

public class Fireman extends KitHandler2 {
	
	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Block block = player.getLocation().getBlock();
		
		if (KitManager2.getPlayer(player.getName()).haskit2(this) 
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
		
		if (KitManager2.getPlayer(player.getName()).haskit2(this) 
				&& (event.getCause().equals(DamageCause.LAVA) 
				|| event.getCause().equals(DamageCause.FIRE) 
				|| event.getCause().equals(DamageCause.FIRE_TICK))) {
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
		if (!KitManager2.getPlayer(damager.getName()).haskit2(this)) {
			return;
		}
		
		Player victim = (Player) event.getEntity();
		int percentage = new Random().nextInt(100);
		
		if (percentage < 27) {
			victim.setFireTicks(50);
		}
	}
}

