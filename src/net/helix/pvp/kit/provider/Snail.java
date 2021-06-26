package net.helix.pvp.kit.provider;

import java.util.Random;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Snail extends KitHandler {
	
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
		
		if (percentage < 33) {
			victim.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 4 * 20, 1));
		}
	}
}
