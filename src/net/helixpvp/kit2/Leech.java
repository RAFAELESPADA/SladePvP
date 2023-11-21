package net.helixpvp.kit2;


import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import net.helix.pvp.kit.KitHandler2;
import net.helix.pvp.kit.KitManager;
import net.helix.pvp.kit.KitManager2;

public class Leech extends KitHandler2 {

    @EventHandler
    public void EntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player)) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) return;		
		Player damager = (Player) event.getDamager();
		if (!KitManager2.getPlayer(damager.getName()).haskit2(this)) {
			return;
		}
        Player target = (Player) event.getEntity();
        int chance = new Random().nextInt(10);
        if (chance < 3) {
            if (damager.getHealth() + 1.5 < 20) {
                damager.setHealth(damager.getHealth() + 1.5);
                if (target.getHealth() - 1.5 > 0) target.setHealth(target.getHealth() - 1.5);
                damager.sendMessage("§aYou drained your enemy life.");
                target.sendMessage("§cYour life has been drained by a leech.");
            }
        }
    }

}
