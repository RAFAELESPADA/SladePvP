package net.helixpvp.kit2;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager2;

public class Poseidon extends KitHandler2 {
	
	@EventHandler
	public void onDamage(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		Block block = player.getLocation().getBlock();
		
		if (KitManager2.getPlayer(player.getName()).haskit2(this) 
				&& block.getType().toString().contains("WATER")) {
			player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 7*20, 1));
			player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 9*20, 2));
		}
		
	}
}
