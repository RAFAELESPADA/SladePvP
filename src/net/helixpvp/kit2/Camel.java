package net.helixpvp.kit2;


import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager2;

public class Camel extends KitHandler2 {

    @EventHandler
    public void EntityDamage(EntityDamageEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (!KitManager2.getPlayer(player.getName()).haskit2(this)) {
        	return;
        }
        if (event.getCause() == EntityDamageEvent.DamageCause.CONTACT) event.setCancelled(true);
    }

    @EventHandler
    public void PlayerMove(PlayerMoveEvent event) {
        if (event.isCancelled()) return;
        Player player = event.getPlayer();
        if (!KitManager2.getPlayer(player.getName()).haskit2(this)) {
        	return;
        }
        Block block = event.getTo().getBlock().getRelative(BlockFace.DOWN);
        if (block.getBiome().name().contains("DESERT") || block.getType() == Material.SAND || block.getType() == Material.SANDSTONE) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 4 * 20, 0), true);
            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 4 * 20, 0), true);
        }
    }

}
