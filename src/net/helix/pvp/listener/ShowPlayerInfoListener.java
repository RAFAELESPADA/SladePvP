package net.helix.pvp.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.inventivetalent.bossbar.BossBar;
import org.inventivetalent.bossbar.BossBarAPI;

import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.md_5.bungee.api.chat.TextComponent;

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

			HelixKit kit = KitManager.getPlayer(victim.getName()).getKit();
			HelixKit2 kit2 = KitManager2.getPlayer(victim.getName()).getkit2();
			BossBar bossBar = BossBarAPI.addBar(damager, // The receiver of the BossBar
				      new TextComponent("§e" + victim.getName() + " §8- §fKit: §a" + kit.getName() + " §fKit2: §a" + kit2.getName()), // Displayed message
				      BossBarAPI.Color.BLUE, // Color of the bar
				      BossBarAPI.Style.NOTCHED_20, // Bar style
				      1.0f, // Progress (0.0 - 1.0)
				      7, // Timeout
				      2);
			
		}
	}
}
