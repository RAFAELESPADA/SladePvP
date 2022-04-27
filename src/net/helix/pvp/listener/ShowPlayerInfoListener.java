package net.helix.pvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import net.helix.core.account.HelixRole;
import net.helix.core.bukkit.HelixBukkit;
import net.helix.core.bukkit.api.HelixActionBar;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.KitManager;

public class ShowPlayerInfoListener implements Listener {
	
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		Player victim = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		
		if (KitManager.getPlayer(victim.getName()).hasKit() 
				&& KitManager.getPlayer(damager.getName()).hasKit()) {
			HelixRole tag = HelixBukkit.getInstance().getPlayerManager()
					.getPlayer(victim.getName()).getTag();
			HelixKit kit = KitManager.getPlayer(victim.getName()).getKit();
			HelixActionBar.send(damager, tag.getColor() + victim.getName() + " §8- §fKit: §a" + kit.getName());
		}
	}
}
