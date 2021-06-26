package net.helix.pvp.kit.provider;

import java.util.Random;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Poseidon extends KitHandler {
	
	@EventHandler
	public void onDamage(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Block block = player.getLocation().getBlock();
		
		if (KitManager.getPlayer(player.getName()).hasKit(this) 
				&& block.getType().toString().contains("WATER")) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 7*20, 1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 9*20, 2));
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
