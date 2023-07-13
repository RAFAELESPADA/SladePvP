package net.helixpvp.kit2;



import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;


public class Ninja extends KitHandler2 {
	
	protected static final HashMap<String, String> map = new HashMap<>();
	
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		Player victim = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		
		if (KitManager2.getPlayer(damager.getName()).haskit2(this) 
				&& KitManager.getPlayer(victim.getName()).hasKit()) {
			map.put(damager.getName(), victim.getName());
		}
	}
	
	@EventHandler
	public void onSneaking(PlayerToggleSneakEvent event) {
		if (event.isSneaking()) return;
		Player player = event.getPlayer();

		if (KitManager2.getPlayer(player.getName()).haskit2(this) 
				&& map.containsKey(player.getName())) {
			
			
			String targetName = map.get(player.getName());
			Player targetPlayer;
			if (inCooldown(player) && KitManager2.getPlayer(player.getName()).haskit2(this)) {
				sendMessageCooldown(player);
				return;
			}
			if ((targetPlayer = Bukkit.getPlayer(targetName)) != null) {
				if (player.getLocation().distance(targetPlayer.getLocation()) >= 50) {
					player.sendMessage("§cO jogador está muito longe (50+ blocos).");
					return;
				}
				addCooldown(event.getPlayer(), HelixPvP.getInstance().getConfig().getInt("NinjaCooldown"));
				player.teleport(targetPlayer);
				player.sendMessage("§aTeleportado para §f" + targetName);
				player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 10);
			}
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		
		if (KitManager2.getPlayer(player.getName()).haskit2(this)) {
			map.remove(player.getName());
		}
		if (map.containsValue(player.getName())) {
			map.entrySet().removeIf(entry -> entry.getValue().equalsIgnoreCase(player.getName()));
		}
	}

}
