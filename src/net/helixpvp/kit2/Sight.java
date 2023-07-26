package net.helixpvp.kit2;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager2;

public class Sight extends KitHandler2 {
	
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
		
		if (percentage < 33) {
			victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 4 * 20, 1));
		}
	}
}
