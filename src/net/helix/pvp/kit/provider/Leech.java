package net.helix.pvp.kit.provider;


import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;

public class Leech extends KitHandler {

    @EventHandler
    public void EntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player)) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) return;		
		Player damager = (Player) event.getDamager();
		if (!KitManager.getPlayer(damager.getName()).hasKit(this)) {
			return;
		}
        Player target = (Player) event.getEntity();
        int chance = new Random().nextInt(10);
        if (chance < 3) {
            if (damager.getHealth() + 1.5 < 20) {
                damager.setHealth(damager.getHealth() + 1.5);
                if (target.getHealth() - 1.5 > 0) target.setHealth(target.getHealth() - 1.5);
                damager.sendMessage("§aYou drained the enemy life.");
                target.sendMessage("§cYour life gets drained by a Leech.");
            }
        }
    }

}
