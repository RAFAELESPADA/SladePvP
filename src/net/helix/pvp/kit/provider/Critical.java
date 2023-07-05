package net.helix.pvp.kit.provider;



import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import net.helix.pvp.kit.KitHandler;
import net.helix.pvp.kit.KitManager;
import net.md_5.bungee.api.ChatColor;

public class Critical extends KitHandler {

    @EventHandler
    public void EntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.isCancelled()) return;
        if (!(event.getEntity() instanceof Player) || !(event.getDamager() instanceof Player)) return;
        if (event.getCause() != EntityDamageEvent.DamageCause.ENTITY_ATTACK) return;
        Player damager = (Player) event.getDamager();
        if (!KitManager.getPlayer(damager.getName()).hasKit(this)) {
			return;
		}
        int chance = new Random().nextInt(10);
        if (chance < 3) {
            event.setDamage(event.getDamage() + 0.70);
            damager.sendMessage(ChatColor.DARK_RED + "Você deu um hit critico!");
            damager.getWorld().playEffect(event.getEntity().getLocation(), Effect.STEP_SOUND, Material.GOLD_BLOCK, 10);
        }
    }

	/*    */ }


