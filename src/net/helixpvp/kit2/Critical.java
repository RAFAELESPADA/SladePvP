package net.helixpvp.kit2;


import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager2;
import net.md_5.bungee.api.ChatColor;

public class Critical extends KitHandler2 {

    @EventHandler
    public void EntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player)) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) return;
        Player damager = (Player) event.getDamager();
        if (!KitManager2.getPlayer(damager.getName()).haskit2(this)) {
			return;
		}
        int chance = new Random().nextInt(10);
        if (chance < 3) {
            event.setDamage(event.getDamage() + 0.70);
            damager.sendMessage(ChatColor.DARK_RED + "You give a critical hit!");
            damager.getWorld().playEffect(event.getEntity().getLocation(), Effect.STEP_SOUND, Material.EMERALD_BLOCK, 10);
        }
    }

	/*    */ }
