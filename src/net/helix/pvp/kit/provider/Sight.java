package net.helix.pvp.kit.provider;

import java.util.Random;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.md_5.bungee.api.ChatColor;

public class Sight extends KitHandler {
	
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
		if (event.isCancelled()) {
			return;
		}
		Player victim = (Player) event.getEntity();
		int percentage = new Random().nextInt(100);
		
		if (percentage < 27) {
			victim.sendMessage(ChatColor.RED + "A sight removed your vision");
			victim.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 4 * 20, 1));
		}
	}
}
