package net.helix.pvp.kit.provider;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import net.helix.pvp.kit.Habilidade;
import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.md_5.bungee.api.ChatColor;

public class Stomper extends KitHandler {
	
	@EventHandler(ignoreCancelled = true)
	public void onDamage(EntityDamageEvent event) {
		if (!(event.getEntity() instanceof Player)) {
			return;
		}
		Player player = (Player) event.getEntity();
		
		if (!KitManager.getPlayer(player.getName()).hasKit(this) 
				|| !event.getCause().equals(DamageCause.FALL)) {
			return;
		}

		if (event.getDamage() >= 4.0D) {
			player.getNearbyEntities(5.0, 5.0, 5.0).stream().filter(
					entity -> entity.getType().equals(EntityType.PLAYER) 
						&& KitManager.getPlayer(entity.getName()).hasKit()
			).forEach(entity -> {
				Player target = (Player) entity;
				if (Habilidade.getAbility(target) == "SteelHead") {
					player.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
					target.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
					player.sendMessage(ChatColor.AQUA + "Você stompou um NEO e nao surtiu efeito nele");
					target.sendMessage(ChatColor.AQUA + "Seu kit NEO o protegeu contra o stomper");
					return;
				}
				else if (player.getFallDistance() >= 20 && !target.isSneaking()) {
					target.setHealth(0);
					player.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
					target.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
					target.sendMessage(ChatColor.RED + "Você foi stompado por " + player.getName());
				}
				else if (player.getItemInHand().equals(Material.STONE_SWORD) || player.getItemInHand().equals(Material.IRON_SWORD)) {
					target.damage(target.isSneaking() ? 4.0D : event.getDamage() * player.getFallDistance() * 5.3, player);
					target.sendMessage(ChatColor.RED + "Você foi stompado por " + player.getName());
				}
				target.damage(target.isSneaking() ? 4.0D : event.getDamage() * player.getFallDistance() * 1.7, player);
				player.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
				target.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
				target.sendMessage(ChatColor.AQUA + "Você foi stompado por " + player.getName());
			});
		}
		player.playSound(player.getLocation(), Sound.ANVIL_LAND, 15.0f, 15.0f);
		event.setDamage(3.0D);
	}
}

