package net.helix.pvp.kit.provider;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import net.helix.pvp.HelixPvP;
import net.helix.pvp.kit.HelixKit;
import net.helix.pvp.kit.HelixKit2;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;
import net.md_5.bungee.api.ChatColor;


public class Ninja extends KitHandler {
	
	public static final HashMap<String, String> map = new HashMap<>();
	
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageByEntityEvent event) {
		if (!(event.getEntity() instanceof Player) 
				|| (!(event.getDamager() instanceof Player))) {
			return;
		}
		Player victim = (Player) event.getEntity();
		Player damager = (Player) event.getDamager();
		
		if (KitManager.getPlayer(damager.getName()).hasKit(this) 
				&& KitManager.getPlayer(victim.getName()).hasKit()) {
			map.put(damager.getName(), victim.getName());
		}
	}
	
	@EventHandler
	public void onSneaking(PlayerToggleSneakEvent event) {
		if (event.isSneaking()) return;
		Player player = event.getPlayer();
		Player mage = event.getPlayer();
		if (mage.getLocation().getY() > HelixPvP.getInstance().getConfig().getInt("SpawnAltura")) {
			return;
		 }
		if (KitManager.getPlayer(player.getName()).hasKit(this) 
				&& map.containsKey(player.getName())) {
			
			
			String targetName = map.get(player.getName());
			Player targetPlayer;
			if (inCooldown(player) && KitManager.getPlayer(player.getName()).hasKit(this)) {
				sendMessageCooldown(player);
				return;
			}
			if ((targetPlayer = Bukkit.getPlayer(targetName)) != null) {
				if (player.getLocation().distance(targetPlayer.getLocation()) >= 50) {
					player.sendMessage("§cThe player is too far away (50+ blocks).");
					return;
				}
				if (GladiatorListener.combateGlad.containsKey(targetPlayer) || net.helixpvp.kit2.GladiatorListener.combateGlad.containsKey(targetPlayer)) {
					player.sendMessage("§cThis player is on gladiator.");
					return;
				}
				 if (KitManager.getPlayer(targetPlayer.getName()).hasKit(HelixKit.NEO) || KitManager2.getPlayer(targetPlayer.getName()).haskit2(HelixKit2.NEO)) {
					 event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.NOTE_BASS_DRUM, 15.0f, 15.0f);
					 event.getPlayer().sendMessage(ChatColor.AQUA + "You cant use the ninja on " + targetPlayer.getName() + " because he has kit NEO");
						return;
					}
				addCooldown(event.getPlayer(), HelixPvP.getInstance().getConfig().getInt("NinjaCooldown"));
				player.teleport(targetPlayer);
				player.sendMessage("§aTeleported to §f" + targetName);
				player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 10);
			}
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent event) {
		Player player = event.getEntity();
		
		if (KitManager.getPlayer(player.getName()).hasKit(this)) {
			map.remove(player.getName());
		}
		if (map.containsValue(player.getName())) {
			map.entrySet().removeIf(entry -> entry.getValue().equalsIgnoreCase(player.getName()));
		}
	}

}
